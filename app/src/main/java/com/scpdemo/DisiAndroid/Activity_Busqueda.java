package com.scpdemo.DisiAndroid;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rgalvez on 20/02/2015.
 */
public class Activity_Busqueda extends ActionBarActivity implements SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        texto = (TextView) findViewById(R.id.texto);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.busqueda, menu);

        MenuItem searchItem = menu.findItem(R.id.menu3_buscar);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

         searchView.setQueryHint("Ingrese Valor a buscar");
        searchView.setBackgroundResource(R.drawable.abc_ab_share_pack_holo_dark);

        MenuItemCompat.setOnActionExpandListener(searchItem, this);

        return super.onCreateOptionsMenu(menu);
    }


    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        Toast.makeText(getApplicationContext(), "EXPAND", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        Toast.makeText(getApplicationContext(), "COLLAPSE", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        texto.setText("Texto a buscar\n\n" + s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        texto.setText("Escribiendo texto...\n\n" + s);
        return false;
    }
}