package com.scpdemo.DisiAndroid.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import com.scpdemo.DisiAndroid.Entities.Mesa;
import com.scpdemo.DisiAndroid.R;

import java.util.ArrayList;

/**
 * Created by Renan on 25/02/2015.
 */
public class MesaDAO {
       public  ArrayList<Mesa> lstMesa = null;
    public int MESA_MAX() {
        Integer MaxID=0;
        Cursor cursor = null;
        try{
            cursor = DataBaseHelper.myDataBase.rawQuery("SELECT   ifnull(MAX(MESACOD),0)AS ID FROM MESAS",null);
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

    public void Mesa_PopulateList() {
        Cursor cursor = null;
        Mesa mesa = null;
        try {

            cursor = DataBaseHelper.myDataBase.query("MESAs", null, null, null, null, null, "MESADES");
            lstMesa = new ArrayList<Mesa>();
            lstMesa.clear();
            if (cursor.moveToFirst()) {
                do {
                    mesa = new Mesa();
                    mesa.setMESACOD(cursor.isNull(cursor.getColumnIndex("MESACOD")) ? 0 : cursor.getInt(cursor.getColumnIndex("MESACOD")));
                    mesa.setMESADES(cursor.isNull(cursor.getColumnIndex("MESADES")) ? "" : cursor.getString(cursor.getColumnIndex("MESADES")));
                    mesa.setMESAINA(cursor.isNull(cursor.getColumnIndex("MESAINA")) ? 0 : cursor.getInt(cursor.getColumnIndex("MESAINA")));
                    lstMesa.add(new Mesa(mesa.getMESACOD(), mesa.getMESADES(),mesa.getMESAINA(), R.drawable.mesa));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }



    public Mesa getMESACODById(int MESACOD) {
        Cursor cursor = null;
        Mesa mesa = null;
        try {
            cursor = DataBaseHelper.myDataBase.query("MESAS", null, "MESACOD = ?", new String[]{String.valueOf(MESACOD)}, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    mesa = new Mesa();
                    mesa.setMESACOD(MESACOD);
                    mesa.setMESADES(cursor.isNull(cursor.getColumnIndex("MESADES")) ? "" : cursor.getString(cursor.getColumnIndex("MESADES")));

                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return mesa;
    }

    public void insertMesa(Mesa mesa){
        try{
            ContentValues cv = new ContentValues();
            cv.put("MESACOD", mesa.getMESACOD());
            cv.put("MESADES", mesa.getMESADES());
            cv.put("MESAINA", mesa.getMESAINA());
            DataBaseHelper.myDataBase.insert("MESAS",null,cv);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateMesa(Mesa mesa){
        try{
            ContentValues cv = new ContentValues();
            cv.put("MESADES", mesa.getMESADES());
            cv.put("MESAINA", mesa.getMESAINA());
            DataBaseHelper.myDataBase.update("MESAS",cv,"MESACOD = ?", new String[]{String.valueOf(mesa.getMESACOD())});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteMesa(Mesa mesa){
        try{
            DataBaseHelper.myDataBase.delete("MESAS","MESACOD = ?", new String[]{String.valueOf(mesa.getMESACOD())});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
