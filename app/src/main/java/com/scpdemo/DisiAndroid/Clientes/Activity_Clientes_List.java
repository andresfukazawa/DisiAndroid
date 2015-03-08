package com.scpdemo.DisiAndroid.Clientes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.scpdemo.DisiAndroid.Adapter.ClienteAdapter;
import com.scpdemo.DisiAndroid.Adapter.MesaAdapter;
import com.scpdemo.DisiAndroid.Entities.Cliente;
import com.scpdemo.DisiAndroid.Mesa.Activity_Mesa;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.DAO.ClienteDAO;
import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.Entities.Cliente;
import java.util.ArrayList;

/**
 * Created by rgalvez on 03/03/2015.
 */
public class Activity_Clientes_List extends ActionBarActivity {
    private final int RequestCode = 1;
    private boolean isAdd = false;

    private ListView cl_lvclientes;
    private ArrayList<Cliente> lstCliente = null;
    private ClienteAdapter clienteAdapter = null;
    private ClienteDAO clienteDAO = new ClienteDAO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_list);

        cl_lvclientes = (ListView) findViewById(R.id.cl_lvclientes);
        try {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
            getSupportActionBar().setSubtitle(R.string.cliente_title_Lis);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setIcon(R.drawable.ic_launcher);

            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Clientes_List.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            clienteDAO.Cliente_PopulateList();
            clienteAdapter = new ClienteAdapter(Activity_Clientes_List.this, 0, clienteDAO.lstCliente);
            clienteAdapter.notifyDataSetChanged();
            cl_lvclientes.setAdapter(clienteAdapter);

            cl_lvclientes.setOnItemClickListener(lvClienteOnItemClickListener);

        } catch (Exception ex) {
            Toast.makeText(Activity_Clientes_List.this, R.string.mensaje_conexion, Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(Activity_Clientes_List.this, Activity_Clientes.class);
                intent.putExtra("s_cliecod", "0");
                startActivityForResult(intent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    AbsListView.OnItemClickListener lvClienteOnItemClickListener = new AbsListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cliente cliente = clienteAdapter.getItem(position);
            Intent intent=new Intent(Activity_Clientes_List.this,Activity_Clientes.class);

            intent.putExtra("s_cliecod", String.valueOf(clienteAdapter.getItem(position).getCLIECOD()));
            intent.putExtra("s_clienom", clienteAdapter.getItem(position).getCLIENOM().toString());
            intent.putExtra("s_clieruc", clienteAdapter.getItem(position).getCLIERUC().toString());
            startActivityForResult(intent,1);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCode) {
            if (resultCode == RESULT_OK) {
                //tvMainTextA.setText(data.getStringExtra("TextA"));
                clienteDAO.Cliente_PopulateList();
                clienteAdapter = new ClienteAdapter(Activity_Clientes_List.this, 0, clienteDAO.lstCliente);
                clienteAdapter.notifyDataSetChanged();
                cl_lvclientes.setAdapter(clienteAdapter);
            } else {

            }
        }
    }





}

