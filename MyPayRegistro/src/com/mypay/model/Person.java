/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypay.model;

import java.util.Date;

/**
 *
 * @author luisr
 */
public class Person {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date birthDate ;
    private static final int MANOS = 2;
    private static final int DEDOS = 5;
    private final FingerPrint[][] fingerPrints; 
    
    private Person(){
        fingerPrints = new FingerPrint[MANOS][DEDOS];
    }
    
    public Person(String firstName, String lastName, Gender gender, Date birthDate, FingerPrint fingerPrint){
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.fingerPrints[Hand.RIGHT.getNumHand()][Finger.INDEX.getNumFinger()] = fingerPrint;
    }
    
    public void agregarHuella(Finger finger, Hand hand){
        if(null == fingerPrints[hand.getNumHand()][finger.getNumFinger()])
            fingerPrints[hand.getNumHand()][finger.getNumFinger()] = new FingerPrint(hand, finger);
        else
            System.out.println("Ya se registro una huella para el dedo " + finger + " de la mano " + hand);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + firstName + ", apellido=" + lastName + ", genero=" + gender + ", fechaDeNacimiento=" + birthDate + '}';
    }
    
}
