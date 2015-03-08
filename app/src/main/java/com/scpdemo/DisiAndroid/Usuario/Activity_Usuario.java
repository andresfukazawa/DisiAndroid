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
import com.scpdemo.DisiAndroid.Util.ConfirmacionDialogfragment;

import java.security.MessageDigest;


public class Activity_Usuario extends ActionBarActivity  implements ConfirmacionDialogfragment.ConfirmacionDialogfragmentListener{
    private Context context;

    EditText us_etCodigo, us_etNombre,us_etemail,us_etPassword;
    CheckBox us_chkEstado;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuario=null;


    private Integer Accion=0;
    private Integer Inactivo=0;
    private Integer MAX_VALOR=0;
    private Integer AlertDialog=0;


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
            us_etCodigo.requestFocus();
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Usuario.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            Integer ID=Integer.valueOf(getIntent().getExtras().getString("s_usuacod"));
            if (ID>0){
                Accion=1;
            }

            if (Accion==0){
                MAX_VALOR=usuarioDAO.USUARIO_MAX()+1;
                us_etCodigo.setText(String.valueOf(MAX_VALOR));

            }  else{
                us_etCodigo.setText(getIntent().getExtras().getString("s_usuacod"));
                us_etNombre.setText(getIntent().getExtras().getString("s_usuanom"));
                us_etPassword.setText(getIntent().getExtras().getString("s_usuacla"));
                us_etemail.setText(getIntent().getExtras().getString("s_usuamai"));
                us_chkEstado.setChecked(Boolean.valueOf(getIntent().getExtras().getString("s_usuaina")));
            }

        }catch (Exception ex){
            Toast.makeText(Activity_Usuario.this, R.string.mensaje_conexion, Toast.LENGTH_SHORT).show();
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
                    confirmacionDialogfragment.setmConfirmacionDialogfragmentListener(Activity_Usuario.this);
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
        if(us_etCodigo.getText().toString().equals("")){
            Toast toastCodigo = Toast.makeText(getApplicationContext(),R.string.usuario_val_codigo,Toast.LENGTH_SHORT);
            toastCodigo.show();
            vibrator.vibrate(200);
            us_etCodigo.requestFocus();
            return false;
        }

        if(us_etNombre.getText().toString().equals("")){
            Toast toastNombre = Toast.makeText(getApplicationContext(),R.string.usuario_val_nombre,Toast.LENGTH_SHORT);
            toastNombre.show();
            vibrator.vibrate(200);
            us_etNombre.requestFocus();
            return false;
        }

        if(us_etemail.getText().toString().equals("")){
            Toast toastEmail = Toast.makeText(getApplicationContext(),R.string.usuario_val_email,Toast.LENGTH_SHORT);
            toastEmail.show();
            vibrator.vibrate(200);
            us_etemail.requestFocus();
            return false;
        }

        if(us_etPassword.getText().toString().equals("")){
            Toast toastClave = Toast.makeText(getApplicationContext(),R.string.usuario_val_clave,Toast.LENGTH_SHORT);
            toastClave.show();
            vibrator.vibrate(200);
            us_etPassword.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void onConfirmacionSI() {
        if (OKData() == true) {
            if (us_chkEstado.isChecked()) {
                Inactivo = 1;
            }

            if (!us_chkEstado.isChecked()) {
                Inactivo = 0;
            }

            //GUARDAMOS
            if (Accion == 0) {
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
    }

    @Override
    public void onConfirmacionNO() {

    }
}