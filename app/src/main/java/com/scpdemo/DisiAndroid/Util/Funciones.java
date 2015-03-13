package com.scpdemo.DisiAndroid.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * Created by rgalvez on 27/02/2015.
 */
public class Funciones{
    Integer valorDevuelto=0;

    public Integer Confirma_Guardar(Context frm){

        AlertDialog.Builder builder =new AlertDialog.Builder(frm);
        builder.setMessage("¿Esta seguro de Guardar?")
                .setTitle("Confirmación")
                .setPositiveButton("Si", new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                        valorDevuelto=1;
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        valorDevuelto=0;
                        dialog.cancel();
                    }
                });
        return valorDevuelto;
    }



}
