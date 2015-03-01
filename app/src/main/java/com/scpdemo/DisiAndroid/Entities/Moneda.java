package com.scpdemo.DisiAndroid.Entities;

/**
 * Created by Renan on 20/02/2015.
 */
public class Moneda {
    private int  MONCOD;
    private String  MONDESC;
    private String  MONABRE;

    public Moneda(int MONCOD, String MONDESC, String MONABRE) {
        this.MONCOD = MONCOD;
        this.MONDESC = MONDESC;
        this.MONABRE = MONABRE;
    }

    public int getMONCOD() {
        return MONCOD;
    }

    public void setMONCOD(int MONCOD) {
        this.MONCOD = MONCOD;
    }

    public String getMONDESC() {
        return MONDESC;
    }

    public void setMONDESC(String MONDESC) {
        this.MONDESC = MONDESC;
    }

    public String getMONABRE() {
        return MONABRE;
    }

    public void setMONABRE(String MONABRE) {
        this.MONABRE = MONABRE;
    }
}
