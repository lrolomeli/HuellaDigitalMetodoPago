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
public class User extends Person{
    
    private final int userId;
    private String userName;
    private String password;
    private static int userCounter;
    
    public User(String firstName, String lastName, Gender gender,
    		Date birthDate, String userName, String password, FingerPrint fingerPrint){
        super(firstName, lastName, gender, birthDate, fingerPrint);
        this.userId = ++userCounter;
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public static int getUserCount() {
        return userCounter;
    }

    
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
    public String toString() {
        return super.toString() + " Usuario{" + "idUsuario=" + userId + ", nombreUsuario=" + userName + ", contrasena=" + password + '}';
    }
    
}
