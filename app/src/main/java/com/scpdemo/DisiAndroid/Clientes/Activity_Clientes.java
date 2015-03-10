package com.scpdemo.DisiAndroid.Clientes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.DAO.ClienteDAO;
import com.scpdemo.DisiAndroid.Entities.Cliente;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.Util.ConfirmacionDialogfragment;
import com.scpdemo.DisiAndroid.Util.Funciones;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by rgalvez on 03/03/2015.
 */
public class Activity_Clientes extends ActionBarActivity implements ConfirmacionDialogfragment.ConfirmacionDialogfragmentListener{
    private Context context;

    private EditText cl_etCodigo,cl_etRUC,cl_etNombre;
    private CheckBox cl_chkEstado;

    private ClienteDAO clienteDAO = new ClienteDAO();
    private Cliente cliente=null;
    private Funciones Validar=new Funciones();

    private Integer Accion=0;
    private Integer Inactivo=0;
    private Integer MAX_VALOR=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        cl_etCodigo = (EditText) findViewById(R.id.cl_etCodigo);
        cl_etRUC = (EditText) findViewById(R.id.cl_etRUC);
        cl_etNombre = (EditText) findViewById(R.id.cl_etNombre);
        cl_chkEstado = (CheckBox) findViewById(R.id.cl_chkEstado);
        cl_etRUC.requestFocus();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.cliente_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        Integer ID=Integer.valueOf(getIntent().getExtras().getString("s_cliecod"));

        if (ID>0){
            Accion=1;
        }

        if (Accion==0){
            MAX_VALOR=clienteDAO.CLIENTE_MAX()+1;
            cl_etCodigo.setText(String.valueOf(MAX_VALOR));

        }  else{
            cl_etCodigo.setText(getIntent().getExtras().getString("s_cliecod"));
            cl_etRUC.setText(getIntent().getExtras().getString("s_clieruc"));
            cl_etNombre.setText(getIntent().getExtras().getString("s_clienom"));
        }

        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Clientes.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        }catch (Exception ex){
            Toast.makeText(Activity_Clientes.this,R.string.mensaje_conexion, Toast.LENGTH_SHORT).show();
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
                        confirmacionDialogfragment.setmConfirmacionDialogfragmentListener(Activity_Clientes.this);
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
        if(cl_etCodigo.getText().toString().equals("")){
            Toast toastCodigo = Toast.makeText(getApplicationContext(),R.string.cliente_val_Codigo,Toast.LENGTH_SHORT);
            toastCodigo.show();
            vibrator.vibrate(200);
            cl_etCodigo.requestFocus();
            return false;
        }
        /*
        if(cl_etRUC.getText().toString().equals("")){
            Toast toastRUC = Toast.makeText(getApplicationContext(),R.string.cliente_val_RUC,Toast.LENGTH_SHORT);
            toastRUC.show();
            vibrator.vibrate(200);
            cl_etRUC.requestFocus();
            return false;
        }
        */

        if(cl_etNombre.getText().toString().equals("")){
            Toast toastNombre = Toast.makeText(getApplicationContext(),R.string.cliente_val_Nombre,Toast.LENGTH_SHORT);
            toastNombre.show();
            vibrator.vibrate(200);
            cl_etNombre.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onConfirmacionSI() {
        if (OKData() == true) {

            if (cl_chkEstado.isChecked()) {
                Inactivo = 1;
            }
            if (!cl_chkEstado.isChecked()) {
                Inactivo = 0;
            }

            //GUARDAMOS
            if (Accion == 0) {
                //Obtenemos la fecha y hora actual
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formattedDate = df.format(c.getTime());
                //procedmiento para insertar
                Intent intent = new Intent();
                cliente=new Cliente();
                cliente.setCLIECOD(Integer.valueOf(cl_etCodigo.getText().toString()));
                cliente.setCLIERUC(cl_etRUC.getText().toString());
                cliente.setCLIENOM(cl_etNombre.getText().toString());
                cliente.setCLIEFECR(String.valueOf(formattedDate));
                cliente.setCLIEINA(Integer.valueOf(Inactivo));

                clienteDAO.insertCliente(cliente);
                setResult(RESULT_OK, intent);
                finish();


            }

            //ACTUALIZAMOS
            if (Accion == 1) {
                Intent intent = new Intent();
                cliente = new Cliente();
                cliente.setCLIECOD(Integer.valueOf(cl_etCodigo.getText().toString()));
                cliente.setCLIERUC(cl_etRUC.getText().toString());
                cliente.setCLIENOM(cl_etNombre.getText().toString());
                //cliente.setCLIEINA(Integer.valueOf(Inactivo));
                clienteDAO.updateMesa(cliente);

                setResult(RESULT_OK, intent);
                finish();
            }
        }

        //ELIMINAR
        if (Accion == 3) {
            cliente = new Cliente();
            cliente.setCLIECOD(Integer.valueOf(cl_etCodigo.getText().toString()));
            clienteDAO.deleteMesa(cliente);
            Toast.makeText(context, "Resultado: " , Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onConfirmacionNO() {

    }
}