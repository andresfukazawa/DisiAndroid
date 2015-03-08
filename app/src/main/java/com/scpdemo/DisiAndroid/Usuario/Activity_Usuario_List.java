package com.scpdemo.DisiAndroid.Usuario;

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
import android.widget.TextView;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.Adapter.MesaAdapter;
import com.scpdemo.DisiAndroid.Adapter.UsuarioAdapter;
import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.DAO.MesaDAO;
import com.scpdemo.DisiAndroid.DAO.UsuarioDAO;
import com.scpdemo.DisiAndroid.Entities.Usuario;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.Adapter.LVMainAdapter;

import java.util.ArrayList;

/**
 * Created by Renan on 27/02/2015.
 */

public class Activity_Usuario_List extends ActionBarActivity {
    private final int RequestCode = 1;
    private boolean isAdd = false;

    private ListView lvUsuario;
    private UsuarioAdapter usuarioAdapter = null;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_list);

        lvUsuario = (ListView) findViewById(R.id.us_lvUsuario);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.usuario_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        //etFiltro.addTextChangedListener(etMenuTextWatcher);

        try{
            lvUsuario.setOnItemClickListener(lvUsuarioOnItemClickListener);

            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Usuario_List.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            usuarioDAO.Usuario_PopulateList();
            usuarioAdapter = new UsuarioAdapter(Activity_Usuario_List.this, 0, usuarioDAO.lstUsuario);
            usuarioAdapter.notifyDataSetChanged();
            lvUsuario.setAdapter(usuarioAdapter);

        }catch (Exception ex){
            Toast.makeText(Activity_Usuario_List.this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public boolean onQueryTextChange(String s) {
        //texto.setText("Escribiendo texto...\n\n" + s);
        Toast.makeText(Activity_Usuario_List.this,R.string.mensaje_conexion, Toast.LENGTH_SHORT).show();
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
            usuarioAdapter.getFilter().filter(s.toString());
            usuarioAdapter.notifyDataSetChanged();
        }
    };

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
                Intent intent = new Intent(Activity_Usuario_List.this, Activity_Usuario.class);
                intent.putExtra("s_usuacod","0");
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

    AbsListView.OnItemClickListener lvUsuarioOnItemClickListener = new AbsListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Usuario usuario = usuarioAdapter.getItem(position);
            Intent intent=new Intent(Activity_Usuario_List.this,Activity_Usuario.class);
            intent.putExtra("s_usuacod", String.valueOf(usuarioAdapter.getItem(position).getUSUACOD()));
            intent.putExtra("s_usuanom", usuarioAdapter.getItem(position).getUSUADES().toString());
            intent.putExtra("s_usuacla", usuarioAdapter.getItem(position).getUSUACLAV());
            intent.putExtra("s_usuamai", usuarioAdapter.getItem(position).getUSUAMAIL().toString());
            intent.putExtra("s_usuaina", String.valueOf(usuarioAdapter.getItem(position).getUSUAINA()));
            startActivityForResult(intent,1);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode) {
            if (resultCode == RESULT_OK) {
                usuarioDAO.Usuario_PopulateList();
                usuarioAdapter = new UsuarioAdapter(Activity_Usuario_List.this, 0, usuarioDAO.lstUsuario);
                usuarioAdapter.notifyDataSetChanged();
                lvUsuario.setAdapter(usuarioAdapter);
            } else {
                //tvMainTextA.setText("Canceló");
            }
        }
    }
}

