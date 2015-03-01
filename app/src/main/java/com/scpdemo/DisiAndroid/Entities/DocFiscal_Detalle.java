package com.scpdemo.DisiAndroid.Entities;

/**
 * Created by Renan on 20/02/2015.
 */
public class DocFiscal_Detalle {

    private int  DETITEM;
    private int  DETDDOC;
    private int  DETDPED;
    private int  DETDITE;
    private int  DETDPRO;
    private double DETDCAN;
    private double DETDPRE;
    private double DETDIMP;
    private int DETDINA;

    public DocFiscal_Detalle(int DETITEM, int DETDDOC, int DETDPED, int DETDITE, int DETDPRO, double DETDCAN, double DETDPRE, double DETDIMP, int DETDINA) {
        this.DETITEM = DETITEM;
        this.DETDDOC = DETDDOC;
        this.DETDPED = DETDPED;
        this.DETDITE = DETDITE;
        this.DETDPRO = DETDPRO;
        this.DETDCAN = DETDCAN;
        this.DETDPRE = DETDPRE;
        this.DETDIMP = DETDIMP;
        this.DETDINA = DETDINA;
    }

    public int getDETITEM() {
        return DETITEM;
    }

    public void setDETITEM(int DETITEM) {
        this.DETITEM = DETITEM;
    }

    public int getDETDDOC() {
        return DETDDOC;
    }

    public void setDETDDOC(int DETDDOC) {
        this.DETDDOC = DETDDOC;
    }

    public int getDETDPED() {
        return DETDPED;
    }

    public void setDETDPED(int DETDPED) {
        this.DETDPED = DETDPED;
    }

    public int getDETDITE() {
        return DETDITE;
    }

    public void setDETDITE(int DETDITE) {
        this.DETDITE = DETDITE;
    }

    public int getDETDPRO() {
        return DETDPRO;
    }

    public void setDETDPRO(int DETDPRO) {
        this.DETDPRO = DETDPRO;
    }

    public double getDETDCAN() {
        return DETDCAN;
    }

    public void setDETDCAN(double DETDCAN) {
        this.DETDCAN = DETDCAN;
    }

    public double getDETDPRE() {
        return DETDPRE;
    }

    public void setDETDPRE(double DETDPRE) {
        this.DETDPRE = DETDPRE;
    }

    public double getDETDIMP() {
        return DETDIMP;
    }

    public void setDETDIMP(double DETDIMP) {
        this.DETDIMP = DETDIMP;
    }

    public int getDETDINA() {
        return DETDINA;
    }

    public void setDETDINA(int DETDINA) {
        this.DETDINA = DETDINA;
    }
}
