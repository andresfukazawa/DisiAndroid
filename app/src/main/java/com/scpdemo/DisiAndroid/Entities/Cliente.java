package com.scpdemo.DisiAndroid.Entities;

/**
 * Created by Renan on 20/02/2015.
 */
public class Cliente {
    private int    IDCLIET;
    private String CLIERUC;
    private String CLIENOM;
    private String CLIEFECR;
    private String CLIEUSUR;
    private int CLIEINA;

    public Cliente(int IDCLIET, String CLIERUC, String CLIENOM, String CLIEFECR, String CLIEUSUR, int CLIEINA) {
        this.IDCLIET = IDCLIET;
        this.CLIERUC = CLIERUC;
        this.CLIENOM = CLIENOM;
        this.CLIEFECR = CLIEFECR;
        this.CLIEUSUR = CLIEUSUR;
        this.CLIEINA = CLIEINA;
    }

    public int getIDCLIET() {
        return IDCLIET;
    }

    public void setIDCLIET(int IDCLIET) {
        this.IDCLIET = IDCLIET;
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
}
