package com.scpdemo.DisiAndroid.Entities;

/**
 * Created by Renan on 20/02/2015.
 */
public class Pedido_Detalle {
    private int DETPITE;
    private int DETPPED;
    private int DETPPRO;
    private double DETPCAN;
    private double DETPPRE;
    private int DETMESA;
    private String DETFECHA;
    private int DETESTAD;
    private String MESADES;
    private String PRODNOM;



    public String getMESADES() {
        return MESADES;
    }

/*
    public Pedido_Detalle(int DETPITE, int DETPPED, int DETPPRO, double DETPCAN, double DETPPRE, int DETMESA, String DETFECHA, int DETESTAD) {
        this.DETPITE = DETPITE;
        this.DETPPED = DETPPED;
        this.DETPPRO = DETPPRO;
        this.DETPCAN = DETPCAN;
        this.DETPPRE = DETPPRE;
        this.DETMESA = DETMESA;
        this.DETFECHA = DETFECHA;
        this.DETESTAD = DETESTAD;
    }*/

    public Pedido_Detalle(int DETPITE, int DETPPED, int DETPPRO,
                          double DETPCAN,double DETPPRE, int DETMESA,
                          String DETFECHA, int DETESTAD, String MESADES,
                          String PRODNOM) {
        this.DETPITE = DETPITE;
        this.DETPPED = DETPPED;
        this.DETPPRO = DETPPRO;
        this.DETPCAN = DETPCAN;
        this.DETPPRE = DETPPRE;
        this.DETMESA = DETMESA;
        this.DETFECHA = DETFECHA;
        this.DETESTAD = DETESTAD;
        this.MESADES = MESADES;
        this.PRODNOM = PRODNOM;
    }

    public Pedido_Detalle(int DETPPED) {
        this.DETPPED = DETPPED;
    }

    public Pedido_Detalle() {

    }


    public void setMESADES(String MESADES) {
        this.MESADES = MESADES;
    }

    public String getPRODNOM() {
        return PRODNOM;
    }

    public void setPRODNOM(String PRODNOM) {
        this.PRODNOM = PRODNOM;
    }



    public int getDETPITE() {
        return DETPITE;
    }

    public void setDETPITE(int DETPITE) {
        this.DETPITE = DETPITE;
    }

    public int getDETPPED() {
        return DETPPED;
    }

    public void setDETPPED(int DETPPED) {
        this.DETPPED = DETPPED;
    }

    public int getDETPPRO() {
        return DETPPRO;
    }

    public void setDETPPRO(int DETPPRO) {
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

    public int getDETMESA() {
        return DETMESA;
    }

    public void setDETMESA(int DETMESA) {
        this.DETMESA = DETMESA;
    }

    public String getDETFECHA() {
        return DETFECHA;
    }

    public void setDETFECHA(String DETFECHA) {
        this.DETFECHA = DETFECHA;
    }

    public int getDETESTAD() {
        return DETESTAD;
    }

    public void setDETESTAD(int DETESTAD) {
        this.DETESTAD = DETESTAD;
    }
}