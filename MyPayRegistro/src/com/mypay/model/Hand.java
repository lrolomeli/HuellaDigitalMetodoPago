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
public enum Hand {
    LEFT(0),
    RIGHT(1);
    
    private final int numHand;
    
    private Hand(int numHand){
        this.numHand = numHand;
    }
    
    public int getNumHand(){
        return numHand;
    }
    
}
