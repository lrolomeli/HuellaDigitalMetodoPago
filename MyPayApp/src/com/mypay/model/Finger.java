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
public enum Finger{
    INDEX(0),
    THUMB(1),
    MIDDLE(2),
    RING(3),
    PINKY(4);
    
    private final int numfinger;
    
    private Finger(int numfinger){
        this.numfinger = numfinger;
    }
    
    public int getNumFinger(){
        return numfinger;
    }
    
}
