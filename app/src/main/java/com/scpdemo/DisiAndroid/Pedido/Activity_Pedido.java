package com.scpdemo.DisiAndroid.Pedido;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Susumu on 3/3/2015.
 */
public class Activity_Pedido extends ActionBarActivity {

    private Spinner spMesa;
    private Button  btEntradas, btPFondo, btPostres;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        spMesa     = (Spinner)findViewById(R.id.sp_pe_mesa);
        btEntradas = (Button)findViewById(R.id.bt_pe_entradas);
        btPFondo   = (Button)findViewById(R.id.bt_pe_pfondo);
        btPostres  = (Button)findViewById(R.id.bt_pe_postres);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.dashboard_title_pedidos);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        populateSpinner();

        btEntradas.setOnClickListener(btEntradasOnClick);
        btPFondo.setOnClickListener(btPFondoOnClick);
        btPostres.setOnClickListener(btPostresOnClick);


        spMesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextSize(48);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void populateSpinner() {

        List<String> list = new ArrayList<String>();
        Cursor cursor = null;

        try{
            cursor = DataBaseHelper.myDataBase.query("MESA", new String[]{"MESACOD"}, null, null, null, null, "MESACOD");
            if(cursor.moveToFirst()) {
                do {
                    list.add(cursor.getInt(cursor.getColumnIndex("MESACOD"))+"");
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spMesa.setAdapter(dataAdapter);
    }

    private void mostrarMenu(String tipo, String mesa) {
        Intent intent = new Intent(Activity_Pedido.this, Activity_MostrarMenu.class);
        intent.putExtra("tipo", tipo);
        intent.putExtra("mesa", mesa);
        startActivity(intent);
    }

    View.OnClickListener btEntradasOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mostrarMenu("1", spMesa.getSelectedItem().toString());
        }
    };

    View.OnClickListener btPFondoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mostrarMenu("2", spMesa.getSelectedItem().toString());
        }
    };

    View.OnClickListener btPostresOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mostrarMenu("3", spMesa.getSelectedItem().toString());
        }
    };

}
