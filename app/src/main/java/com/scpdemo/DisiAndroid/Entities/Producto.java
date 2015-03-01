package com.scpdemo.DisiAndroid.Entities;

/**
 * Created by Renan on 20/02/2015.
 */
public class Producto {
    private int   PRODCOD;
    private String   PRODMON;
    private String  PRODTIP;
    private String  PRODNOM;
    private String PRODDES;
    private double PRODPRE;
    private int PRODINA;
    private String PRODFTO;

    public Producto(int PRODCOD, String PRODMON, String PRODTIP, String PRODNOM, String PRODDES, double PRODPRE, int PRODINA, String PRODFTO) {
        this.PRODCOD = PRODCOD;
        this.PRODMON = PRODMON;
        this.PRODTIP = PRODTIP;
        this.PRODNOM = PRODNOM;
        this.PRODDES = PRODDES;
        this.PRODPRE = PRODPRE;
        this.PRODINA = PRODINA;
        this.PRODFTO = PRODFTO;
    }

    public int getPRODCOD() {
        return PRODCOD;
    }

    public void setPRODCOD(int PRODCOD) {
        this.PRODCOD = PRODCOD;
    }

    public String getPRODMON() {
        return PRODMON;
    }

    public void setPRODMON(String PRODMON) {
        this.PRODMON = PRODMON;
    }

    public String getPRODTIP() {
        return PRODTIP;
    }

    public void setPRODTIP(String PRODTIP) {
        this.PRODTIP = PRODTIP;
    }

    public String getPRODNOM() {
        return PRODNOM;
    }

    public void setPRODNOM(String PRODNOM) {
        this.PRODNOM = PRODNOM;
    }

    public String getPRODDES() {
        return PRODDES;
    }

    public void setPRODDES(String PRODDES) {
        this.PRODDES = PRODDES;
    }

    public double getPRODPRE() {
        return PRODPRE;
    }

    public void setPRODPRE(double PRODPRE) {
        this.PRODPRE = PRODPRE;
    }

    public int getPRODINA() {
        return PRODINA;
    }

    public void setPRODINA(int PRODINA) {
        this.PRODINA = PRODINA;
    }

    public String getPRODFTO() {
        return PRODFTO;
    }

    public void setPRODFTO(String PRODFTO) {
        this.PRODFTO = PRODFTO;
    }
}
