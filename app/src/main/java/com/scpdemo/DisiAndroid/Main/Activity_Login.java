package com.scpdemo.DisiAndroid.Main;

/**
 * Created by Renan on 12/02/2015.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.R;

import java.security.MessageDigest;


public class Activity_Login extends ActionBarActivity {
    //VARIABLES
    private ProgressDialog pd;
    private Context context;
    EditText txtUsuarioNombre;
    EditText txtUsuarioPassword;
    Button  btnUsuarioLogin;
    CheckBox chkGuardar;

    //private Boolean res;
    private String res;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.login_title);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);


        txtUsuarioNombre  = (EditText)findViewById(R.id.txtUsuarioNombre);
        txtUsuarioPassword = (EditText)findViewById(R.id.txtUsuarioPassword);
        btnUsuarioLogin = (Button)findViewById(R.id.btnUsuarioLogin);

        txtUsuarioNombre.setText(getSharedPreferences(getPackageName(), MODE_PRIVATE).getString("Usuario",""));
        txtUsuarioPassword.setText(getSharedPreferences(getPackageName(), MODE_PRIVATE).getString("Clave",""));
        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Login.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            Toast.makeText(Activity_Login.this, "No se pudo copiar la BD", Toast.LENGTH_SHORT).show();
        }
        btnUsuarioLogin.setOnClickListener(btnUsuarioLoginOnClick);
    }

    View.OnClickListener btnUsuarioLoginOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Cursor cursor   = null;
            Boolean loginOk = false;

            if (OKData()==false){return;}

            try{
                //Calcular el MD5 de la contrasena
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(txtUsuarioPassword.getText().toString().getBytes());
                byte byteData[] = messageDigest.digest();

                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < byteData.length; i++) {
                    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }
                String pwdhash = sb.toString();

                cursor = DataBaseHelper.myDataBase.query("USUARIOS", null, "USUADES=? and USUACLAV=?", new String[]{txtUsuarioNombre.getText().toString(), pwdhash}, null, null, null);
                if(cursor.getCount() == 1) {
                    loginOk = true;
                }else{
                    Toast.makeText(Activity_Login.this,R.string.usuario_val_usuario_clave, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (cursor != null)
                    cursor.close();
            }

            if (loginOk) {
                SharedPreferences.Editor editor = getSharedPreferences(getPackageName(), MODE_PRIVATE).edit();
                editor.putString("Usuario", txtUsuarioNombre.getText().toString());
                editor.commit();

                Intent intent = new Intent(Activity_Login.this, Activity_Main.class);
//                startActivityForResult(intent, 0);
                startActivity(intent);
            } else {
                Toast.makeText(Activity_Login.this, R.string.login_fallido, Toast.LENGTH_SHORT).show();
                txtUsuarioPassword.setText("");
                chkGuardar.setChecked(false);
            }

        }
    };

    public Boolean OKData(){
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        Toast toastUsuario = Toast.makeText(getApplicationContext(),"Ingrese el nombre de usuario",Toast.LENGTH_SHORT);
        Toast toastPassword = Toast.makeText(getApplicationContext(),"Ingrese su password",Toast.LENGTH_SHORT);

        if(txtUsuarioNombre.getText().toString().equals("")){
            toastUsuario.show();
            vibrator.vibrate(200);
            return false;
        }
        if(txtUsuarioPassword.getText().toString().equals("")){
            toastPassword.show();
            vibrator.vibrate(200);
            return false;
        }
        return true;
    }

}
