package com.scpdemo.DisiAndroid.Mesa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.Adapter.MesaAdapter;
import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.DAO.MesaDAO;
import com.scpdemo.DisiAndroid.R;

import java.util.ArrayList;

/**
 * Created by Renan on 24/02/2015.
 */
public class Activity_Mesa_Lis extends ActionBarActivity {
    private TextView me_etCodigo, me_etNombre;
    private final int RequestCode = 1;
    private boolean isAdd = false;

    private MesaDAO mesaDAO = new MesaDAO();
   // private Mesa mesa=null;

    private ListView lvMesa;
    private MesaAdapter mesaAdapter=null;
    public ArrayList<String> mLstString;

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
            mesaAdapter = new MesaAdapter(Activity_Mesa_Lis.this, 0, mesaDAO.mLstString);
            mesaAdapter.notifyDataSetChanged();
            lvMesa.setAdapter(mesaAdapter);

        }catch (Exception ex){
            Toast.makeText(Activity_Mesa_Lis.this, ex.toString() , Toast.LENGTH_SHORT).show();
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
                //intent.putExtra("N1",tvMainTextA.getText().toString());
                //intent.putExtra("N2",tvMainTextB.getText().toString());
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCode) {
            if (resultCode == RESULT_OK) {
                //tvMainTextA.setText(data.getStringExtra("TextA"));
                mesaDAO.Mesa_PopulateList();
                mesaAdapter = new MesaAdapter(Activity_Mesa_Lis.this, 0, mesaDAO.mLstString);
                mesaAdapter.notifyDataSetChanged();
                lvMesa.setAdapter(mesaAdapter);
            } else {
                //tvMainTextA.setText("Canceló");
                //tvMainTextB.setText("El usuario");
            }
        }
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
}


