package com.scpdemo.DisiAndroid.Pedido;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.DAO.MesaDAO;
import com.scpdemo.DisiAndroid.DAO.PedidoDetalleDAO;
import com.scpdemo.DisiAndroid.Entities.Mesa;
import com.scpdemo.DisiAndroid.Entities.Pedido_Detalle;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.Util.ConfirmacionDialogfragment;
import com.scpdemo.DisiAndroid.Util.Funciones;

/**
 * Created by Renan on 12/03/2015.
 */
public class Activity_Update_Pedido extends ActionBarActivity implements ConfirmacionDialogfragment.ConfirmacionDialogfragmentListener {
    private Context context;
    private EditText etpedido,etMesa,etcantidad;
    private Button btGuardar,btEliminar;

    private PedidoDetalleDAO pedidoDetalleDAO = new PedidoDetalleDAO();
    private Pedido_Detalle pedido_detalle=null;
    private Funciones Validar=new Funciones();
    private Integer Accion=0;
    private Integer ITEM=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiti_update_pedido);

        etpedido = (EditText) findViewById(R.id.etpedido);
        etMesa = (EditText) findViewById(R.id.etMesa);
        etcantidad = (EditText) findViewById(R.id.etcantidad);

        etcantidad.requestFocus();
        btGuardar = (Button) findViewById(R.id.btGuardar);
        btEliminar = (Button) findViewById(R.id.btEliminar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.mesa_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        btGuardar.setOnClickListener(OnClickListenerbtGuardar);
        btEliminar.setOnClickListener(OnClickListenerbtEliminar);
        try{

            etpedido.setText(getIntent().getExtras().getString("sPedido_Nro"));
            etMesa.setText(getIntent().getExtras().getString("sPedido_Mesa"));

            ITEM=Integer.valueOf(getIntent().getExtras().getString("sPedido_Item"));
            etcantidad.setText("0");

            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Update_Pedido.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

        }catch (Exception ex){
            Toast.makeText(Activity_Update_Pedido.this, R.string.mensaje_conexion, Toast.LENGTH_SHORT).show();
        }
    }

    View.OnClickListener OnClickListenerbtGuardar=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                Accion=2;
            if (OKData() == false) {return;}

            ConfirmacionDialogfragment confirmacionDialogfragment = new ConfirmacionDialogfragment();
            confirmacionDialogfragment.setmConfirmacionDialogfragmentListener(Activity_Update_Pedido.this);
            confirmacionDialogfragment.show(getSupportFragmentManager(), confirmacionDialogfragment.TAG);
        }
            catch(Exception ex){
                Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show();
            }
    } };

        View.OnClickListener OnClickListenerbtEliminar=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Accion=3;
                    ConfirmacionDialogfragment confirmacionDialogfragment = new ConfirmacionDialogfragment();
                    confirmacionDialogfragment.setmConfirmacionDialogfragmentListener(Activity_Update_Pedido.this);
                    confirmacionDialogfragment.show(getSupportFragmentManager(), confirmacionDialogfragment.TAG);
                }
                catch(Exception ex){
                    Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show();
                }
            }
        };

    public Boolean OKData(){
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        if(etcantidad.getText().toString().trim().length()==0){
            Toast toastCodigo = Toast.makeText(getApplicationContext(),"Ingrese cantidad",Toast.LENGTH_SHORT);
            toastCodigo.show();
            vibrator.vibrate(200);
            etcantidad.requestFocus();
            return false;
        }
        if(Integer.valueOf(etcantidad.getText().toString())<=0){
            Toast toastCodigo = Toast.makeText(getApplicationContext(),"Cantidad no valida",Toast.LENGTH_SHORT);
            toastCodigo.show();
            vibrator.vibrate(200);
            etcantidad.requestFocus();
            return false;
        }


        return true;
    }

    @Override
    public void onConfirmacionSI() {
        if (Accion == 2) {
            //ACTUALIZAMOS
            Intent intent = new Intent();
            pedido_detalle = new Pedido_Detalle();
            pedido_detalle.setDETPITE(Integer.valueOf(ITEM));
            pedido_detalle.setDETPCAN(Double.valueOf(etcantidad.getText().toString()));
            pedidoDetalleDAO.updatePedidoDetalle(pedido_detalle);
            intent.putExtra("sPedido_Nro",String.valueOf(etpedido.getText()));
            intent.putExtra("sPedido_Mesa",String.valueOf(etMesa.getText()));
            setResult(RESULT_OK, intent);
            finish();
        }
        //ELIMINAR
        if (Accion == 3) {
            Intent intent = new Intent();
            pedido_detalle = new Pedido_Detalle();
            pedido_detalle.setDETPITE(Integer.valueOf(ITEM));
            pedidoDetalleDAO.deletePedidoDetalle(pedido_detalle);
            intent.putExtra("sPedido_Nro",String.valueOf(etpedido.getText()));
            intent.putExtra("sPedido_Mesa",String.valueOf(etMesa.getText()));
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onConfirmacionNO() {
       // Toast.makeText(Activity_Update_Pedido.this,"Preciono no salir",Toast.LENGTH_SHORT).show();
    }
}