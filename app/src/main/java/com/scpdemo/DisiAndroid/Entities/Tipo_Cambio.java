package com.scpdemo.DisiAndroid.Entities;

/**
 * Created by Renan on 20/02/2015.
 */
public class Tipo_Cambio {

  private int  TIPCCOD;
    private String  TIPCFEC;
    private String  TIPCMON;
    private String  TIPCCOM;
    private String TIPCVEN;
    private int TIPCINA;

    public Tipo_Cambio(int TIPCCOD, String TIPCFEC, String TIPCMON, String TIPCCOM, String TIPCVEN, int TIPCINA) {
        this.TIPCCOD = TIPCCOD;
        this.TIPCFEC = TIPCFEC;
        this.TIPCMON = TIPCMON;
        this.TIPCCOM = TIPCCOM;
        this.TIPCVEN = TIPCVEN;
        this.TIPCINA = TIPCINA;
    }

    public int getTIPCCOD() {
        return TIPCCOD;
    }

    public void setTIPCCOD(int TIPCCOD) {
        this.TIPCCOD = TIPCCOD;
    }

    public String getTIPCFEC() {
        return TIPCFEC;
    }

    public void setTIPCFEC(String TIPCFEC) {
        this.TIPCFEC = TIPCFEC;
    }

    public String getTIPCMON() {
        return TIPCMON;
    }

    public void setTIPCMON(String TIPCMON) {
        this.TIPCMON = TIPCMON;
    }

    public String getTIPCCOM() {
        return TIPCCOM;
    }

    public void setTIPCCOM(String TIPCCOM) {
        this.TIPCCOM = TIPCCOM;
    }

    public String getTIPCVEN() {
        return TIPCVEN;
    }

    public void setTIPCVEN(String TIPCVEN) {
        this.TIPCVEN = TIPCVEN;
    }

    public int getTIPCINA() {
        return TIPCINA;
    }

    public void setTIPCINA(int TIPCINA) {
        this.TIPCINA = TIPCINA;
    }
}
