package com.scpdemo.DisiAndroid.Entities;

/**
 * Created by Renan on 20/02/2015.
 */
public class Producto_Tipo {

    private int  TIPOCOD;
    private String TIPODES;
    private int TIPOINA;

    public Producto_Tipo(int TIPOCOD, int TIPOINA) {
        this.TIPOCOD = TIPOCOD;
        this.TIPOINA = TIPOINA;
    }

    public int getTIPOCOD() {
        return TIPOCOD;
    }

    public void setTIPOCOD(int TIPOCOD) {
        this.TIPOCOD = TIPOCOD;
    }

    public int getTIPOINA() {
        return TIPOINA;
    }

    public void setTIPOINA(int TIPOINA) {
        this.TIPOINA = TIPOINA;
    }

    public String getTIPODES() {
        return TIPODES;
    }

    public void setTIPODES(String TIPODES) {
        this.TIPODES = TIPODES;
    }
}
