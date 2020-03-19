/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fingerprintpay;
/**
 *
 * @author luisr
 */
public class Huella {
    private Dedo dedo;
    private Mano mano;
    private static final int MUESTRAS_HUELLA = 100;
    private byte[] huella;
    private static byte[] muestra;
    
    private Huella(){
        this.huella = new byte[MUESTRAS_HUELLA];
        muestra = new byte[MUESTRAS_HUELLA];
    }
    
    public Huella(Mano mano, Dedo dedo){
        this();
        this.mano = mano;
        this.dedo = dedo;
    }
    
    public void setHuella(boolean boolMuestra){
        if(boolMuestra){
            System.arraycopy(Huella.muestra, 0, this.huella, 0, MUESTRAS_HUELLA);
            System.out.println("Huella Agregada.");
        }
        else{
            System.out.println("No se agrego la huella, intente otra vez.");
        }
    }

    public Dedo getDedo() {
        return dedo;
    }

    public void setDedo(Dedo dedo) {
        this.dedo = dedo;
    }

    public Mano getMano() {
        return mano;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }

}