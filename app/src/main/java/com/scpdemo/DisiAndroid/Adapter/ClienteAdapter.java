package com.scpdemo.DisiAndroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.scpdemo.DisiAndroid.Entities.Cliente;
import com.scpdemo.DisiAndroid.R;
import java.util.List;

/**
 * Created by Renan on 03/03/2015.
 */
public class ClienteAdapter extends ArrayAdapter<Cliente> {
    public ClienteAdapter(Context context, int resource, List<Cliente> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainHolder mainHolder = null;
        if (convertView == null || !(convertView.getTag() instanceof MainHolder)) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cliente, parent, false);
            mainHolder = new MainHolder();
            mainHolder.cl_itemCodigo = (TextView) convertView.findViewById(R.id.cl_itemCodigo);
            mainHolder.cl_itemRUC = (TextView) convertView.findViewById(R.id.cl_itemRUC);
            mainHolder.cl_itemNombre = (TextView) convertView.findViewById(R.id.cl_itemNombre);
            mainHolder.cl_foto = (ImageView)convertView.findViewById(R.id.foto);
            convertView.setTag(mainHolder);
        } else {
            mainHolder = (MainHolder) convertView.getTag();
        }
        Cliente clientes = getItem(position);

        mainHolder.cl_itemCodigo.setText(String.valueOf(clientes.getCLIECOD()));
        mainHolder.cl_itemRUC.setText(clientes.getCLIERUC());
        mainHolder.cl_itemNombre.setText(clientes.getCLIENOM());
        mainHolder.cl_foto.setImageResource(clientes.getFOTO());
        return convertView;
    }


    static class MainHolder {
        TextView cl_itemCodigo, cl_itemRUC, cl_itemNombre;
        ImageView cl_foto;
    }
}