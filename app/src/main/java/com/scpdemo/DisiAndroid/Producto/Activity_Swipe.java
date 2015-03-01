package com.scpdemo.DisiAndroid.Producto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.scpdemo.DisiAndroid.Producto.Fragment_Cliente;
import com.scpdemo.DisiAndroid.Producto.Fragment_Pedido;
import com.scpdemo.DisiAndroid.Producto.Fragment_Producto;
import com.scpdemo.DisiAndroid.R;

/**
 * Created by rgalvez on 19/02/2015.
 */
public class Activity_Swipe extends ActionBarActivity {

    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_my);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(adapter);
    }

    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int arg0) {
            switch (arg0) {
                case 0:
                    return new Fragment_Producto();
                case 1:
                    return new Fragment_Cliente();
                case 2:
                    return new Fragment_Pedido();
                default:
                    return null;
            }
        }

        public int getCount() {
            return 3;
        }

        public CharSequence getPageTitle(int position) {
            String titulo = null;
            switch (position) {
                case 0:
                    titulo = "PRODUCTOS";
                    break;
                case 1:
                    titulo = "CLIENTES";
                    break;
                case 2:
                    titulo = "PEDIDOS";
                    break;
            }
            return titulo;
        }
    }
}
