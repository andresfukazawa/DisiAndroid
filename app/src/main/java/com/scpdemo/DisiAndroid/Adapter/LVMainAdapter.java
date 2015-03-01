package com.scpdemo.DisiAndroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renan on 28/02/2015.
 */
public class LVMainAdapter extends ArrayAdapter<String> implements Filterable {

    private ArrayList<String> mLstStringFiltrado;
    private LVMenuAdapterFilter mLVMainAdapterFilter;
    private ArrayList<String> mLstString;

    public LVMainAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        mLVMainAdapterFilter = new LVMenuAdapterFilter();
        mLstStringFiltrado = new ArrayList<>();
        mLstStringFiltrado.addAll(objects);
        mLstString = (ArrayList<String>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);

        text1.setText(mLstStringFiltrado.get(position));

        return convertView;
    }

    @Override
    public int getCount() {
        return mLstStringFiltrado.size();
    }

    @Override
    public String getItem(int position) {
        return mLstStringFiltrado.get(position);
    }

    @Override
    public Filter getFilter() {
        if (mLVMainAdapterFilter == null)
            mLVMainAdapterFilter = new LVMenuAdapterFilter();
        return mLVMainAdapterFilter;
    }

    private class LVMenuAdapterFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint == null || constraint.length() <= 0) {
                filterResults.values = mLstString;
                filterResults.count = mLstString.size();
            } else {
                ArrayList<String> lstString = new ArrayList<>();
                for (int i = 0; i < mLstString.size(); i++) {
                    if (mLstString.get(i).toUpperCase().contains(constraint.toString().toUpperCase()))
                        lstString.add(mLstString.get(i));
                }

                filterResults.values = lstString;
                filterResults.count = lstString.size();
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mLstStringFiltrado.clear();
            mLstStringFiltrado.addAll((ArrayList<String>) results.values);
        }
    }
}