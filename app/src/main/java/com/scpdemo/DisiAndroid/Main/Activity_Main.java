package com.scpdemo.DisiAndroid.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.scpdemo.DisiAndroid.Activity_Busqueda;
import com.scpdemo.DisiAndroid.Mesa.Activity_Mesa_Lis;
import com.scpdemo.DisiAndroid.Producto.Activity_Producto;
import com.scpdemo.DisiAndroid.Usuario.Activity_Usuario;
import com.scpdemo.DisiAndroid.Producto.Activity_Swipe;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.Usuario.Activity_Usuario_List;

/**
 * Created by rgalvez on 10/02/2015.
 */

public class Activity_Main extends ActionBarActivity {
    Button btnUsuario,btnCliente,btnMesa,btnProducto,btnPedido,btnFacturar,btnConsulta,btnReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.dashboard_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        btnUsuario=(Button) findViewById(R.id.btnUsuario);
        btnCliente=(Button) findViewById(R.id.btnCliente);
        btnMesa=(Button) findViewById(R.id.btnMesa);
        btnProducto=(Button) findViewById(R.id.btnProducto);
        btnPedido=(Button) findViewById(R.id.btnPedido);
        btnFacturar=(Button) findViewById(R.id.btnFacturar);
        btnConsulta=(Button) findViewById(R.id.btnConsulta);
        btnReporte=(Button) findViewById(R.id.btnReporte);

        btnUsuario.setOnClickListener(btnUsuarioOnClickListener);
        btnCliente.setOnClickListener(btnClienteOnClickListener);
        btnMesa.setOnClickListener(btnMesaOnClickListener);
        btnProducto.setOnClickListener(btnProductoOnClickListener);
        btnPedido.setOnClickListener(btnPedidoOnClickListener);
        btnFacturar.setOnClickListener(btnFacturarOnClickListener);
        btnConsulta.setOnClickListener(btnConsultaOnClickListener);
        btnReporte.setOnClickListener(btnReporteOnClickListener);
    }

    View.OnClickListener btnUsuarioOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Activity_Main.this,Activity_Usuario_List.class);
            startActivityForResult(intent,0);
        }
    };

    View.OnClickListener btnClienteOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Activity_Main.this,Activity_Swipe.class);
            startActivityForResult(intent,0);
        }
    };

    View.OnClickListener btnMesaOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Activity_Main.this,Activity_Mesa_Lis.class);
            startActivityForResult(intent,0);
        }
    };

    View.OnClickListener btnProductoOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Activity_Main.this,Activity_Producto.class);
            startActivityForResult(intent,0);
        }
    };

    View.OnClickListener btnPedidoOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Activity_Main.this,Activity_Producto.class);
            startActivityForResult(intent,0);
        }
    };

    View.OnClickListener btnFacturarOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Activity_Main.this,Activity_Producto.class);
            startActivityForResult(intent,0);
        }
    };

    View.OnClickListener btnConsultaOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Activity_Main.this,Activity_Busqueda.class);
            startActivityForResult(intent,0);
        }
    };

    View.OnClickListener btnReporteOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Activity_Main.this,Activity_Producto.class);
            startActivityForResult(intent,0);
        }
    };

}