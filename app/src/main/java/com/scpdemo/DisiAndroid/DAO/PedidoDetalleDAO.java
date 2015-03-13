package com.scpdemo.DisiAndroid.DAO;
import android.content.ContentValues;
import android.database.Cursor;
import com.scpdemo.DisiAndroid.Entities.Pedido_Detalle;

import java.util.ArrayList;

/**
 * Created by rgalvez on 11/03/2015.
 */
public class PedidoDetalleDAO{
    public ArrayList<Pedido_Detalle> lstPedido_Detalle = null;

    public int PROD_EXISTS(int DETPPRO,int DETPPED) {
        Integer Count_Exist=0;
        Cursor cursor = null;
        try{
            cursor = DataBaseHelper.myDataBase.rawQuery("SELECT COUNT(0) AS ID FROM PEDIDO_DETALLE WHERE DETPPRO=" + DETPPRO  + " AND DETPPED=" +DETPPED ,null);
            if(cursor.moveToFirst()) {
                do {
                    Count_Exist = Integer.valueOf(cursor.getString(cursor.getColumnIndex("ID")));
                } while (cursor.moveToNext());
            }
            return Count_Exist;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return Count_Exist;
    }


    public int PEDIDO_EXISTS(int DETMESA,int DETPPED) {
        Integer Count_Exist=0;
        Cursor cursor = null;
        try{
            cursor = DataBaseHelper.myDataBase.rawQuery("SELECT COUNT(0) AS ID FROM PEDIDO_DETALLE WHERE DETMESA=" + DETMESA  + " AND DETPPED=" +DETPPED ,null);
            if(cursor.moveToFirst()) {
                do {
                    Count_Exist = Integer.valueOf(cursor.getString(cursor.getColumnIndex("ID")));
                } while (cursor.moveToNext());
            }
            return Count_Exist;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return Count_Exist;
    }


    public int MAX_ITEM() {
        Integer MaxID=0;
        Cursor cursor = null;
        try{
            cursor = DataBaseHelper.myDataBase.rawQuery("SELECT   ifnull(MAX(DETPITE),0) AS ID FROM PEDIDO_DETALLE",null);
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

    public int MAX_PEDIDO() {
        Integer MaxID=0;
        Cursor cursor = null;
        try{
            cursor = DataBaseHelper.myDataBase.rawQuery("SELECT  ifnull(MAX(DETPPED),0) AS ID FROM PEDIDO_DETALLE",null);
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

    public void PedidoDetalle_Populate_byMesa_List(int DETMESA) {
        Cursor cursor = null;
        Pedido_Detalle pedido_Detalle = null;
        try {
            cursor = DataBaseHelper.myDataBase.query("PEDIDO_DETALLE", null, "DETMESA = ?", new String[]{String.valueOf(DETMESA)}, null, null, null);
            lstPedido_Detalle = new ArrayList<Pedido_Detalle>();
            lstPedido_Detalle.clear();
            if (cursor.moveToFirst()) {
                do {
                    pedido_Detalle = new Pedido_Detalle();
                    pedido_Detalle.setDETPITE(cursor.isNull(cursor.getColumnIndex("DETPITE")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETPITE")));
                    pedido_Detalle.setDETPPED(cursor.isNull(cursor.getColumnIndex("DETPPED")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETPPED")));
                    pedido_Detalle.setDETPPRO(cursor.isNull(cursor.getColumnIndex("DETPPRO")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETPPRO")));
                    pedido_Detalle.setDETPCAN(cursor.isNull(cursor.getColumnIndex("DETPCAN")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETPCAN")));
                    pedido_Detalle.setDETPPRE(cursor.isNull(cursor.getColumnIndex("DETPPRE")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETPPRE")));
                    pedido_Detalle.setDETMESA(cursor.isNull(cursor.getColumnIndex("DETMESA")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETMESA")));
                    pedido_Detalle.setDETFECHA(cursor.isNull(cursor.getColumnIndex("DETFECHA")) ? "" : cursor.getString(cursor.getColumnIndex("DETFECHA")));
                    pedido_Detalle.setDETESTAD(cursor.isNull(cursor.getColumnIndex("DETESTAD")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETESTAD")));
                    pedido_Detalle.setMESADES(cursor.isNull(cursor.getColumnIndex("MESADES")) ? "" : cursor.getString(cursor.getColumnIndex("MESADES")));
                    pedido_Detalle.setMESADES(cursor.isNull(cursor.getColumnIndex("PRODNOM")) ? "" : cursor.getString(cursor.getColumnIndex("PRODNOM")));
                    lstPedido_Detalle.add(new Pedido_Detalle(
                            pedido_Detalle.getDETPITE(),
                            pedido_Detalle.getDETPPED(),
                            pedido_Detalle.getDETPPRO(),
                            pedido_Detalle.getDETPCAN(),
                            pedido_Detalle.getDETPPRE(),
                            pedido_Detalle.getDETMESA(),
                            pedido_Detalle.getDETFECHA(),
                            pedido_Detalle.getDETESTAD(),
                            pedido_Detalle.getMESADES(),
                            pedido_Detalle.getPRODNOM()));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    public void PedidoDetalle_Populate_byPedido_List(int NROPARAM,int Opcion) {
        Cursor cursor = null;
        Pedido_Detalle pedido_Detalle = null;
        try {
            //FILTRAMOS POR PEDIDO
                if (Opcion==1){
                    cursor = DataBaseHelper.myDataBase.query("V_PEDIDO_DETALLE", null, "DETPPED = ?", new String[]{String.valueOf(NROPARAM)}, null, null, null);
                }
            //FILTRAMOS POR PEDIDO
            if (Opcion==2){
                cursor = DataBaseHelper.myDataBase.query("V_PEDIDO_DETALLE", null, "DETMESA = ?", new String[]{String.valueOf(NROPARAM)}, null, null, null);
            }
            lstPedido_Detalle = new ArrayList<Pedido_Detalle>();
            lstPedido_Detalle.clear();
            if (cursor.moveToFirst()) {
                do {
                    pedido_Detalle = new Pedido_Detalle();
                    pedido_Detalle.setDETPITE(cursor.isNull(cursor.getColumnIndex("DETPITE")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETPITE")));
                    pedido_Detalle.setDETPPED(cursor.isNull(cursor.getColumnIndex("DETPPED")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETPPED")));
                    pedido_Detalle.setDETPPRO(cursor.isNull(cursor.getColumnIndex("DETPPRO")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETPPRO")));
                    pedido_Detalle.setDETPCAN(cursor.isNull(cursor.getColumnIndex("DETPCAN")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETPCAN")));
                    pedido_Detalle.setDETPPRE(cursor.isNull(cursor.getColumnIndex("DETPPRE")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETPPRE")));
                    pedido_Detalle.setDETMESA(cursor.isNull(cursor.getColumnIndex("DETMESA")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETMESA")));
                    pedido_Detalle.setDETFECHA(cursor.isNull(cursor.getColumnIndex("DETFECHA")) ? "" : cursor.getString(cursor.getColumnIndex("DETFECHA")));
                    pedido_Detalle.setDETESTAD(cursor.isNull(cursor.getColumnIndex("DETESTAD")) ? 0 : cursor.getInt(cursor.getColumnIndex("DETESTAD")));
                    pedido_Detalle.setMESADES(cursor.isNull(cursor.getColumnIndex("MESADES")) ? "" : cursor.getString(cursor.getColumnIndex("MESADES")));
                    pedido_Detalle.setPRODNOM(cursor.isNull(cursor.getColumnIndex("PRODNOM")) ? "" : cursor.getString(cursor.getColumnIndex("PRODNOM")));
                    lstPedido_Detalle.add(new Pedido_Detalle(
                            pedido_Detalle.getDETPITE(),
                            pedido_Detalle.getDETPPED(),
                            pedido_Detalle.getDETPPRO(),
                            pedido_Detalle.getDETPCAN(),
                            pedido_Detalle.getDETPPRE(),
                            pedido_Detalle.getDETMESA(),
                            pedido_Detalle.getDETFECHA(),
                            pedido_Detalle.getDETESTAD(),
                            pedido_Detalle.getMESADES(),
                            pedido_Detalle.getPRODNOM()));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }


    public void insertPedidoDetalle(Pedido_Detalle pedidodetalle){
        try{
            ContentValues cv = new ContentValues();
            cv.put("DETPITE", pedidodetalle.getDETPITE());
            cv.put("DETPPED", pedidodetalle.getDETPPED());
            cv.put("DETPPRO", pedidodetalle.getDETPPRO());
            cv.put("DETPCAN", pedidodetalle.getDETPCAN());
            cv.put("DETPPRE", pedidodetalle.getDETPPRE());
            cv.put("DETMESA", pedidodetalle.getDETMESA());
            cv.put("DETFECHA", pedidodetalle.getDETFECHA());
            cv.put("DETESTAD", pedidodetalle.getDETESTAD());
            DataBaseHelper.myDataBase.insert("PEDIDO_DETALLE",null,cv);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updatePedidoDetalle(Pedido_Detalle pedidodetalle){
        try{
            ContentValues cv = new ContentValues();
            cv.put("DETPCAN", pedidodetalle.getDETPCAN());
            DataBaseHelper.myDataBase.update("PEDIDO_DETALLE",cv,"DETPITE = ?", new String[]{String.valueOf(pedidodetalle.getDETPITE())});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deletePedidoDetalle(Pedido_Detalle pedidodetalle){
        try{
            DataBaseHelper.myDataBase.delete("PEDIDO_DETALLE","DETPITE = ?", new String[]{String.valueOf(pedidodetalle.getDETPITE())});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}