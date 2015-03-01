package com.scpdemo.DisiAndroid.Main;

/**
 * Created by Renan on 12/02/2015.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import com.scpdemo.DisiAndroid.R;


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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.login_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);


        txtUsuarioNombre  = (EditText)findViewById(R.id.txtUsuarioNombre);
        txtUsuarioPassword = (EditText)findViewById(R.id.txtUsuarioPassword);
        btnUsuarioLogin = (Button)findViewById(R.id.btnUsuarioLogin);
        chkGuardar = (CheckBox)findViewById(R.id.chkGuardar);


        txtUsuarioNombre.setText(getSharedPreferences(getPackageName(), MODE_PRIVATE).getString("Usuario",""));
        txtUsuarioPassword.setText(getSharedPreferences(getPackageName(), MODE_PRIVATE).getString("Clave",""));

        // LOGIN
        btnUsuarioLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EXTRAEMOS VALORES DE EDITTEXT
                String sUser = txtUsuarioNombre.getText().toString();
                String sPwd =txtUsuarioPassword.getText().toString();

                //Lineas para ocultar el teclado virtual (Hide keyboard)
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtUsuarioPassword.getWindowToken(), 0);

                //VALIDANDO DATOS INGRESADOS
                if (OKData() == true) {
                    getSharedPreferences(getPackageName(), MODE_PRIVATE).edit()
                            .putString("Usuario", sUser)
                            .commit();

                    if(chkGuardar.isChecked()) {
                    getSharedPreferences(getPackageName(), MODE_PRIVATE).edit()
                            .putString("Clave", sPwd)
                            .commit();
                    }

                    if(!chkGuardar.isChecked()) {
                        getSharedPreferences(getPackageName(), MODE_PRIVATE).edit()
                                .putString("Clave", "")
                                .commit();
                    }
                    Intent intent = new Intent(Activity_Login.this, Activity_Main.class);
                    startActivityForResult(intent, 0);
                }
            }
        });
    }

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

    private class DownloadTask2 extends AsyncTask<String, Void, Object> {
        protected Integer doInBackground(String... args) {
            //BL ws=new BL();
            //Se invoca nuestro metodo
            //res=ws.PasswordOK(txtUsuarioNombre.getText().toString(), txtUsuarioPassword.getText().toString());
            return 1;
        }

        protected void onPostExecute(Object result) {
            //Se elimina la pantalla de por favor espere.
            pd.dismiss();
            //Se muestra mensaje con la respuesta del servicio web
            Toast.makeText(context,"Resultado: "+res,Toast.LENGTH_LONG).show();
            super.onPostExecute(result);
        }


    }
}
