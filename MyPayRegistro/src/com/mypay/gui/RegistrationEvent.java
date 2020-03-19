package com.mypay.gui;

import java.util.Date;
import java.util.EventObject;

public class RegistrationEvent extends EventObject {

	private String firstName;
	private String lastName;
	private Date birthDate;
	private String gender;
	private String userName;
	private String password;
	
	public RegistrationEvent(Object source) {
		super(source);
	}
	
	public RegistrationEvent(Object source, String firstName, String lastName,
			Date birthDate, String gender, String userName, String password) {
		super(source);
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.userName = userName;
		this.password = password;
		
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

}
