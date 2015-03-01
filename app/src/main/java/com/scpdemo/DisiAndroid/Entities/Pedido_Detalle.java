package com.scpdemo.DisiAndroid.Entities;

/**
 * Created by Renan on 20/02/2015.
 */
public class Pedido_Detalle {

    private int   DETPITE;
    private String   DETPPED;
    private String  DETPPRO;
    private double  DETPCAN;
    private double DETPPRE;
    private String MESADET;
    private int DETPINA;

    public Pedido_Detalle(int DETPITE, String DETPPED, String DETPPRO, double DETPCAN, double DETPPRE, String MESADET, int DETPINA) {
        this.DETPITE = DETPITE;
        this.DETPPED = DETPPED;
        this.DETPPRO = DETPPRO;
        this.DETPCAN = DETPCAN;
        this.DETPPRE = DETPPRE;
        this.MESADET = MESADET;
        this.DETPINA = DETPINA;
    }

    public int getDETPITE() {
        return DETPITE;
    }

    public void setDETPITE(int DETPITE) {
        this.DETPITE = DETPITE;
    }

    public String getDETPPED() {
        return DETPPED;
    }

    public void setDETPPED(String DETPPED) {
        this.DETPPED = DETPPED;
    }

    public String getDETPPRO() {
        return DETPPRO;
    }

    public void setDETPPRO(String DETPPRO) {
        this.DETPPRO = DETPPRO;
    }

    public double getDETPCAN() {
        return DETPCAN;
    }

    public void setDETPCAN(double DETPCAN) {
        this.DETPCAN = DETPCAN;
    }

    public double getDETPPRE() {
        return DETPPRE;
    }

    public void setDETPPRE(double DETPPRE) {
        this.DETPPRE = DETPPRE;
    }

    public String getMESADET() {
        return MESADET;
    }

    public void setMESADET(String MESADET) {
        this.MESADET = MESADET;
    }

    public int getDETPINA() {
        return DETPINA;
    }

    public void setDETPINA(int DETPINA) {
        this.DETPINA = DETPINA;
    }
}
