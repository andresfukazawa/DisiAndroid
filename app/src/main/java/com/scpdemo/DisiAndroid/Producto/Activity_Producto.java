package com.scpdemo.DisiAndroid.Producto;



import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.Toast;

import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.DAO.ProductoDAO;

import android.content.Context;

import com.scpdemo.DisiAndroid.Entities.Producto;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.Util.ConfirmacionDialogfragment;
import com.scpdemo.DisiAndroid.Util.Funciones;


/**
 * Created by Renan on 24/02/2015.
 */
public class Activity_Producto extends ActionBarActivity implements ConfirmacionDialogfragment.ConfirmacionDialogfragmentListener {
    private Context context;

    private EditText pr_etCodigo,pr_etNombre,pr_etTipo,pr_etMoneda,pr_etPrecio,pr_etDescrip;
    private CheckBox pr_chkEstado;

    private ProductoDAO productoDAO = new ProductoDAO();
    private Producto producto=null;
    private Funciones Validar=new Funciones();

    private Integer Accion=0;
    private Integer Inactivo=0;
    private Integer MAX_VALOR=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);
        pr_etCodigo = (EditText) findViewById(R.id.pr_etCodigo);
        pr_etNombre = (EditText) findViewById(R.id.pr_etNombre);
        pr_etTipo = (EditText) findViewById(R.id.pr_etTipo);
        pr_etMoneda = (EditText) findViewById(R.id.pr_etMoneda);
        pr_etTipo = (EditText) findViewById(R.id.pr_etTipo);
        pr_etPrecio = (EditText) findViewById(R.id.pr_etPrecio);
        pr_etDescrip = (EditText) findViewById(R.id.pr_etDescrip);
        pr_chkEstado = (CheckBox) findViewById(R.id.pr_chkEstado);

        pr_etNombre.requestFocus();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.mesa_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Producto.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            Integer ID=Integer.valueOf(getIntent().getExtras().getString("s_productocod"));
            if (ID>0){
                Accion=1;
            }

            if (Accion==0){
                MAX_VALOR=productoDAO.PRODUCTO_MAX()+1;
                pr_etCodigo.setText(String.valueOf(MAX_VALOR));

            }  else{
                pr_etCodigo.setText(getIntent().getExtras().getString("s_productocod"));
                pr_etNombre.setText(getIntent().getExtras().getString("s_productonom"));
                pr_etTipo.setText(getIntent().getExtras().getString("s_productotip"));
                pr_etMoneda.setText(getIntent().getExtras().getString("s_productomon"));
                pr_etDescrip.setText(getIntent().getExtras().getString("s_productodes"));
                pr_etPrecio.setText(getIntent().getExtras().getString("s_productopre"));
                pr_chkEstado.setChecked(Boolean.valueOf(getIntent().getExtras().getString("s_productoina")));
            }

        }catch (Exception ex){
            Toast.makeText(Activity_Producto.this, R.string.mensaje_conexion, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_producto_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try{

            switch (item.getItemId()) {
                case R.id.ic_action_save:
                    ConfirmacionDialogfragment confirmacionDialogfragment = new ConfirmacionDialogfragment();
                    confirmacionDialogfragment.setmConfirmacionDialogfragmentListener(Activity_Producto.this);
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

        if(pr_etCodigo.getText().toString().equals("")){
            Toast toastCodigo = Toast.makeText(getApplicationContext(),R.string.mesa_val_codigo,Toast.LENGTH_SHORT);
            toastCodigo.show();
            vibrator.vibrate(200);
            pr_etCodigo.requestFocus();
            return false;
        }
        if(pr_etNombre.getText().toString().equals("")){
            Toast toastNombre = Toast.makeText(getApplicationContext(),R.string.mesa_val_nombre,Toast.LENGTH_SHORT);
            toastNombre.show();
            vibrator.vibrate(200);
            pr_etNombre.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onConfirmacionSI() {
        if (OKData() == true) {
            if (pr_chkEstado.isChecked()) {
                Inactivo = 1;
            }
            if (!pr_chkEstado.isChecked()) {
                Inactivo = 0;
            }

            //GUARDAMOS
            if (Accion == 0) {
                Intent intent = new Intent();
                producto=new Producto();
                producto.setPRODCOD(Integer.valueOf(pr_etCodigo.getText().toString()));
                producto.setPRODNOM(pr_etNombre.getText().toString());
                producto.setPRODMON(pr_etMoneda.getText().toString());
                producto.setPRODTIP(pr_etTipo.getText().toString());
                producto.setPRODDES(pr_etDescrip.getText().toString());
                producto.setPRODINA(Integer.valueOf(Inactivo));
                producto.setPRODPRE(Double.valueOf(pr_etPrecio.getText().toString()));

                productoDAO.insertProducto(producto);
                setResult(RESULT_OK, intent);
                finish();
            }

            //ACTUALIZAMOS
            if (Accion == 1) {
                Intent intent = new Intent();
                producto=new Producto();
                producto.setPRODCOD(Integer.valueOf(pr_etCodigo.getText().toString()));
                producto.setPRODNOM(pr_etNombre.getText().toString());
                producto.setPRODMON(pr_etMoneda.getText().toString());
                producto.setPRODTIP(pr_etTipo.getText().toString());
                producto.setPRODDES(pr_etDescrip.getText().toString());
                producto.setPRODINA(Integer.valueOf(Inactivo));
                producto.setPRODPRE(Double.valueOf(pr_etPrecio.getText().toString()));

                productoDAO.updateProducto(producto);

                setResult(RESULT_OK, intent);
                finish();
            }
        }
        //ELIMINAR
        if (Accion == 3) {
            producto=new Producto();
            producto.setPRODCOD(Integer.valueOf(pr_etCodigo.getText().toString()));
            productoDAO.deleteProducto(producto);


            Toast.makeText(context, "Resultado: " , Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onConfirmacionNO() {
        Toast.makeText(Activity_Producto.this,"Preciono no salir",Toast.LENGTH_SHORT).show();
    }
}