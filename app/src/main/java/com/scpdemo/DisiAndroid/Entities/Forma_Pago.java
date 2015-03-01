package com.scpdemo.DisiAndroid.Entities;

/**
 * Created by Renan on 20/02/2015.
 */
public class Forma_Pago {

    private int   FORPACOD;
    private String   FORPADES;
    private int  FORPAINA;

    public Forma_Pago(int FORPACOD, String FORPADES, int FORPAINA) {
        this.FORPACOD = FORPACOD;
        this.FORPADES = FORPADES;
        this.FORPAINA = FORPAINA;
    }

    public int getFORPACOD() {
        return FORPACOD;
    }

    public void setFORPACOD(int FORPACOD) {
        this.FORPACOD = FORPACOD;
    }

    public String getFORPADES() {
        return FORPADES;
    }

    public void setFORPADES(String FORPADES) {
        this.FORPADES = FORPADES;
    }

    public int getFORPAINA() {
        return FORPAINA;
    }

    public void setFORPAINA(int FORPAINA) {
        this.FORPAINA = FORPAINA;
    }
}
