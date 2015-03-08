package com.scpdemo.DisiAndroid.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

import com.scpdemo.DisiAndroid.Adapter.ClienteAdapter;
import com.scpdemo.DisiAndroid.Entities.Cliente;
import com.scpdemo.DisiAndroid.Entities.Mesa;
import com.scpdemo.DisiAndroid.R;

import java.util.ArrayList;

/**
 * Created by rgalvez on 03/03/2015.
 */
public class ClienteDAO {
    public  ArrayList<Cliente> lstCliente = null;

    public int CLIENTE_MAX() {
        Integer MaxID=0;
        Cursor cursor = null;
        try{
            cursor = DataBaseHelper.myDataBase.rawQuery("SELECT  MAX(CLIECOD)AS ID FROM CLIENTES",null);
            if(cursor.moveToFirst()) {
                do {
                    MaxID = Integer.valueOf(cursor.getString(cursor.getColumnIndex("ID")));
                } while (cursor.moveToNext());
            }
            return MaxID;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return MaxID;
    }



    public void Cliente_PopulateList() {
        Cursor cursor = null;
        Cliente cliente = null;
        try {
            String[] campos = new String[] {"CLIECOD", "CLIERUC","CLIENOM"};
            cursor = DataBaseHelper.myDataBase.query("CLIENTES", campos, null, null, null, null, "CLIENOM");
            lstCliente = new ArrayList<Cliente>();
            lstCliente.clear();
            if (cursor.moveToFirst()) {
                do {
                    cliente = new Cliente();
                    cliente.setCLIECOD(cursor.isNull(cursor.getColumnIndex("CLIECOD")) ? 0 : cursor.getInt(cursor.getColumnIndex("CLIECOD")));
                    cliente.setCLIERUC(cursor.isNull(cursor.getColumnIndex("CLIERUC")) ? "" : cursor.getString(cursor.getColumnIndex("CLIERUC")));
                    cliente.setCLIENOM(cursor.isNull(cursor.getColumnIndex("CLIENOM")) ? "" : cursor.getString(cursor.getColumnIndex("CLIENOM")));
                    lstCliente.add(new Cliente(cliente.getCLIECOD(), cliente.getCLIERUC(),cliente.getCLIENOM(),R.drawable.cliente2));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }


    public Cliente getCLIECODById(int CLIECOD) {
        Cursor cursor = null;
        Cliente cliente = null;
        ArrayList<Cliente> lstCliente = null;
        try {
            cursor = DataBaseHelper.myDataBase.query("CLIENTES", null, "CLIECOD = ?", new String[]{String.valueOf(CLIECOD)}, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    cliente = new Cliente();
                    cliente.setCLIECOD(CLIECOD);
                    cliente.setCLIECOD(cursor.isNull(cursor.getColumnIndex("CLIECOD")) ? 0 : Integer.valueOf(cursor.getString(cursor.getColumnIndex("CLIECOD"))));
                    cliente.setCLIERUC(cursor.isNull(cursor.getColumnIndex("CLIERUC")) ? "" : cursor.getString(cursor.getColumnIndex("CLIERUC")));
                    cliente.setCLIENOM(cursor.isNull(cursor.getColumnIndex("CLIENOM")) ? "" : cursor.getString(cursor.getColumnIndex("CLIENOM")));
                    //cliente.setCLIEINA(cursor.isNull(cursor.getColumnIndex("CLIEINA")) ? 0 : Integer.valueOf(cursor.getString(cursor.getColumnIndex("CLIEINA"))));
                    lstCliente.add(new Cliente(cliente.getCLIECOD(), cliente.getCLIERUC(), cliente.getCLIENOM(),0));
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return cliente;
    }

    public void insertCliente(Cliente cliente){
        try{
            ContentValues cv = new ContentValues();
            cv.put("CLIECOD", cliente.getCLIECOD());
            cv.put("CLIERUC", cliente.getCLIERUC());
            cv.put("CLIENOM", cliente.getCLIENOM());
            //cv.put("CLIEFECR", cliente.getCLIEFECR());
            //cv.put("CLIEUSUR", cliente.getCLIEUSUR());
            //cv.put("CLIEINA", cliente.getCLIEINA());
            DataBaseHelper.myDataBase.insert("CLIENTES",null,cv);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateMesa(Cliente cliente){
        try{
            ContentValues cv = new ContentValues();
            cv.put("CLIERUC", cliente.getCLIERUC());
            cv.put("CLIENOM", cliente.getCLIENOM());
            //cv.put("CLIEFECR", cliente.getCLIEFECR());
            //cv.put("CLIEUSUR", cliente.getCLIEUSUR());
            //cv.put("CLIEINA", cliente.getCLIEINA());
            DataBaseHelper.myDataBase.update("CLIENTES",cv,"CLIECOD = ?", new String[]{String.valueOf(cliente.getCLIECOD())});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteMesa(Cliente cliente){
        try{
            DataBaseHelper.myDataBase.delete("CLIENTES","CLIECOD = ?", new String[]{String.valueOf(cliente.getCLIECOD())});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
