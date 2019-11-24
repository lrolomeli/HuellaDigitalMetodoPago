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
public class Usuario extends Persona{
    
    private final int idUsuario;
    private String nombreUsuario;
    private String pwd;
    private static int contadorUsuarios;
    
    public Usuario(String nombre, String apellido, char genero, int diaNac, int mesNac, int anioNac, String nombreUsuario, String pwd){
        super(nombre, apellido, genero, diaNac, mesNac, anioNac);
        this.idUsuario = ++contadorUsuarios;
        this.nombreUsuario = nombreUsuario;
        this.pwd = pwd;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public static int getContadorUsuarios() {
        return contadorUsuarios;
    }

    @Override
    public String toString() {
        return super.toString() + " Usuario{" + "idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", password=" + pwd + '}';
    }
    
}
