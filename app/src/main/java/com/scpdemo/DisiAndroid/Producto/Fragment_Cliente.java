package com.scpdemo.DisiAndroid.Producto;

/**
 * Created by rgalvez on 10/02/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scpdemo.DisiAndroid.R;

public class Fragment_Cliente extends Fragment{
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cliente, container, false);
        return rootView;
    }
}
