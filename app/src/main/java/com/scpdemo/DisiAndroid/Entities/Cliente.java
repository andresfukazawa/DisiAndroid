package com.scpdemo.DisiAndroid.Entities;

import java.io.Serializable;

/**
 * Created by Renan on 20/02/2015.
 */
public class Cliente {
    private int CLIECOD;
    private String CLIERUC;
    private String CLIENOM;
    private String CLIEFECR;
    private String CLIEUSUR;
    private int CLIEINA;
    private int FOTO;
    public Cliente() {}

    public Cliente(int CLIECOD, String CLIERUC, String CLIENOM,int FOTO) {
        this.CLIECOD = CLIECOD;
        this.CLIERUC = CLIERUC;
        this.CLIENOM = CLIENOM;
        this.FOTO = FOTO;
    }

    public int getCLIECOD() {
        return CLIECOD;
    }

    public void setCLIECOD(int CLIECOD) {
        this.CLIECOD = CLIECOD;
    }

    public String getCLIERUC() {
        return CLIERUC;
    }

    public void setCLIERUC(String CLIERUC) {
        this.CLIERUC = CLIERUC;
    }

    public String getCLIENOM() {
        return CLIENOM;
    }

    public void setCLIENOM(String CLIENOM) {
        this.CLIENOM = CLIENOM;
    }

    public String getCLIEFECR() {
        return CLIEFECR;
    }

    public void setCLIEFECR(String CLIEFECR) {
        this.CLIEFECR = CLIEFECR;
    }

    public String getCLIEUSUR() {
        return CLIEUSUR;
    }

    public void setCLIEUSUR(String CLIEUSUR) {
        this.CLIEUSUR = CLIEUSUR;
    }

    public int getCLIEINA() {
        return CLIEINA;
    }

    public void setCLIEINA(int CLIEINA) {
        this.CLIEINA = CLIEINA;
    }

    public int getFOTO() {
        return FOTO;
    }

    public void setFOTO(int FOTO) {
        this.FOTO = FOTO;
    }
}

