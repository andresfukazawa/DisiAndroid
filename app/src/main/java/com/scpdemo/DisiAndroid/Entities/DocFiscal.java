package com.scpdemo.DisiAndroid.Entities;

/**
 * Created by Renan on 20/02/2015.
 */
public class DocFiscal {

    private int   DOCFCOD;
    private int   DOCFCLI ;
    private int   DOCFSER ;
    private String   DOCFNUM ;
    private String   DOCFFEC ;
    private String   DOCFMON ;
    private double DOCFSUB ;
    private double DOCFIGV ;
    private double DOCFTOT ;
    private int DOCFEST ;
    private String DOCFECR ;
    private String DOCUSER ;
    private double DOCTIPCA ;
    private int DOCFPAG;

    public DocFiscal(int DOCFCOD, int DOCFCLI, int DOCFSER, String DOCFNUM, String DOCFFEC, String DOCFMON, double DOCFSUB, double DOCFIGV, double DOCFTOT, int DOCFEST, String DOCFECR, String DOCUSER, double DOCTIPCA, int DOCFPAG) {
        this.DOCFCOD = DOCFCOD;
        this.DOCFCLI = DOCFCLI;
        this.DOCFSER = DOCFSER;
        this.DOCFNUM = DOCFNUM;
        this.DOCFFEC = DOCFFEC;
        this.DOCFMON = DOCFMON;
        this.DOCFSUB = DOCFSUB;
        this.DOCFIGV = DOCFIGV;
        this.DOCFTOT = DOCFTOT;
        this.DOCFEST = DOCFEST;
        this.DOCFECR = DOCFECR;
        this.DOCUSER = DOCUSER;
        this.DOCTIPCA = DOCTIPCA;
        this.DOCFPAG = DOCFPAG;
    }

    public int getDOCFCOD() {
        return DOCFCOD;
    }

    public void setDOCFCOD(int DOCFCOD) {
        this.DOCFCOD = DOCFCOD;
    }

    public int getDOCFCLI() {
        return DOCFCLI;
    }

    public void setDOCFCLI(int DOCFCLI) {
        this.DOCFCLI = DOCFCLI;
    }

    public int getDOCFSER() {
        return DOCFSER;
    }

    public void setDOCFSER(int DOCFSER) {
        this.DOCFSER = DOCFSER;
    }

    public String getDOCFNUM() {
        return DOCFNUM;
    }

    public void setDOCFNUM(String DOCFNUM) {
        this.DOCFNUM = DOCFNUM;
    }

    public String getDOCFFEC() {
        return DOCFFEC;
    }

    public void setDOCFFEC(String DOCFFEC) {
        this.DOCFFEC = DOCFFEC;
    }

    public String getDOCFMON() {
        return DOCFMON;
    }

    public void setDOCFMON(String DOCFMON) {
        this.DOCFMON = DOCFMON;
    }

    public double getDOCFSUB() {
        return DOCFSUB;
    }

    public void setDOCFSUB(double DOCFSUB) {
        this.DOCFSUB = DOCFSUB;
    }

    public double getDOCFIGV() {
        return DOCFIGV;
    }

    public void setDOCFIGV(double DOCFIGV) {
        this.DOCFIGV = DOCFIGV;
    }

    public double getDOCFTOT() {
        return DOCFTOT;
    }

    public void setDOCFTOT(double DOCFTOT) {
        this.DOCFTOT = DOCFTOT;
    }

    public int getDOCFEST() {
        return DOCFEST;
    }

    public void setDOCFEST(int DOCFEST) {
        this.DOCFEST = DOCFEST;
    }

    public String getDOCFECR() {
        return DOCFECR;
    }

    public void setDOCFECR(String DOCFECR) {
        this.DOCFECR = DOCFECR;
    }

    public String getDOCUSER() {
        return DOCUSER;
    }

    public void setDOCUSER(String DOCUSER) {
        this.DOCUSER = DOCUSER;
    }

    public double getDOCTIPCA() {
        return DOCTIPCA;
    }

    public void setDOCTIPCA(double DOCTIPCA) {
        this.DOCTIPCA = DOCTIPCA;
    }

    public int getDOCFPAG() {
        return DOCFPAG;
    }

    public void setDOCFPAG(int DOCFPAG) {
        this.DOCFPAG = DOCFPAG;
    }
}
