package com.scpdemo.DisiAndroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scpdemo.DisiAndroid.Entities.Cliente;
import com.scpdemo.DisiAndroid.Entities.Pedido_Detalle;
import com.scpdemo.DisiAndroid.R;

import java.util.List;

/**
 * Created by rgalvez on 11/03/2015.
 */
public class PedidoDetalleAdapter extends ArrayAdapter<Pedido_Detalle> {
    public PedidoDetalleAdapter(Context context, int resource, List<Pedido_Detalle> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainHolder mainHolder = null;
        if (convertView == null || !(convertView.getTag() instanceof MainHolder)) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pedidodetalle, parent, false);
            mainHolder = new MainHolder();

            mainHolder.dp_itemid = (TextView) convertView.findViewById(R.id.dp_itemid);
            mainHolder.dp_itemnropedido = (TextView) convertView.findViewById(R.id.dp_itemnropedido);
            mainHolder.dp_itemmesanro = (TextView) convertView.findViewById(R.id.dp_itemmesanro);
            mainHolder.dp_itemproductodesc = (TextView) convertView.findViewById(R.id.dp_itemproductodesc);
            mainHolder.dp_itemcantidad = (TextView) convertView.findViewById(R.id.dp_itemcantidad);
            mainHolder.dp_itemprecio = (TextView) convertView.findViewById(R.id.dp_itemprecio);
            /*
            mainHolder.dp_itemid = (TextView) convertView.findViewById(R.id.dp_itemid);
            mainHolder.dp_itemnropedido = (TextView) convertView.findViewById(R.id.dp_itemnropedido);
            mainHolder.dp_itemproductoid = (TextView) convertView.findViewById(R.id.dp_itemproductoid);
            mainHolder.dp_itemcantidad = (TextView) convertView.findViewById(R.id.dp_itemcantidad);
            mainHolder.dp_itemprecio = (TextView) convertView.findViewById(R.id.dp_itemprecio);
            mainHolder.dp_itemmesanro = (TextView) convertView.findViewById(R.id.dp_itemmesanro);
            mainHolder.dp_itemfecha = (TextView) convertView.findViewById(R.id.dp_itemfecha);
            mainHolder.dp_itemestadoid = (TextView) convertView.findViewById(R.id.dp_itemestadoid);
            mainHolder.dp_itemmesadesc = (TextView) convertView.findViewById(R.id.dp_itemmesadesc);
            mainHolder.dp_itemproductodesc = (TextView) convertView.findViewById(R.id.dp_itemproductodesc);
            */

            convertView.setTag(mainHolder);
        } else {
            mainHolder = (MainHolder) convertView.getTag();
        }
        Pedido_Detalle pedido_detalle = getItem(position);
        mainHolder.dp_itemid.setText(String.valueOf(pedido_detalle.getDETPITE()));
        mainHolder.dp_itemnropedido.setText(String.valueOf(pedido_detalle.getDETPPED()));
        mainHolder.dp_itemmesanro.setText(String.valueOf(pedido_detalle.getDETMESA()));
        mainHolder.dp_itemproductodesc.setText(pedido_detalle.getPRODNOM());
        mainHolder.dp_itemcantidad.setText(String.valueOf(pedido_detalle.getDETPCAN()));
        mainHolder.dp_itemprecio.setText(String.valueOf(pedido_detalle.getDETPPRE()));
        /*
        mainHolder.dp_itemid.setText(String.valueOf(pedido_detalle.getDETPITE()));
        mainHolder.dp_itemnropedido.setText(String.valueOf(pedido_detalle.getDETPPED()));
        mainHolder.dp_itemproductoid.setText(String.valueOf(pedido_detalle.getDETPPRO()));
        mainHolder.dp_itemcantidad.setText(String.valueOf(pedido_detalle.getDETPCAN()));
        mainHolder.dp_itemprecio.setText(String.valueOf(pedido_detalle.getDETPPRE()));
        mainHolder.dp_itemmesanro.setText(String.valueOf(pedido_detalle.getDETMESA()));
        mainHolder.dp_itemfecha.setText(pedido_detalle.getDETFECHA());
        mainHolder.dp_itemestadoid.setText(String.valueOf(pedido_detalle.getDETESTAD()));
        mainHolder.dp_itemmesadesc.setText(pedido_detalle.getMESADES());
        mainHolder.dp_itemproductodesc.setText(pedido_detalle.getPRODNOM());
        */
        return convertView;    }


    static class MainHolder {
        TextView dp_itemid, dp_itemnropedido, dp_itemproductoid,dp_itemcantidad;
        TextView dp_itemprecio,dp_itemmesanro,dp_itemfecha,dp_itemestadoid;
        TextView dp_itemmesadesc,dp_itemproductodesc;
}
    }
