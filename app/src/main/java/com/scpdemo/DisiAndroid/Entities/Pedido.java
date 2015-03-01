package com.scpdemo.DisiAndroid.Entities;

/**
 * Created by Renan on 20/02/2015.
 */
public class Pedido {

private int PEDICOD;
    private int PEDCLIE;
    private String PEDIFEC;
    private String PEDIUSU;
    private String PEDIVEN;
    private int PEDINAC;

    public Pedido(int PEDICOD, int PEDCLIE, String PEDIFEC, String PEDIUSU, String PEDIVEN, int PEDINAC) {
        this.PEDICOD = PEDICOD;
        this.PEDCLIE = PEDCLIE;
        this.PEDIFEC = PEDIFEC;
        this.PEDIUSU = PEDIUSU;
        this.PEDIVEN = PEDIVEN;
        this.PEDINAC = PEDINAC;
    }

    public int getPEDICOD() {
        return PEDICOD;
    }

    public void setPEDICOD(int PEDICOD) {
        this.PEDICOD = PEDICOD;
    }

    public int getPEDCLIE() {
        return PEDCLIE;
    }

    public void setPEDCLIE(int PEDCLIE) {
        this.PEDCLIE = PEDCLIE;
    }

    public String getPEDIFEC() {
        return PEDIFEC;
    }

    public void setPEDIFEC(String PEDIFEC) {
        this.PEDIFEC = PEDIFEC;
    }

    public String getPEDIUSU() {
        return PEDIUSU;
    }

    public void setPEDIUSU(String PEDIUSU) {
        this.PEDIUSU = PEDIUSU;
    }

    public String getPEDIVEN() {
        return PEDIVEN;
    }

    public void setPEDIVEN(String PEDIVEN) {
        this.PEDIVEN = PEDIVEN;
    }

    public int getPEDINAC() {
        return PEDINAC;
    }

    public void setPEDINAC(int PEDINAC) {
        this.PEDINAC = PEDINAC;
    }
}
