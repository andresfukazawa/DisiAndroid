package com.scpdemo.DisiAndroid.Pedido;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;
import com.scpdemo.DisiAndroid.Adapter.MesaAdapter;
import com.scpdemo.DisiAndroid.Adapter.PedidoDetalleAdapter;
import com.scpdemo.DisiAndroid.Adapter.ProductoAdapterSearch;
import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.DAO.MesaDAO;
import com.scpdemo.DisiAndroid.DAO.PedidoDetalleDAO;
import com.scpdemo.DisiAndroid.DAO.ProductoDAO;
import com.scpdemo.DisiAndroid.Entities.Mesa;
import com.scpdemo.DisiAndroid.Entities.Pedido_Detalle;
import com.scpdemo.DisiAndroid.Mesa.Activity_Mesa;
import com.scpdemo.DisiAndroid.R;

/**
 * Created by Renan on 11/03/2015.
 */
public class Activity_Pedidos extends ActionBarActivity {
    private final int RequestCode = 1;
    private TextView tvMoso;
    private ListView lvpedidoDetalle;
    private Button btnuevo,btAgregar;
    private ImageButton btBucarPedido,btBuscarMesa;
    private EditText etmesa,etpedido;
    private Integer PED_EXIST=0;

    private PedidoDetalleDAO pedidoDetalleDAO = new PedidoDetalleDAO();
    private Pedido_Detalle pedido_detalle=null;
    private PedidoDetalleAdapter pedidoDetalleAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevopedido);

        tvMoso=(TextView)findViewById(R.id.tvmoso);
        btnuevo = (Button) findViewById(R.id.btNuevo);
        btAgregar = (Button) findViewById(R.id.btAgregar);

        btBucarPedido = (ImageButton) findViewById(R.id.btBucarPedido);
        btBuscarMesa = (ImageButton) findViewById(R.id.btBuscarMesa);

        lvpedidoDetalle=(ListView)findViewById(R.id.lvPedidos);

        etmesa=(EditText)findViewById(R.id.etmesa);
        etpedido=(EditText)findViewById(R.id.etpedido);

        etmesa.setText("0");
        etpedido.setText("0");

         btnuevo.setOnClickListener(OnClickListenerbtbtnuevo);
         btAgregar.setOnClickListener(OnClickListenerbtbtAgregar);

        btBucarPedido.setOnClickListener(OnClickListenerbtBucarPedido);
        btBuscarMesa.setOnClickListener(OnClickListenerbtbtBuscarMesa);

        lvpedidoDetalle.setOnItemClickListener(lvPedidoDetalleOnItemClickListener);

        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Pedidos.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            pedidoDetalleDAO.PedidoDetalle_Populate_byPedido_List(0,1);
            pedidoDetalleAdapter = new PedidoDetalleAdapter(Activity_Pedidos.this, 0, pedidoDetalleDAO.lstPedido_Detalle);
            pedidoDetalleAdapter.notifyDataSetChanged();
            lvpedidoDetalle.setAdapter(pedidoDetalleAdapter);

        }catch (Exception ex){
            Toast.makeText(Activity_Pedidos.this, R.string.mensaje_conexion, Toast.LENGTH_SHORT).show();
        }
    }

    AbsListView.OnItemClickListener lvPedidoDetalleOnItemClickListener = new AbsListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Pedido_Detalle pedido_detalle1 = pedidoDetalleAdapter.getItem(position);
            Intent intent=new Intent(Activity_Pedidos.this,Activity_Update_Pedido.class);
            intent.putExtra("sPedido_Nro", String.valueOf(pedidoDetalleAdapter.getItem(position).getDETPPED()));
            intent.putExtra("sPedido_Mesa", String.valueOf(pedidoDetalleAdapter.getItem(position).getDETMESA()));
            intent.putExtra("sPedido_Item",String.valueOf(pedidoDetalleAdapter.getItem(position).getDETPITE()));
            startActivityForResult(intent,1);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCode) {
            if (resultCode == RESULT_OK) {
               etpedido.setText(data.getExtras().getString("sPedido_Nro"));
               etmesa.setText(data.getExtras().getString("sPedido_Mesa"));
                Integer Pedido_Nro=Integer.valueOf(data.getExtras().getString("sPedido_Nro"));

                pedidoDetalleDAO.PedidoDetalle_Populate_byPedido_List(Pedido_Nro,1);
                pedidoDetalleAdapter = new PedidoDetalleAdapter(Activity_Pedidos.this, 0, pedidoDetalleDAO.lstPedido_Detalle);
                pedidoDetalleAdapter.notifyDataSetChanged();
                lvpedidoDetalle.setAdapter(pedidoDetalleAdapter);

            } else {
                //tvMainTextA.setText("Canceló");

            }
        }
    }

    View.OnClickListener OnClickListenerbtBucarPedido=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            if(etpedido.getText().toString().trim().length()==0){
                Toast toastCodigo = Toast.makeText(getApplicationContext(),"Ingrese Número de pedido",Toast.LENGTH_SHORT);
                toastCodigo.show();
                vibrator.vibrate(200);
                etpedido.requestFocus();
                return;
            }

            Integer Pedido_Nro=Integer.valueOf(String.valueOf(etpedido.getText()));
            pedidoDetalleDAO.PedidoDetalle_Populate_byPedido_List(Pedido_Nro,1);
            pedidoDetalleAdapter = new PedidoDetalleAdapter(Activity_Pedidos.this, 0, pedidoDetalleDAO.lstPedido_Detalle);
            pedidoDetalleAdapter.notifyDataSetChanged();
            lvpedidoDetalle.setAdapter(pedidoDetalleAdapter);

            //Ocultamos nel teclado
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etpedido.getWindowToken(), 0);
        }
    };

    View.OnClickListener OnClickListenerbtbtBuscarMesa=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            if(etmesa.getText().toString().trim().length()==0){
                Toast toastMesa = Toast.makeText(getApplicationContext(),"Ingrese Número de mesa",Toast.LENGTH_SHORT);
                toastMesa.show();
                vibrator.vibrate(200);
                etmesa.requestFocus();
                return;
            }

            Integer Pedido_mesa=Integer.valueOf(String.valueOf(etmesa.getText()));
            pedidoDetalleDAO.PedidoDetalle_Populate_byPedido_List(Pedido_mesa,2);
            pedidoDetalleAdapter = new PedidoDetalleAdapter(Activity_Pedidos.this, 0, pedidoDetalleDAO.lstPedido_Detalle);
            pedidoDetalleAdapter.notifyDataSetChanged();
            lvpedidoDetalle.setAdapter(pedidoDetalleAdapter);

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etmesa.getWindowToken(), 0);
        }
    };

    View.OnClickListener OnClickListenerbtbtnuevo=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Activity_Pedidos.this, Activity_Pedido_Search.class);
            intent.putExtra("sPedido_item", "0");
            intent.putExtra("sPedido_Nro", "0");
            intent.putExtra("sPedido_Mesa", "0");
            startActivityForResult(intent,1);
        }
    };


    View.OnClickListener OnClickListenerbtbtAgregar=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            if(etmesa.getText().toString().trim().length()==0){
                Toast toastMesa = Toast.makeText(getApplicationContext(),"Ingrese Número de mesa",Toast.LENGTH_SHORT);
                toastMesa.show();
                vibrator.vibrate(200);
                etmesa.requestFocus();
                return;
            }

            if(etpedido.getText().toString().trim().length()==0){
                Toast toastCodigo = Toast.makeText(getApplicationContext(),"Ingrese Número de pedido",Toast.LENGTH_SHORT);
                toastCodigo.show();
                vibrator.vibrate(200);
                etpedido.requestFocus();
                return;
            }

            PED_EXIST=pedidoDetalleDAO.PEDIDO_EXISTS(Integer.valueOf(etmesa.getText().toString()),Integer.valueOf(etpedido.getText().toString()));
            if(PED_EXIST==0 ){
                Toast.makeText(Activity_Pedidos.this,"El numero de pedido o la mesa no existe", Toast.LENGTH_SHORT).show();
                return;
            }

            Integer Mesa=Integer.valueOf(etmesa.getText().toString());
            Integer Pedido=Integer.valueOf(etpedido.getText().toString());
            Intent intent = new Intent(Activity_Pedidos.this, Activity_Pedido_Search.class);
            intent.putExtra("sPedido_item", "1");
            intent.putExtra("sPedido_Nro", Pedido.toString());
            intent.putExtra("sPedido_Mesa", Mesa.toString());
            startActivityForResult(intent,1);
        }
    };

}
