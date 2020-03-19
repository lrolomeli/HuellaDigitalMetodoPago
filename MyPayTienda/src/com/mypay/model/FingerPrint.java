/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypay.model;
/**
 *
 * @author luisr
 */
public class FingerPrint {
    private Finger finger;
    private Hand hand;
    private static final int FINGER_PRINT_SAMPLES = 100;
    private byte[] fingerPrint;
    private static byte[] sample;
    
    private FingerPrint(){
        this.fingerPrint = new byte[FINGER_PRINT_SAMPLES];
        sample = new byte[FINGER_PRINT_SAMPLES];
    }
    
    public FingerPrint(byte[] fingerPrint){
        this.fingerPrint = fingerPrint;
    }
    
    public FingerPrint(Hand hand, Finger finger){
        this();
        this.hand = hand;
        this.finger = finger;
    }
    
    public void setHuella(boolean boolMuestra){
        if(boolMuestra){
            System.arraycopy(FingerPrint.sample, 0, this.fingerPrint, 0, FINGER_PRINT_SAMPLES);
            System.out.println("Huella Agregada.");
        }
        else{
            System.out.println("No se agrego la huella, intente otra vez.");
        }
    }

    public Finger getDedo() {
        return finger;
    }

    public void setDedo(Finger dedo) {
        this.finger = dedo;
    }

    public Hand getMano() {
        return hand;
    }

    public void setMano(Hand mano) {
        this.hand = mano;
    }

}