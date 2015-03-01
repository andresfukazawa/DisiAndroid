package com.scpdemo.DisiAndroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.Entities.Mesa;
import java.util.List;

/**
 * Created by rgalvez on 26/02/2015.
 */

public class MesaAdapter extends ArrayAdapter<String> {
    public MesaAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainHolder mainHolder = null;
        if(convertView == null || !(convertView.getTag() instanceof MainHolder)){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_mesa,parent,false);

            mainHolder = new MainHolder();
            //mainHolder.me_itemCodigo = (TextView)convertView.findViewById(R.id.me_itemCodigo);
            mainHolder.me_itemNombre = (TextView)convertView.findViewById(R.id.me_itemNombre);
            //mainHolder.me_itemEstado = (CheckBox)convertView.findViewById(R.id.me_itemEstado);
            convertView.setTag(mainHolder);
        }
        else{
            mainHolder = (MainHolder)convertView.getTag();
        }
        //Mesa mesa = getItem(position);
         Mesa mesa = new Mesa(getItem(position).toString());
       // mainHolder.me_itemCodigo.setText(mesa.getMESACOD());
        mainHolder.me_itemNombre.setText(mesa.getMESADES());
        //mainHolder.me_itemEstado.setChecked(Boolean.parseBoolean(mesa.getMESADES()));
        return convertView;
    }

    static class MainHolder{
        TextView me_itemNombre;
       //CheckBox me_itemEstado;
    }

}
