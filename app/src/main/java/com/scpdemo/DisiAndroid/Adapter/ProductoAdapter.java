package com.scpdemo.DisiAndroid.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.scpdemo.DisiAndroid.Entities.Producto;

import com.scpdemo.DisiAndroid.R;

import java.util.List;

/**
 * Created by Renan on 27/02/2015.
 */
public class ProductoAdapter  extends ArrayAdapter<Producto> {
    public ProductoAdapter(Context context, int resource, List<Producto> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainHolder mainHolder = null;
        if (convertView == null || !(convertView.getTag() instanceof MainHolder)) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_producto, parent, false);
            mainHolder = new MainHolder();
            mainHolder.pr_itemcodigo = (TextView) convertView.findViewById(R.id.pr_itemcodigo);
            mainHolder.pr_itemmoneda = (TextView) convertView.findViewById(R.id.pr_itemmoneda);
            mainHolder.pr_itemtipo = (TextView) convertView.findViewById(R.id.pr_itemtipo);
            mainHolder.pr_itemnombre = (TextView) convertView.findViewById(R.id.pr_itemnombre);
            mainHolder.pr_itemdescripcion = (TextView) convertView.findViewById(R.id.pr_itemdescripcion);
            mainHolder.pr_itemprecio = (TextView) convertView.findViewById(R.id.pr_itemprecio);
            mainHolder.pr_itemestado = (TextView) convertView.findViewById(R.id.pr_itemestado);
            mainHolder.pr_foto = (ImageView)convertView.findViewById(R.id.foto);

            convertView.setTag(mainHolder);
        } else {
            mainHolder = (MainHolder) convertView.getTag();
        }
        Producto producto = getItem(position);

        mainHolder.pr_itemcodigo.setText(String.valueOf(producto.getPRODCOD()));
        mainHolder.pr_itemmoneda.setText(producto.getPRODMON());
        mainHolder.pr_itemtipo.setText(producto.getPRODTIP());
        mainHolder.pr_itemnombre.setText(producto.getPRODNOM());
        mainHolder.pr_itemdescripcion.setText(producto.getPRODDES());
        mainHolder.pr_itemprecio.setText(Double.toString(producto.getPRODPRE()));

        if(producto.getPRODINA()==1){
            mainHolder.pr_itemestado.setText(String.valueOf("Agotado"));
        }
       else{
            mainHolder.pr_itemestado.setText(String.valueOf("Disponible"));
        }

        mainHolder.pr_foto.setImageResource(producto.getFOTO());
        return convertView;
    }

    static class MainHolder {
        TextView pr_itemcodigo, pr_itemnombre,pr_itemtipo,pr_itemmoneda,pr_itemprecio,pr_itemdescripcion,pr_itemestado;
        ImageView pr_foto;

    }
}
