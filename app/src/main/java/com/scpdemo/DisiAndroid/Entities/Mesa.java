package com.scpdemo.DisiAndroid.Entities;

import java.io.Serializable;

/**
 * Created by Renan on 20/02/2015.
 */
public class Mesa implements Serializable{

    private int  MESACOD;
    private String  MESADES;
    private int MESAINA;
    private int FOTO;

    public Mesa() {   }

    public Mesa(int MESACOD, String MESADES, int MESAINA,int FOTO) {
        this.MESACOD = MESACOD;
        this.MESADES = MESADES;
        this.MESAINA = MESAINA;
        this.FOTO = FOTO;
    }

    public int getMESACOD() {
        return MESACOD;
    }

    public void setMESACOD(int MESACOD) {
        this.MESACOD = MESACOD;
    }

    public String getMESADES() {
        return MESADES;
    }

    public void setMESADES(String MESADES) {
        this.MESADES = MESADES;
    }

    public int getMESAINA() {
        return MESAINA;
    }

    public void setMESAINA(int MESAINA) {
        this.MESAINA = MESAINA;
    }

    public int getFOTO() {
        return FOTO;
    }

    public void setFOTO(int FOTO) {
        this.FOTO = FOTO;
    }

}
