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
public enum Mano {
    IZQUIERDA(0),
    DERECHA(1);
    
    private final int constante;
    
    private Mano(int constante){
        this.constante = constante;
    }
    
    public int getConstante(){
        return constante;
    }
    
}
