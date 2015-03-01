package com.scpdemo.DisiAndroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.scpdemo.DisiAndroid.Entities.Mesa;
import com.scpdemo.DisiAndroid.Entities.Usuario;
import com.scpdemo.DisiAndroid.R;

import java.util.List;

/**
 * Created by Renan on 27/02/2015.
 */
public class UsuarioAdapter extends ArrayAdapter<String> {
    public UsuarioAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainHolder mainHolder = null;
        if(convertView == null || !(convertView.getTag() instanceof MainHolder)){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_usuario,parent,false);
            mainHolder = new MainHolder();
            mainHolder.us_itemNombre = (TextView)convertView.findViewById(R.id.us_itemNombre);
            convertView.setTag(mainHolder);
        }
        else{
            mainHolder = (MainHolder)convertView.getTag();        }
        //Mesa mesa = getItem(position);
        Usuario usuario = new Usuario(getItem(position).toString());
        mainHolder.us_itemNombre.setText(usuario.getUSUADES());
        return convertView;
    }

    static class MainHolder{
        TextView us_itemNombre;
    }
}