package com.scpdemo.DisiAndroid.Producto;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.graphics.Color;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;

import com.scpdemo.DisiAndroid.R;


public class Activity_Producto extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();


        /**INDICAR TITULO Y SUBTITULO**/
        actionBar.setTitle("TOMA DE PEDIDOS");
        actionBar.setSubtitle("REGISTRO");


        /**MODO TABS EN ACTIONBAR**/
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        final int[] ICONS = new int[] {
                R.drawable.configuracion,
                R.drawable.cliente1,
                R.drawable.buscar1,
                R.drawable.ic_launcher,
        };

        /**CREAR TABS**/
        ActionBar.Tab tab;
        tab = actionBar.newTab()
                //.setText("Productos")
                .setIcon((ICONS[0]))
                .setTabListener(new TabsListener(
                        this, "pedidos", Fragment_Pedido.class));

        actionBar.addTab(tab);

        tab = actionBar.newTab()
                //.setText("Clientes")
                .setIcon((ICONS[1]))
                .setTabListener(new TabsListener(
                        this, "clientes", Fragment_Cliente.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                //.setText("Pedidos")
                .setIcon((ICONS[2]))
                .setTabListener(new TabsListener(
                 this, "productos", Fragment_Producto.class));
        actionBar.addTab(tab);


        //actionBar.setBackgroundDrawable(new ColorDrawable(0xff00DDED));

// set background for action bar
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8F00")));


// set background for action bar tab
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD54F")));

        //actionBar.show();


    }


    public class TabsListener  implements ActionBar.TabListener {

        private Fragment fragment;
        private final String tag;

        public TabsListener(Activity activity, String tag, Class cls) {
            this.tag = tag;
            fragment = Fragment.instantiate(activity, cls.getName());
        }

        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.replace(android.R.id.content, fragment, tag);

        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(fragment);
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}
    }
}
