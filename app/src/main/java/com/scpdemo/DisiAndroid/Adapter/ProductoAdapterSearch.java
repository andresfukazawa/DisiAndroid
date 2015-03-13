package com.scpdemo.DisiAndroid.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.scpdemo.DisiAndroid.Entities.Producto;
import com.scpdemo.DisiAndroid.R;

import java.util.List;

/**
 * Created by Renan on 27/02/2015.
 */
public class ProductoAdapterSearch extends ArrayAdapter<Producto> {
    public ProductoAdapterSearch(Context context, int resource, List<Producto> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainHolder mainHolder = null;
        if (convertView == null || !(convertView.getTag() instanceof MainHolder)) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_productosearh, parent, false);
            mainHolder = new MainHolder();
            mainHolder.pr_itemcodigo = (TextView) convertView.findViewById(R.id.pr_itemcodigo);
            mainHolder.pr_itemnombre = (TextView) convertView.findViewById(R.id.pr_itemnombre);
            mainHolder.pr_itemprecio = (TextView) convertView.findViewById(R.id.pr_itemprecio);
            convertView.setTag(mainHolder);
        } else {
            mainHolder = (MainHolder) convertView.getTag();
        }
        Producto producto = getItem(position);
        mainHolder.pr_itemcodigo.setText(String.valueOf(producto.getPRODCOD()));
        mainHolder.pr_itemnombre.setText(producto.getPRODNOM());
        mainHolder.pr_itemprecio.setText(String.valueOf(producto.getPRODPRE()));
        return convertView;
    }

    static class MainHolder {
        TextView pr_itemcodigo, pr_itemnombre,pr_itemprecio;
    }
}
