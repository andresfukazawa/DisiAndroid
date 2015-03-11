package com.scpdemo.DisiAndroid.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.Activity_Busqueda;
import com.scpdemo.DisiAndroid.Adapter.ListViewAdapter;
import com.scpdemo.DisiAndroid.Clientes.Activity_Clientes_List;
import com.scpdemo.DisiAndroid.Mesa.Activity_Mesa_Lis;
import com.scpdemo.DisiAndroid.Pedido.Activity_Pedido;
import com.scpdemo.DisiAndroid.Producto.Activity_Producto;
import com.scpdemo.DisiAndroid.R;
import com.scpdemo.DisiAndroid.Usuario.Activity_Usuario_List;
import com.scpdemo.tab_control.MapsActivity;

/**
 * Created by rgalvez on 10/02/2015.
 */

public class Activity_Main extends ActionBarActivity {
    Button btnUsuario,btnCliente,btnMesa,btnProducto,btnPedido,btnFacturar,btnConsulta,btnReporte;

    private DrawerLayout mDrawerLayout;
    private ListView mLvMenu;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private LinearLayout llMenu;

    ListViewAdapter adapter;

    String[] titulo = new String[]{
            "Usuarios",
            "Clientes",
            "Mesas",
            "Productos",
            "Ubicación de Franquicias"
    };


    int[] imagenes = {
            R.drawable.seguridad3,
            R.drawable.cliente1,
            R.drawable.mesa,
            R.drawable.nota1,
            R.drawable.ic_gps};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.dashboard_title);
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


        llMenu = (LinearLayout) findViewById(R.id.llMenu);
        mLvMenu = (ListView) findViewById(R.id.listView1);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        DrawerLayout.LayoutParams dllp = (DrawerLayout.LayoutParams) llMenu.getLayoutParams();
        dllp.width = displayMetrics.widthPixels - getResources().getDimensionPixelOffset(R.dimen.lvmenu_marginright);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(Activity_Main.this, mDrawerLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }


        };


        final ListView lista = (ListView) findViewById(R.id.listView1);
        adapter = new ListViewAdapter(this, titulo, imagenes);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), "presiono " + i, Toast.LENGTH_SHORT).show();

                switch(i) {
                    case 0:
                         Intent intentUsuarios = new Intent(Activity_Main.this,Activity_Usuario_List.class);
                         startActivity(intentUsuarios);
                        break;
                    case 1:
                        Intent intentClientes = new Intent(Activity_Main.this,Activity_Clientes_List.class);
                        startActivity(intentClientes);
                        break;
                    case 2:
                        Intent intentMesas = new Intent(Activity_Main.this,Activity_Mesa_Lis.class);
                        startActivity(intentMesas);
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), R.string.en_construccion, Toast.LENGTH_SHORT).show();
//
                        break;
                    case 4:
                        Intent intent5 = new Intent(Activity_Main.this,MapsActivity.class);
                        startActivity(intent5);
                        break;

                }

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), "presiono LARGO " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        else
            return super.onOptionsItemSelected(item);
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
            Intent intent=new Intent(Activity_Main.this,Activity_Clientes_List.class);
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
            Intent intent=new Intent(Activity_Main.this, Activity_Pedido.class);
//            startActivityForResult(intent,0);
            startActivity(intent);
        }
    };

    View.OnClickListener btnFacturarOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), R.string.en_construccion, Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(Activity_Main.this,Activity_Producto.class);
//            startActivityForResult(intent,0);
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
            Toast.makeText(getApplicationContext(), R.string.en_construccion, Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(Activity_Main.this,Activity_Producto.class);
//            startActivityForResult(intent,0);
        }
    };

}