package com.scpdemo.DisiAndroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scpdemo.DisiAndroid.Entities.Mesa;
import com.scpdemo.DisiAndroid.Entities.Usuario;
import com.scpdemo.DisiAndroid.R;

import java.util.List;

/**
 * Created by Renan on 27/02/2015.
 */
public class UsuarioAdapter  extends ArrayAdapter<Usuario> {
    public UsuarioAdapter(Context context, int resource, List<Usuario> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainHolder mainHolder = null;
        if (convertView == null || !(convertView.getTag() instanceof MainHolder)) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_usuario, parent, false);
            mainHolder = new MainHolder();
            mainHolder.us_itemcodigo = (TextView) convertView.findViewById(R.id.us_itemcodigo);
            mainHolder.us_itemnombre = (TextView) convertView.findViewById(R.id.us_itemnombre);
            mainHolder.us_itemclave = (TextView) convertView.findViewById(R.id.us_itemclave);
            mainHolder.us_itemmail = (TextView) convertView.findViewById(R.id.us_itemmail);
            mainHolder.us_itemestado = (TextView) convertView.findViewById(R.id.us_itemestado);
            mainHolder.us_foto = (ImageView)convertView.findViewById(R.id.foto);

            convertView.setTag(mainHolder);
        } else {
            mainHolder = (MainHolder) convertView.getTag();
        }
        Usuario usuario = getItem(position);

        mainHolder.us_itemcodigo.setText(String.valueOf(usuario.getUSUACOD()));
        mainHolder.us_itemnombre.setText(usuario.getUSUADES());
        mainHolder.us_itemclave.setText(usuario.getUSUACLAV());
        mainHolder.us_itemmail.setText(usuario.getUSUAMAIL());
        mainHolder.us_itemestado.setText(String.valueOf(usuario.getUSUAINA()));
        mainHolder.us_foto.setImageResource(usuario.getFOTO());
        return convertView;
    }

    static class MainHolder {
        TextView us_itemcodigo, us_itemnombre,us_itemclave,us_itemmail,us_itemestado;
        ImageView us_foto;

    }
}
