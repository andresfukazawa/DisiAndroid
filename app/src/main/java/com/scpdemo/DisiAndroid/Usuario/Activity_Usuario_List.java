package com.scpdemo.DisiAndroid.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.Adapter.MesaAdapter;
import com.scpdemo.DisiAndroid.Adapter.UsuarioAdapter;
import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.DAO.MesaDAO;
import com.scpdemo.DisiAndroid.DAO.UsuarioDAO;
import com.scpdemo.DisiAndroid.Mesa.Activity_Mesa;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.Adapter.LVMainAdapter;

import java.util.ArrayList;

/**
 * Created by Renan on 27/02/2015.
 */

public class Activity_Usuario_List extends ActionBarActivity {
    private final int RequestCode = 1;
    private boolean isAdd = false;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    // private Mesa mesa=null;

    private ListView lvUsuario;
    private LVMainAdapter     mLVMainAdapter;
    public ArrayList<String> mLstString;

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
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Usuario_List.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            usuarioDAO.Usuario_PopulateList();
            mLVMainAdapter = new LVMainAdapter(Activity_Usuario_List.this, 0, mLstString);
            mLVMainAdapter.notifyDataSetChanged();
            lvUsuario.setAdapter(mLVMainAdapter);

        }catch (Exception ex){
            Toast.makeText(Activity_Usuario_List.this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public boolean onQueryTextChange(String s) {
        //texto.setText("Escribiendo texto...\n\n" + s);
        Toast.makeText(Activity_Usuario_List.this, "No se pudo copiar la BD", Toast.LENGTH_SHORT).show();
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
            mLVMainAdapter.getFilter().filter(s.toString());
            mLVMainAdapter.notifyDataSetChanged();
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
                //intent.putExtra("N1",tvMainTextA.getText().toString());
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
                usuarioDAO.Usuario_PopulateList();
                mLVMainAdapter = new LVMainAdapter(Activity_Usuario_List.this, 0, usuarioDAO.mLstString);
                mLVMainAdapter.notifyDataSetChanged();
                lvUsuario.setAdapter(mLVMainAdapter);
            } else {
                //tvMainTextA.setText("Canceló");
                //tvMainTextB.setText("El usuario");
            }
        }
    }
}

/*
public class Activity_Usuario_List extends ActionBarActivity {
    private final int RequestCode = 1;
    private boolean isAdd = false;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    // private Mesa mesa=null;

    private ListView lvUsuario;
    private UsuarioAdapter usuarioAdapter=null;
    public ArrayList<String> mLstString;

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

        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Usuario_List.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            usuarioDAO.Usuario_PopulateList();
            usuarioAdapter = new UsuarioAdapter(Activity_Usuario_List.this, 0, usuarioDAO.mLstString);
            usuarioAdapter.notifyDataSetChanged();
            lvUsuario.setAdapter(usuarioAdapter);

        }catch (Exception ex){
            Toast.makeText(Activity_Usuario_List.this, ex.toString(), Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(Activity_Usuario_List.this, Activity_Usuario.class);
                //intent.putExtra("N1",tvMainTextA.getText().toString());
                startActivityForResult(intent,1);
                return true;
           /* case R.id.ic_action_save:
                isAdd = false;
                invalidateOptionsMenu();
                return true;
                */
/*
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
                usuarioDAO.Usuario_PopulateList();
                usuarioAdapter = new UsuarioAdapter(Activity_Usuario_List.this, 0, usuarioDAO.mLstString);
                usuarioAdapter.notifyDataSetChanged();
                lvUsuario.setAdapter(usuarioAdapter);
            } else {
                //tvMainTextA.setText("Canceló");
                //tvMainTextB.setText("El usuario");
            }
        }
    }
}
*/