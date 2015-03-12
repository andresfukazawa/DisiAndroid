package com.scpdemo.DisiAndroid.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import com.scpdemo.DisiAndroid.Entities.Usuario;
import com.scpdemo.DisiAndroid.R;

import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * Created by Renan on 27/02/2015.
 */
public class UsuarioDAO {
      public  ArrayList<Usuario> lstUsuario = null;

    public int USUARIO_MAX() {
        Integer MaxID=0;
        Cursor cursor = null;
        try{
            cursor = DataBaseHelper.myDataBase.rawQuery("SELECT  MAX(USUACOD)AS ID FROM USUARIOS",null);
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

    public void Usuario_PopulateList() {
        Cursor cursor = null;
        Usuario usuario = null;
        try {

            cursor = DataBaseHelper.myDataBase.query("USUARIOS", null, null, null, null, null, "USUADES");
            lstUsuario = new ArrayList<Usuario>();
            lstUsuario.clear();
            if (cursor.moveToFirst()) {
                do {
                    usuario = new Usuario();
                    usuario.setUSUACOD(cursor.isNull(cursor.getColumnIndex("USUACOD")) ? 0 : cursor.getInt(cursor.getColumnIndex("USUACOD")));
                    usuario.setUSUADES(cursor.isNull(cursor.getColumnIndex("USUADES")) ? "" : cursor.getString(cursor.getColumnIndex("USUADES")));
                    usuario.setUSUACLAV(cursor.isNull(cursor.getColumnIndex("USUACLAV")) ? "" : cursor.getString(cursor.getColumnIndex("USUACLAV")));
                    usuario.setUSUAMAIL(cursor.isNull(cursor.getColumnIndex("USUAMAIL")) ? "" : cursor.getString(cursor.getColumnIndex("USUAMAIL")));
                    usuario.setUSUAINA(cursor.isNull(cursor.getColumnIndex("USUAINA")) ? 0 : cursor.getInt(cursor.getColumnIndex("USUAINA")));
                    lstUsuario.add(new Usuario(usuario.getUSUACOD(), usuario.getUSUADES(),usuario.getUSUACLAV(),

                            usuario.getUSUAMAIL(),usuario.getUSUAINA(), R.drawable.ic_producto));
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }


    public Usuario getUSUACODById(int USUACOD) {
        Cursor cursor = null;
        Usuario usuario = null;
        try {
            cursor = DataBaseHelper.myDataBase.query("USUARIOS", null, "USUACOD = ?",
                    new String[]{String.valueOf(USUACOD)}, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    usuario = new Usuario();
                    usuario.setUSUACOD(USUACOD);
                    usuario.setUSUADES(cursor.isNull(cursor.getColumnIndex("USUADES")) ? "" : cursor.getString(cursor.getColumnIndex("USUADES")));

                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return usuario;
    }

    public void insertUsuario(Usuario usuario){
        try{

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(usuario.getUSUACLAV().toString().getBytes());
            byte byteData[] = messageDigest.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            String pwdhash = sb.toString();
            ContentValues cv = new ContentValues();
            cv.put("USUACOD", usuario.getUSUACOD());
            cv.put("USUADES", usuario.getUSUADES());
            cv.put("USUACLAV", pwdhash);
            cv.put("USUAMAIL", usuario.getUSUAMAIL());
            cv.put("USUAINA", usuario.getUSUAINA());
            DataBaseHelper.myDataBase.insert("USUARIOS",null,cv);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateUsuario(Usuario usuario){
        try{
            ContentValues cv = new ContentValues();
            cv.put("USUADES", usuario.getUSUADES());
            //cv.put("USUACLAV", usuario.getUSUACLAV());
            cv.put("USUAMAIL", usuario.getUSUAMAIL());
            cv.put("USUAINA", usuario.getUSUAINA());
            DataBaseHelper.myDataBase.update("USUARIOS",cv,"USUACOD = ?", new String[]{String.valueOf(usuario.getUSUACOD())});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteUsuario(Usuario usuario){
        try{
            DataBaseHelper.myDataBase.delete("USUARIOS","USUACOD = ?", new String[]{String.valueOf(usuario.getUSUACOD())});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
