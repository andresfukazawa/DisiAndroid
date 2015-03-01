package com.scpdemo.DisiAndroid.Usuario;

/**
 * Created by Renan on 12/02/2015.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.DAO.UsuarioDAO;
import com.scpdemo.DisiAndroid.Entities.Usuario;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.Util.Funciones;

public class Activity_Usuario extends ActionBarActivity {
    private Context context;

    EditText us_etCodigo, us_etNombre,us_etemail,us_etPassword;
    CheckBox us_chkEstado;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuario=null;
    private Funciones Validar=new Funciones();

    private Integer Accion=0;
    private Integer Inactivo=0;
    private Integer MAX_VALOR=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.activity_usuario);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.usuario_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        us_etCodigo=(EditText)findViewById(R.id.us_etcodigo);
        us_etNombre=(EditText)findViewById(R.id.us_etNombre);
        us_etemail=(EditText)findViewById(R.id.us_etEmail);
        us_etPassword=(EditText)findViewById(R.id.us_etPassword);
        us_chkEstado=(CheckBox)findViewById(R.id.us_chkEstado);


        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Usuario.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            MAX_VALOR=usuarioDAO.USUARIO_MAX()+1;
            us_etCodigo.setText(String.valueOf(MAX_VALOR));

        }catch (Exception ex){
            Toast.makeText(Activity_Usuario.this, "No se pudo copiar la BD", Toast.LENGTH_SHORT).show();
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
            if (us_chkEstado.isChecked()) {
                Inactivo = 1;
            }
            if (!us_chkEstado.isChecked()) {
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
                            usuario=new Usuario();
                            usuario.setUSUACOD(Integer.valueOf(us_etCodigo.getText().toString()));
                            usuario.setUSUADES(us_etNombre.getText().toString());
                            usuario.setUSUACLAV(us_etPassword.getText().toString());
                            usuario.setUSUAMAIL(us_etemail.getText().toString());
                            usuario.setUSUAINA(Integer.valueOf(Inactivo));
                            usuarioDAO.insertUsuario(usuario);
                            setResult(RESULT_OK, intent);
                            finish();
                            //}

                        }

                        //ACTUALIZAMOS
                        if (Accion == 1) {
                            Intent intent = new Intent();
                            usuario = new Usuario();
                            usuario.setUSUACOD(Integer.valueOf(us_etCodigo.getText().toString()));
                            usuario.setUSUADES(us_etNombre.getText().toString());
                            usuario.setUSUACLAV(us_etPassword.getText().toString());
                            usuario.setUSUAMAIL(us_etemail.getText().toString());

                            usuario.setUSUAINA(Integer.valueOf(Inactivo));
                            usuarioDAO.updateUsuario(usuario);

                            setResult(RESULT_OK, intent);
                            finish();
                        }

                        //ELIMINAR
                        if (Accion == 3) {
                            usuario = new Usuario();
                            usuario.setUSUACOD(Integer.valueOf(us_etCodigo.getText().toString()));
                            usuarioDAO.deleteUsuario(usuario);

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
        Toast toastClave = Toast.makeText(getApplicationContext(),"Ingrese el nombre de usuario",Toast.LENGTH_SHORT);
        Toast toastEmail = Toast.makeText(getApplicationContext(),"Ingrese su password",Toast.LENGTH_SHORT);


        if(us_etCodigo.getText().toString().equals("")){
            toastCodigo.show();
            vibrator.vibrate(200);
            us_etCodigo.requestFocus();
            return false;
        }


        if(us_etNombre.getText().toString().equals("")){
            toastNombre.show();
            vibrator.vibrate(200);
            us_etNombre.requestFocus();
            return false;
        }

        if(us_etemail.getText().toString().equals("")){
            toastEmail.show();
            vibrator.vibrate(200);
            us_etemail.requestFocus();
            return false;
        }

        if(us_etPassword.getText().toString().equals("")){
            toastClave.show();
            vibrator.vibrate(200);
            us_etPassword.requestFocus();
            return false;
        }

        return true;
    }

}