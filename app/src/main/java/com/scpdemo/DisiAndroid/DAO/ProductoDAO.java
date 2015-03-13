package com.scpdemo.DisiAndroid.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.scpdemo.DisiAndroid.Entities.Mesa;
import com.scpdemo.DisiAndroid.Entities.Producto;
import com.scpdemo.DisiAndroid.R;

import java.util.ArrayList;

/**
 * Created by Cesar on 11/03/2015.
 */
public class ProductoDAO {

    public ArrayList<Producto> lstProducto = null;
    public ArrayList<Producto> lstProductoSearch = null;
    public int PRODUCTO_MAX() {
        Integer MaxID=0;
        Cursor cursor = null;
        try{
            cursor = DataBaseHelper.myDataBase.rawQuery("SELECT  MAX(PRODCOD)AS ID FROM PRODUCTO",null);
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

    public void Producto_PopulateSearch() {
        Cursor cursor = null;
        Producto producto = null;
        try {

            cursor = DataBaseHelper.myDataBase.query("PRODUCTO", null, null, null, null, null, "PRODDES");
            lstProductoSearch = new ArrayList<Producto>();
            lstProductoSearch.clear();
            if (cursor.moveToFirst()) {
                do {
                    producto = new Producto();

                    producto.setPRODCOD(cursor.isNull(cursor.getColumnIndex("PRODCOD")) ? 0 : cursor.getInt(cursor.getColumnIndex("PRODCOD")));
                    producto.setPRODNOM(cursor.isNull(cursor.getColumnIndex("PRODNOM")) ? "" : cursor.getString(cursor.getColumnIndex("PRODNOM")));
                    producto.setPRODPRE(cursor.isNull(cursor.getColumnIndex("PRODPRE")) ? 0 : cursor.getDouble(cursor.getColumnIndex("PRODPRE")));
                    lstProductoSearch.add(new Producto(producto.getPRODCOD(),producto.getPRODMON(),producto.getPRODTIP(),producto.getPRODNOM(),
                    producto.getPRODDES(),producto.getPRODPRE(),producto.getPRODINA()));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    public void Producto_PopulateList() {
        Cursor cursor = null;
        Producto producto = null;
        try {

            cursor = DataBaseHelper.myDataBase.query("PRODUCTO", null, null, null, null, null, "PRODNOM");
            lstProducto = new ArrayList<Producto>();
            lstProducto.clear();
            if (cursor.moveToFirst()) {
                do {
                    producto = new Producto();

                    producto.setPRODCOD(cursor.isNull(cursor.getColumnIndex("PRODCOD")) ? 0 : cursor.getInt(cursor.getColumnIndex("PRODCOD")));
                    producto.setPRODMON(cursor.isNull(cursor.getColumnIndex("PRODMON")) ? "" : cursor.getString(cursor.getColumnIndex("PRODMON")));
                    producto.setPRODTIP(cursor.isNull(cursor.getColumnIndex("PRODTIP")) ? "" : cursor.getString(cursor.getColumnIndex("PRODTIP")));
                    producto.setPRODNOM(cursor.isNull(cursor.getColumnIndex("PRODNOM")) ? "" : cursor.getString(cursor.getColumnIndex("PRODNOM")));
                    producto.setPRODDES(cursor.isNull(cursor.getColumnIndex("PRODDES")) ? "" : cursor.getString(cursor.getColumnIndex("PRODDES")));
                    producto.setPRODPRE(cursor.isNull(cursor.getColumnIndex("PRODPRE")) ? 0 : cursor.getDouble(cursor.getColumnIndex("PRODPRE")));
                    producto.setPRODINA(cursor.isNull(cursor.getColumnIndex("PRODINA")) ? 0 : cursor.getInt(cursor.getColumnIndex("PRODINA")));
                    lstProducto.add(new Producto(producto.getPRODCOD(),producto.getPRODMON(),producto.getPRODTIP(),producto.getPRODNOM(),
                            producto.getPRODDES(),producto.getPRODPRE(),producto.getPRODINA(),R.drawable.comida));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    public void Producto_PopulateList_Opciones(Integer NROPARAM) {
        Cursor cursor = null;
        Producto producto = null;
        try {
            if (NROPARAM==0){
                cursor = DataBaseHelper.myDataBase.query("PRODUCTO", null,null,null, null, null, "PRODDES");
            }else{
                cursor = DataBaseHelper.myDataBase.query("PRODUCTO", null, "PRODTIP = ?", new String[]{String.valueOf(NROPARAM)}, null, null, "PRODDES");
            }

            lstProductoSearch = new ArrayList<Producto>();
            lstProductoSearch.clear();
            if (cursor.moveToFirst()) {
                do {
                    producto = new Producto();

                    producto.setPRODCOD(cursor.isNull(cursor.getColumnIndex("PRODCOD")) ? 0 : cursor.getInt(cursor.getColumnIndex("PRODCOD")));
                    producto.setPRODMON(cursor.isNull(cursor.getColumnIndex("PRODMON")) ? "" : cursor.getString(cursor.getColumnIndex("PRODMON")));
                    producto.setPRODTIP(cursor.isNull(cursor.getColumnIndex("PRODTIP")) ? "" : cursor.getString(cursor.getColumnIndex("PRODTIP")));
                    producto.setPRODNOM(cursor.isNull(cursor.getColumnIndex("PRODNOM")) ? "" : cursor.getString(cursor.getColumnIndex("PRODNOM")));
                    producto.setPRODDES(cursor.isNull(cursor.getColumnIndex("PRODDES")) ? "" : cursor.getString(cursor.getColumnIndex("PRODDES")));
                    producto.setPRODPRE(cursor.isNull(cursor.getColumnIndex("PRODPRE")) ? 0 : cursor.getDouble(cursor.getColumnIndex("PRODPRE")));
                    producto.setPRODINA(cursor.isNull(cursor.getColumnIndex("PRODINA")) ? 0 : cursor.getInt(cursor.getColumnIndex("PRODINA")));
                    lstProductoSearch.add(new Producto(producto.getPRODCOD(),producto.getPRODMON(),producto.getPRODTIP(),producto.getPRODNOM(),
                            producto.getPRODDES(),producto.getPRODPRE(),producto.getPRODINA()));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }





    public Producto getPRODUCTOCODById(int PRODCOD) {
        Cursor cursor = null;
        Producto producto = null;
        try {
            cursor = DataBaseHelper.myDataBase.query("PRODUCTO", null, "PRODCOD = ?",
                    new String[]{String.valueOf(PRODCOD)}, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    producto = new Producto();
                    producto.setPRODCOD(PRODCOD);

                    producto.setPRODNOM(cursor.isNull(cursor.getColumnIndex("PRODNOM")) ? "" : cursor.getString(cursor.getColumnIndex("PRODNOM")));


                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return producto;
    }

    public void insertProducto(Producto producto){
        try{
            ContentValues cv = new ContentValues();

            cv.put("PRODCOD", producto.getPRODCOD());
            cv.put("PRODMON", producto.getPRODMON());
            cv.put("PRODTIP", producto.getPRODTIP());
            cv.put("PRODNOM", producto.getPRODNOM());
            cv.put("PRODDES", producto.getPRODDES());
            cv.put("PRODPRE", producto.getPRODPRE());
            cv.put("PRODINA", producto.getPRODINA());
            DataBaseHelper.myDataBase.insert("PRODUCTO",null,cv);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateProducto(Producto producto){
        try{
            ContentValues cv = new ContentValues();
            cv.put("PRODCOD", producto.getPRODCOD());
            cv.put("PRODMON", producto.getPRODMON());
            cv.put("PRODTIP", producto.getPRODTIP());
            cv.put("PRODNOM", producto.getPRODNOM());
            cv.put("PRODDES", producto.getPRODDES());
            cv.put("PRODPRE", producto.getPRODPRE());
            cv.put("PRODINA", producto.getPRODINA());

            DataBaseHelper.myDataBase.update("PRODUCTO",cv,"PRODCOD = ?", new String[]{String.valueOf(producto.getPRODCOD())});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteProducto(Producto producto){
        try{
            DataBaseHelper.myDataBase.delete("PRODUCTO","PRODCOD = ?", new String[]{String.valueOf(producto.getPRODCOD())});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
