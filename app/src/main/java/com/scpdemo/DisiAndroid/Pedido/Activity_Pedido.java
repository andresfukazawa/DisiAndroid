package com.scpdemo.DisiAndroid.Pedido;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.scpdemo.DisiAndroid.Producto.Fragment_Cliente;
import com.scpdemo.DisiAndroid.Producto.Fragment_Pedido;
import com.scpdemo.DisiAndroid.Producto.Fragment_Producto;
import com.scpdemo.DisiAndroid.R;

/**
 * Created by Susumu on 3/3/2015.
 */
public class Activity_Pedido extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
    }
}
