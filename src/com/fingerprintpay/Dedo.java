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
public enum Dedo{
    INDICE(0),
    PULGAR(1),
    MEDIO(2),
    ANULAR(3),
    MENIQUE(4);
    
    private final int constante;
    
    private Dedo(int constante){
        this.constante = constante;
    }
    
    public int getConstante(){
        return constante;
    }
    
}
