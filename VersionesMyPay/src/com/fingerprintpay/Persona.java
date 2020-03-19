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
public class Persona {
    private String nombre;
    private String apellido;
    private char genero;
    private String fechaNac;
    private static final int MANOS = 2;
    private static final int DEDOS = 5;
    private final Huella[][] huellas; 
    
    private Persona(){
        huellas = new Huella[MANOS][DEDOS];
    }
    
    public Persona(String nombre, String apellido, char genero, int diaNac, int mesNac, int anioNac){
        this();
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fechaNac = anioNac+"-"+mesNac+"-"+diaNac;
    }
    
    public void agregarHuella(Dedo dedo, Mano mano){
        if(null == huellas[mano.getConstante()][dedo.getConstante()])
            huellas[mano.getConstante()][dedo.getConstante()] = new Huella(mano, dedo);
        else
            System.out.println("Ya se registro una huella para el dedo " + dedo + " de la mano " + mano);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(int diaNac, int mesNac, int anioNac) {
        this.fechaNac = anioNac+"-"+mesNac+"-"+diaNac;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", apellido=" + apellido + ", genero=" + genero + ", fechaNac=" + fechaNac + '}';
    }
    
}
