package com.scpdemo.DisiAndroid.Mesa;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.scpdemo.DisiAndroid.DAO.MesaDAO;
import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.Entities.Mesa;
import android.content.Context;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.Util.ConfirmacionDialogfragment;
import com.scpdemo.DisiAndroid.Util.Funciones;


/**
 * Created by Renan on 24/02/2015.
 */
public class Activity_Mesa extends ActionBarActivity implements ConfirmacionDialogfragment.ConfirmacionDialogfragmentListener {
    private Context context;

    private EditText me_etCodigo,me_etNombre;
    private CheckBox me_chkEstado;

    private MesaDAO mesaDAO = new MesaDAO();
    private Mesa mesa=null;
    private Funciones Validar=new Funciones();

    private Integer Accion=0;
    private Integer Inactivo=0;
    private Integer MAX_VALOR=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);
        me_etCodigo = (EditText) findViewById(R.id.me_etCodigo);
        me_etNombre = (EditText) findViewById(R.id.me_etNombre);
        me_chkEstado = (CheckBox) findViewById(R.id.me_chkEstado);
        me_etNombre.requestFocus();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.mesa_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Mesa.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            Integer ID=Integer.valueOf(getIntent().getExtras().getString("s_mesacod"));
            if (ID>0){
                Accion=1;
            }

            if (Accion==0){
                MAX_VALOR=mesaDAO.MESA_MAX()+1;
                me_etCodigo.setText(String.valueOf(MAX_VALOR));

            }  else{
                me_etCodigo.setText(getIntent().getExtras().getString("s_mesacod"));
                me_etNombre.setText(getIntent().getExtras().getString("s_mesanom"));
                me_chkEstado.setChecked(Boolean.valueOf(getIntent().getExtras().getString("s_mesaina")));
            }

        }catch (Exception ex){
            Toast.makeText(Activity_Mesa.this, R.string.mensaje_conexion, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mesa_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try{

            switch (item.getItemId()) {
                case R.id.ic_action_save:
                    ConfirmacionDialogfragment confirmacionDialogfragment = new ConfirmacionDialogfragment();
                    confirmacionDialogfragment.setmConfirmacionDialogfragmentListener(Activity_Mesa.this);
                    confirmacionDialogfragment.show(getSupportFragmentManager(), confirmacionDialogfragment.TAG);

                default:
                    return super.onOptionsItemSelected(item);
            }

        }
        catch(Exception ex){
            Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show();
        }
        return true;
    }


    public Boolean OKData(){
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        if(me_etCodigo.getText().toString().equals("")){
            Toast toastCodigo = Toast.makeText(getApplicationContext(),R.string.mesa_val_codigo,Toast.LENGTH_SHORT);
            toastCodigo.show();
            vibrator.vibrate(200);
            me_etCodigo.requestFocus();
            return false;
        }
        if(me_etNombre.getText().toString().equals("")){
            Toast toastNombre = Toast.makeText(getApplicationContext(),R.string.mesa_val_nombre,Toast.LENGTH_SHORT);
            toastNombre.show();
            vibrator.vibrate(200);
            me_etNombre.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onConfirmacionSI() {
        if (OKData() == true) {
            if (me_chkEstado.isChecked()) {
                Inactivo = 1;
            }
            if (!me_chkEstado.isChecked()) {
                Inactivo = 0;
            }

            //GUARDAMOS
            if (Accion == 0) {
                Intent intent = new Intent();
                mesa=new Mesa();
                mesa.setMESACOD(Integer.valueOf(me_etCodigo.getText().toString()));
                mesa.setMESADES(me_etNombre.getText().toString());
                mesa.setMESAINA(Integer.valueOf(Inactivo));
                mesaDAO.insertMesa(mesa);
                setResult(RESULT_OK, intent);
                finish();
            }

            //ACTUALIZAMOS
            if (Accion == 1) {
                Intent intent = new Intent();
                mesa = new Mesa();
                mesa.setMESACOD(Integer.valueOf(me_etCodigo.getText().toString()));
                mesa.setMESADES(me_etNombre.getText().toString());
                mesa.setMESAINA(Integer.valueOf(Inactivo));
                mesaDAO.updateMesa(mesa);

                setResult(RESULT_OK, intent);
                finish();
            }
        }
        //ELIMINAR
        if (Accion == 3) {
            mesa = new Mesa();
            mesa.setMESACOD(Integer.valueOf(me_etCodigo.getText().toString()));
            mesaDAO.deleteMesa(mesa);


            Toast.makeText(context, "Resultado: " , Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onConfirmacionNO() {
        Toast.makeText(Activity_Mesa.this,"Preciono no salir",Toast.LENGTH_SHORT).show();
    }
}