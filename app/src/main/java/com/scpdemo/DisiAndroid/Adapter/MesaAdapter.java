package com.scpdemo.DisiAndroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.scpdemo.DisiAndroid.Entities.Cliente;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.Entities.Mesa;
import java.util.List;

/**
 * Created by rgalvez on 26/02/2015.
 */

public class MesaAdapter extends ArrayAdapter<Mesa> {
    public MesaAdapter(Context context, int resource, List<Mesa> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainHolder mainHolder = null;
        if (convertView == null || !(convertView.getTag() instanceof MainHolder)) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_mesa, parent, false);
            mainHolder = new MainHolder();
            mainHolder.me_itemcodigo = (TextView) convertView.findViewById(R.id.me_itemcodigo);
            mainHolder.me_itemnombre = (TextView) convertView.findViewById(R.id.me_itemnombre);
            mainHolder.me_itemEstado = (TextView) convertView.findViewById(R.id.me_itemestado);
            mainHolder.me_foto = (ImageView)convertView.findViewById(R.id.foto);

            convertView.setTag(mainHolder);
        } else {
            mainHolder = (MainHolder) convertView.getTag();
        }
        Mesa mesa = getItem(position);

        mainHolder.me_itemcodigo.setText(String.valueOf(mesa.getMESACOD()));
        mainHolder.me_itemnombre.setText(mesa.getMESADES());
        mainHolder.me_itemEstado.setText(String.valueOf(mesa.getMESAINA()));
        mainHolder.me_foto.setImageResource(mesa.getFOTO());
        return convertView;
    }

    static class MainHolder {
        TextView me_itemcodigo, me_itemnombre,me_itemEstado;
        ImageView me_foto;

    }
}


