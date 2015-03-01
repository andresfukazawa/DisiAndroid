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
import com.scpdemo.DisiAndroid.Util.Funciones;

/**
 * Created by Renan on 24/02/2015.
 */
public class Activity_Mesa extends ActionBarActivity {
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

       //String N1 = getIntent().getExtras().getString("N1");
       //Toast.makeText(context, "CARGO: ", Toast.LENGTH_LONG).show();

        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Mesa.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            MAX_VALOR=mesaDAO.MESA_MAX()+1;
            me_etCodigo.setText(String.valueOf(MAX_VALOR));

        }catch (Exception ex){
            Toast.makeText(Activity_Mesa.this, "No se pudo copiar la BD", Toast.LENGTH_SHORT).show();
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
            if (me_chkEstado.isChecked()) {
                Inactivo = 1;
            }
            if (!me_chkEstado.isChecked()) {
                Inactivo = 0;
            }
        if (OKData() == true) {
            switch (item.getItemId()) {
                case R.id.ic_action_save:
                   /*
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                    */
                    //GUARDAMOS
                    if (Accion == 0) {
                        //if (Validar.Confirma_Guardar(Activity_Mesa.this)==1){
                           Intent intent = new Intent();
                            mesa=new Mesa();
                            mesa.setMESACOD(Integer.valueOf(me_etCodigo.getText().toString()));
                            mesa.setMESADES(me_etNombre.getText().toString());
                            mesa.setMESAINA(Integer.valueOf(Inactivo));
                            mesaDAO.insertMesa(mesa);
                            setResult(RESULT_OK, intent);
                            finish();
                        //}

                      /*
                        AlertDialog.Builder builder =new AlertDialog.Builder(context);
                        builder.setMessage("¿Confirma la acción seleccionada?")
                                .setTitle("Confirmacion")
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent();
                                        mesa=new Mesa();
                                        mesa.setMESACOD(Integer.valueOf(me_etCodigo.getText().toString()));
                                        mesa.setMESADES(me_etNombre.getText().toString());
                                        mesa.setMESAINA(Integer.valueOf(Inactivo));
                                        mesaDAO.insertMesa(mesa);
                                        setResult(RESULT_OK, intent);
                                        finish();
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Log.i("Dialogos", "Confirmacion Cancelada.");
                                        dialog.cancel();
                                    }
                                });
                        */
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


                default:
                    return super.onOptionsItemSelected(item);
             }
           }
        }
        catch(Exception ex){
            Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show();
        }
        return true;
    }


    public Boolean OKData(){
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        Toast toastCodigo = Toast.makeText(getApplicationContext(),"Ingrese el nombre de usuario",Toast.LENGTH_SHORT);
        Toast toastNombre = Toast.makeText(getApplicationContext(),"Ingrese su password",Toast.LENGTH_SHORT);

        if(me_etCodigo.getText().toString().equals("")){
            toastCodigo.show();
            vibrator.vibrate(200);
            me_etCodigo.requestFocus();
            return false;
        }
        if(me_etNombre.getText().toString().equals("")){
            toastNombre.show();
            vibrator.vibrate(200);
            me_etNombre.requestFocus();
            return false;
        }
        return true;
    }

}