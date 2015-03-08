package com.scpdemo.DisiAndroid.Mesa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.Adapter.MesaAdapter;
import com.scpdemo.DisiAndroid.Clientes.Activity_Clientes;
import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.DAO.MesaDAO;
import com.scpdemo.DisiAndroid.Entities.Mesa;
import com.scpdemo.DisiAndroid.R;

import java.util.ArrayList;

/**
 * Created by Renan on 24/02/2015.
 */
public class Activity_Mesa_Lis extends ActionBarActivity {
    private TextView me_etCodigo, me_etNombre;
    private final int RequestCode = 1;
    private boolean isAdd = false;

    private ListView lvMesa;
    private MesaAdapter mesaAdapter = null;
    private MesaDAO mesaDAO = new MesaDAO();

    /*
    Activa la back Navigation cuando el extends es a Una Activity
       ActionBar actionBar=getActionBar();
       actionBar.setDisplayHomeAsUpEnabled(true);
   */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa_list);

        me_etCodigo = (TextView) findViewById(R.id.me_etCodigo);
        me_etNombre = (TextView) findViewById(R.id.me_etNombre);
        lvMesa = (ListView) findViewById(R.id.me_lvmesa);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.mesa_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);


        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Mesa_Lis.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            mesaDAO.Mesa_PopulateList();
            mesaAdapter = new MesaAdapter(Activity_Mesa_Lis.this, 0, mesaDAO.lstMesa);
            mesaAdapter.notifyDataSetChanged();
            lvMesa.setAdapter(mesaAdapter);

            lvMesa.setOnItemClickListener(lvMesaOnItemClickListener);

        }catch (Exception ex){
            Toast.makeText(Activity_Mesa_Lis.this, R.string.mensaje_conexion, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mesa_add, menu);
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
                Intent intent = new Intent(Activity_Mesa_Lis.this, Activity_Mesa.class);
                intent.putExtra("s_mesacod", "0");
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

    AbsListView.OnItemClickListener lvMesaOnItemClickListener = new AbsListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Mesa mesa = mesaAdapter.getItem(position);
            Intent intent=new Intent(Activity_Mesa_Lis.this,Activity_Mesa.class);

            intent.putExtra("s_mesacod", String.valueOf(mesaAdapter.getItem(position).getMESACOD()));
            intent.putExtra("s_mesanom", mesaAdapter.getItem(position).getMESADES().toString());
            intent.putExtra("s_mesaina",String.valueOf(mesaAdapter.getItem(position).getMESAINA()));
            startActivityForResult(intent,1);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCode) {
            if (resultCode == RESULT_OK) {
                //tvMainTextA.setText(data.getStringExtra("TextA"));
                mesaDAO.Mesa_PopulateList();
                mesaAdapter = new MesaAdapter(Activity_Mesa_Lis.this, 0, mesaDAO.lstMesa);
                mesaAdapter.notifyDataSetChanged();
                lvMesa.setAdapter(mesaAdapter);
            } else {
                //tvMainTextA.setText("Canceló");

            }
        }
    }

}


