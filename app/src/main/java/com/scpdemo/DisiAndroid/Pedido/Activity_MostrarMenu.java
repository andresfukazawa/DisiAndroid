package com.scpdemo.DisiAndroid.Pedido;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.Adapter.LVMainAdapter;
import com.scpdemo.DisiAndroid.DAO.DataBaseHelper;
import com.scpdemo.DisiAndroid.R;

import java.util.ArrayList;

/**
 * Created by Susumu on 3/4/2015.
 */
public class Activity_MostrarMenu extends ActionBarActivity {

    private TextView tvMesa;
    private EditText etFiltro;
    private ListView lvMenu;
    private LVMainAdapter     mLVMainAdapter;
    private ArrayList<String> mLstString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarmenu);

        tvMesa   = (TextView)findViewById(R.id.tv_mm_SMMesa);
        etFiltro = (EditText)findViewById(R.id.et_mm_SMFiltro);
        lvMenu   = (ListView)findViewById(R.id.lv_mm_SMMenu);
        populateList(getIntent().getExtras().getString("tipo"));
        mLVMainAdapter = new LVMainAdapter(Activity_MostrarMenu.this, 0, mLstString);

        lvMenu.setAdapter(mLVMainAdapter);
        etFiltro.addTextChangedListener(etMenuTextWatcher);
    }

    private void populateList(String tipo) {
        Cursor cursor = null;
        mLstString = new ArrayList<>();

        Toast.makeText(Activity_MostrarMenu.this, "in populateList", Toast.LENGTH_SHORT).show();

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
        }
    };
}
