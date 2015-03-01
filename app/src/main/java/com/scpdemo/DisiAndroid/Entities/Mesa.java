package com.scpdemo.DisiAndroid.Entities;

import java.io.Serializable;

/**
 * Created by Renan on 20/02/2015.
 */
public class Mesa implements Serializable{

  private int  MESACOD;
  private String  MESADES;
  private int MESAINA;

    public Mesa() {   }

    /*
    public Mesa(String MESADES) {
        this.MESADES = MESADES;
    }*/

    public Mesa(String MESADES) {
        this.MESACOD = MESACOD;
        this.MESADES = MESADES;
        this.MESAINA = MESAINA;
    }

  public Mesa(int MESACOD, String MESADES, int MESAINA) {
        this.MESACOD = MESACOD;
       this.MESADES = MESADES;
       this.MESAINA = MESAINA;
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
}
