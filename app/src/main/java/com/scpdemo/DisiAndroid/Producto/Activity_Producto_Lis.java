package com.scpdemo.DisiAndroid.Producto;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.Toast;


import com.scpdemo.DisiAndroid.Adapter.ProductoAdapter;

import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;

import com.scpdemo.DisiAndroid.DAO.ProductoDAO;

import com.scpdemo.DisiAndroid.Entities.Producto;

import com.scpdemo.DisiAndroid.R;


/**
 * Created by Renan on 27/02/2015.
 */

public class Activity_Producto_Lis extends ActionBarActivity {
    private final int RequestCode = 1;
    private boolean isAdd = false;

    private ListView lvProducto;
    private ProductoAdapter productoAdapter = null;
    private ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_list);

        lvProducto = (ListView) findViewById(R.id.pr_lvProducto);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.producto_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        //etFiltro.addTextChangedListener(etMenuTextWatcher);

        try{
            lvProducto.setOnItemClickListener(lvProductoOnItemClickListener);

            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Producto_Lis.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            productoDAO.Producto_PopulateList();
            productoAdapter = new ProductoAdapter(Activity_Producto_Lis.this, 0, productoDAO.lstProducto);
            productoAdapter.notifyDataSetChanged();
            lvProducto.setAdapter(productoAdapter);

        }catch (Exception ex){
            Toast.makeText(Activity_Producto_Lis.this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public boolean onQueryTextChange(String s) {
        //texto.setText("Escribiendo texto...\n\n" + s);
        Toast.makeText(Activity_Producto_Lis.this,R.string.mensaje_conexion, Toast.LENGTH_SHORT).show();
        return false;
    }

    TextWatcher etMenuTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d("watcher", s.toString());
            productoAdapter.getFilter().filter(s.toString());
            productoAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_producto_add, menu);
        menu.findItem(R.id.ic_action_add).setVisible(!isAdd);
        menu.findItem(R.id.ic_action_save).setVisible(isAdd);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_action_add:
                isAdd = true;
                //invalidateOptionsMenu();
                Intent intent = new Intent(Activity_Producto_Lis.this, Activity_Producto.class);
                intent.putExtra("s_Productocod","0");
                startActivityForResult(intent,1);
                return true;
           /* case R.id.ic_action_save:
                isAdd = false;
                invalidateOptionsMenu();
                return true;
                */
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    AbsListView.OnItemClickListener lvProductoOnItemClickListener = new AbsListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Producto producto = productoAdapter.getItem(position);
            Intent intent=new Intent(Activity_Producto_Lis.this,Activity_Producto.class);
            intent.putExtra("s_productocod", String.valueOf(productoAdapter.getItem(position).getPRODCOD()));
            intent.putExtra("s_productonom", productoAdapter.getItem(position).getPRODNOM().toString());
            intent.putExtra("s_productotip", productoAdapter.getItem(position).getPRODTIP().toString());
            intent.putExtra("s_productomon", productoAdapter.getItem(position).getPRODNOM().toString());
            intent.putExtra("s_productopre", productoAdapter.getItem(position).getPRODPRE());
            intent.putExtra("s_productodes", productoAdapter.getItem(position).getPRODDES().toString());
            intent.putExtra("s_productoina", String.valueOf(productoAdapter.getItem(position).getPRODINA()));
            startActivityForResult(intent,1);



        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode) {
            if (resultCode == RESULT_OK) {
                productoDAO.Producto_PopulateList();
                productoAdapter = new ProductoAdapter(Activity_Producto_Lis.this, 0, productoDAO.lstProducto);
                productoAdapter.notifyDataSetChanged();
                lvProducto.setAdapter(productoAdapter);
            } else {
                //tvMainTextA.setText("Canceló");
            }
        }
    }
}

