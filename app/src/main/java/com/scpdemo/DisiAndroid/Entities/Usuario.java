package com.scpdemo.DisiAndroid.Entities;

import java.io.Serializable;

/**
 * Created by Renan on 20/02/2015.
 */
public class Usuario implements Serializable {

    private int  USUACOD;
    private String  USUADES;
    private String  USUACLAV;
    private String USUAMAIL;
    private int  USUAINA;
    private int  FOTO;

    public Usuario() {
    }

    public Usuario(String USUADES) {
        this.USUADES = USUADES;
    }

    public Usuario(int USUACOD, String USUADES, String USUACLAV, String USUAMAIL, int USUAINA,int FOTO) {
        this.USUACOD = USUACOD;
        this.USUADES = USUADES;
        this.USUACLAV = USUACLAV;
        this.USUAMAIL = USUAMAIL;
        this.USUAINA = USUAINA;
        this.FOTO=FOTO;
    }

    public int getUSUACOD() {
        return USUACOD;
    }

    public void setUSUACOD(int USUACOD) {
        this.USUACOD = USUACOD;
    }

    public String getUSUADES() {
        return USUADES;
    }

    public void setUSUADES(String USUADES) {
        this.USUADES = USUADES;
    }

    public String getUSUACLAV() {
        return USUACLAV;
    }

    public void setUSUACLAV(String USUACLAV) {
        this.USUACLAV = USUACLAV;
    }

    public String getUSUAMAIL() {
        return USUAMAIL;
    }

    public void setUSUAMAIL(String USUAMAIL) {
        this.USUAMAIL = USUAMAIL;
    }

    public int getUSUAINA() {
        return USUAINA;
    }

    public void setUSUAINA(int USUAINA) {
        this.USUAINA = USUAINA;
    }

    public int getFOTO() {
        return FOTO;
    }

    public void setFOTO(int FOTO) {
        this.FOTO = FOTO;
    }
}

