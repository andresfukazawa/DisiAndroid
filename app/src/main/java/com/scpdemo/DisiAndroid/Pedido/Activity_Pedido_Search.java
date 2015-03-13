package com.scpdemo.DisiAndroid.Pedido;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.Adapter.MesaAdapter;
import com.scpdemo.DisiAndroid.Adapter.ProductoAdapter;
import com.scpdemo.DisiAndroid.Adapter.ProductoAdapterSearch;
import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.DAO.MesaDAO;
import com.scpdemo.DisiAndroid.DAO.PedidoDetalleDAO;
import com.scpdemo.DisiAndroid.DAO.ProductoDAO;
import com.scpdemo.DisiAndroid.Entities.Mesa;
import com.scpdemo.DisiAndroid.Entities.Pedido_Detalle;
import com.scpdemo.DisiAndroid.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Renan on 11/03/2015.
 */
public class Activity_Pedido_Search extends ActionBarActivity  {
    private Spinner sp_mesa;
    private ListView lvProducto;
    private Button btEntrada,btcarta,btPostre;
    private EditText etBuscar;
    private TextView tvMesa;

    private MesaAdapter mesaAdapter = null;
    private MesaDAO mesaDAO = new MesaDAO();

    private ProductoAdapterSearch productoAdapter = null;
    private ProductoDAO productoDAO = new ProductoDAO();

    private PedidoDetalleDAO pedidoDetalleDAO = new PedidoDetalleDAO();
    private Pedido_Detalle pedido_detalle=null;

    private Integer MAX_PEDIDO=0;
    private Integer MAX_ITEM=0;
    private Integer PROD_EXIST=0;
    private Integer Accion;
    private Integer MesaNro;

    Integer iPedido_item;
    Integer iPedido_Nro;
    Integer iPedido_Mesa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_producto);

        sp_mesa = (Spinner) findViewById(R.id.sp_mesa);
        lvProducto = (ListView) findViewById(R.id.lvProducto);
        btEntrada = (Button) findViewById(R.id.btEntrada);
        btcarta = (Button) findViewById(R.id.btCarta);
        btPostre = (Button) findViewById(R.id.btPostre);
        tvMesa = (TextView) findViewById(R.id.tvMesa);

        btEntrada.setOnClickListener(OnClickListenerbtEntrada);
        btcarta.setOnClickListener(OnClickListenerbtcarta);
        btPostre.setOnClickListener(OnClickListenerbtPostre);
        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_Pedido_Search.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

             iPedido_item=Integer.valueOf(getIntent().getExtras().getString("sPedido_item"));
             iPedido_Nro=Integer.valueOf(getIntent().getExtras().getString("sPedido_Nro"));
             iPedido_Mesa=Integer.valueOf(getIntent().getExtras().getString("sPedido_Mesa"));


            if (iPedido_item>0){
                Accion=1;
            }else{
                Accion=0;
            }

            if (Accion==0){
                MAX_PEDIDO=pedidoDetalleDAO.MAX_PEDIDO()+1;
                Mesa_PopulateSpinner();
            }else {
                MAX_PEDIDO=iPedido_Nro;
                MesaNro=iPedido_Mesa;
                tvMesa.setVisibility(View.GONE);
                sp_mesa.setVisibility(View.GONE);
            }
            productoDAO.Producto_PopulateList_Opciones(0);
            productoAdapter = new ProductoAdapterSearch(Activity_Pedido_Search.this, 0, productoDAO.lstProductoSearch);
            productoAdapter.notifyDataSetChanged();
            lvProducto.setAdapter(productoAdapter);

            lvProducto.setOnItemClickListener(lvProductoOnItemClickListener);


        }catch (Exception ex){
            Toast.makeText(Activity_Pedido_Search.this,ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    View.OnClickListener OnClickListenerbtEntrada=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            productoDAO.Producto_PopulateList_Opciones(1);
            productoAdapter = new ProductoAdapterSearch(Activity_Pedido_Search.this, 0, productoDAO.lstProductoSearch);
            productoAdapter.notifyDataSetChanged();
            lvProducto.setAdapter(productoAdapter);
        }
    };

    View.OnClickListener OnClickListenerbtcarta=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            productoDAO.Producto_PopulateList_Opciones(2);
            productoAdapter = new ProductoAdapterSearch(Activity_Pedido_Search.this, 0, productoDAO.lstProductoSearch);
            productoAdapter.notifyDataSetChanged();
            lvProducto.setAdapter(productoAdapter);
        }
    };


    View.OnClickListener OnClickListenerbtPostre=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            productoDAO.Producto_PopulateList_Opciones(3);
            productoAdapter = new ProductoAdapterSearch(Activity_Pedido_Search.this, 0, productoDAO.lstProductoSearch);
            productoAdapter.notifyDataSetChanged();
            lvProducto.setAdapter(productoAdapter);
        }
    };

    AbsListView.OnItemClickListener lvProductoOnItemClickListener = new AbsListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           Intent intent = new Intent();
            pedido_detalle=new Pedido_Detalle();
            MAX_ITEM=pedidoDetalleDAO.MAX_ITEM()+1;
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDate = df.format(c.getTime());

            if (Accion!=0){
                if (sp_mesa.getSelectedItem().toString().trim().length()==0){
                    Toast.makeText(Activity_Pedido_Search.this,"Seleccione Mesa", Toast.LENGTH_SHORT).show();
                    return;
                }

               PROD_EXIST=pedidoDetalleDAO.PROD_EXISTS(productoAdapter.getItem(position).getPRODCOD(),MAX_PEDIDO);
              if(PROD_EXIST>0 ){
                  Toast.makeText(Activity_Pedido_Search.this,"Ya existe el producto en el pedido", Toast.LENGTH_SHORT).show();
                  return;
              }
            }

            pedido_detalle.setDETPITE(Integer.valueOf(MAX_ITEM));
            pedido_detalle.setDETPPED(Integer.valueOf(MAX_PEDIDO));
            pedido_detalle.setDETPPRO(Integer.valueOf(productoAdapter.getItem(position).getPRODCOD()));
            pedido_detalle.setDETPCAN(Integer.valueOf(1));
            pedido_detalle.setDETPPRE(Double.valueOf(productoAdapter.getItem(position).getPRODPRE()));
            if (Accion==0){
               pedido_detalle.setDETMESA(Integer.valueOf(sp_mesa.getSelectedItem().toString()));
                intent.putExtra("sPedido_Mesa", sp_mesa.getSelectedItem().toString());
                intent.putExtra("sPedido_Nro",String.valueOf(MAX_PEDIDO));
            }else{
                pedido_detalle.setDETMESA(Integer.valueOf(iPedido_Mesa));
                intent.putExtra("sPedido_Mesa", String.valueOf(iPedido_Mesa));
                intent.putExtra("sPedido_Nro",String.valueOf(MAX_PEDIDO));
            }
            pedido_detalle.setDETFECHA(formattedDate);
            pedido_detalle.setDETESTAD(Integer.valueOf(1));
            pedidoDetalleDAO.insertPedidoDetalle(pedido_detalle);
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    private void Mesa_PopulateSpinner() {

        List<String> list = new ArrayList<String>();
        Cursor cursor = null;

        try{
            cursor = DataBaseHelper.myDataBase.query("MESAS", new String[]{"MESACOD"}, null, null, null, null, "MESACOD");
            if(cursor.moveToFirst()) {
                do {
                    list.add(cursor.getInt(cursor.getColumnIndex("MESACOD"))+"");
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        sp_mesa.setAdapter(dataAdapter);
    }



}