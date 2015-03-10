package com.scpdemo.DisiAndroid.Pedido;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.Adapter.LVMainAdapter;
import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Susumu on 3/4/2015.
 */
public class Activity_MostrarMenu extends ActionBarActivity {

    private TextView          tvMesa;
    private EditText          etFiltro;
    private ListView          lvMenu;
    private LVMainAdapter     mLVMainAdapter;
    private ArrayList<String> mLstString;
    private Button            btOrdenar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarmenu);

        tvMesa    = (TextView)findViewById(R.id.tv_mm_SMMesa);
        etFiltro  = (EditText)findViewById(R.id.et_mm_SMFiltro);
        lvMenu    = (ListView)findViewById(R.id.lv_mm_SMMenu);
        btOrdenar = (Button)findViewById(R.id.bt_mm_Ordenar);

        populateList(getIntent().getExtras().getString("tipo"));
        mLVMainAdapter = new LVMainAdapter(Activity_MostrarMenu.this, 0, mLstString);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.dashboard_title_pedidos);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        lvMenu.setAdapter(mLVMainAdapter);
        etFiltro.addTextChangedListener(etMenuTextWatcher);
        btOrdenar.setOnClickListener(btOrdenarOnClick);
        tvMesa.setText("Mesa " + getIntent().getExtras().getString("mesa"));


//        lvMenu.setOnItemClickListener(lvMenuOnClick);
    }

    View.OnClickListener btOrdenarOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();

            ContentValues cv = new ContentValues();
            cv.put("DETPPED", dateFormat.format(cal.getTime()));
            cv.put("MESADET", Integer.valueOf(getIntent().getExtras().getString("mesa")));
            cv.put("DETPPRO", mLVMainAdapter.getItem(0).toString());
            cv.put("DETPCAN", 1);
            long temp = DataBaseHelper.myDataBase.insert("PEDIDO_DETALLE", null, cv);
//            Toast.makeText(Activity_MostrarMenu.this, String.valueOf(temp), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    private void populateList(String tipo) {
        Cursor cursor = null;
        mLstString = new ArrayList<>();

        try{
            cursor = DataBaseHelper.myDataBase.query("PRODUCTO", null, "PRODTIP=?", new String[]{tipo}, null, null, "PRODNOM");
            if(cursor.moveToFirst()) {
                do {
                    mLstString.add(cursor.isNull(cursor.getColumnIndex("PRODNOM")) ? "" : cursor.getString(cursor.getColumnIndex("PRODNOM")));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

    }

    TextWatcher etMenuTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d("watcher", s.toString());
            mLVMainAdapter.getFilter().filter(s.toString());
            mLVMainAdapter.notifyDataSetChanged();
//            tempItem = mLVMainAdapter.getItem(0).toString();
        }
    };

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_platos, menu);
        menu.findItem(R.id.ic_action_add).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Toast.makeText(Activity_MostrarMenu.this, "getCount() : " + mLVMainAdapter.getCount(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()) {
            case R.id.ic_action_add:
//                mLVMainAdapter.get
//                if (lvMenu.getCount() == 1) {
                if (mLVMainAdapter.getCount() == 1) {
                    //if (mLVMenuAdapter.getCount() == 1) {
//                    Toast.makeText(Activity_MostrarMenu.this, "getCount() : " + mLVMainAdapter.getCount(), Toast.LENGTH_SHORT).show();
                    ContentValues cv = new ContentValues();
                    cv.put("MESADET", Integer.valueOf(getIntent().getExtras().getString("mesa")));
                    cv.put("DETPPRO", mLVMainAdapter.getItem(0).toString());
                    cv.put("DETPCAN", 1);
                    long temp = DataBaseHelper.myDataBase.insert("PEDIDO_DETALLE", null, cv);
                    Toast.makeText(Activity_MostrarMenu.this, String.valueOf(temp), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            default:
                return super.onOptionsItemSelected(item);
        }

    }*/

    /*ListView.OnItemClickListener lvMenuOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String data=(String)parent.getItemAtPosition(position);
            etFiltro.setText(data);
        }
    };*/
}
