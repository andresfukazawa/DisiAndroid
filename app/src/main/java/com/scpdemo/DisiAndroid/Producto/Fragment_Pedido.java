package com.scpdemo.DisiAndroid.Producto;

/**
 * Created by rgalvez on 10/02/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.R;

public class Fragment_Pedido extends Fragment {
    View rootView;
    Button btPedido;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pedido, container, false);

        btPedido = (Button) rootView.findViewById(R.id.btPedido);

        btPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ingrese direccion", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

}
