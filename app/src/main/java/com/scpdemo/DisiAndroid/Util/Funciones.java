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

          /*
        //Mostrar un mensaje de confirmación antes de realizar el test
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(frm);
        alertDialog.setMessage("¿Esta seguro de registrar?");
        alertDialog.setTitle("Registrar");
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                valorDevuelto=1 ;
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                valorDevuelto=0;
                dialog.dismiss();

            }
        });
        alertDialog.show();
       */

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
