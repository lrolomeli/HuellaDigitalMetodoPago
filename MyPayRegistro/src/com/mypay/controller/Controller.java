package com.mypay.controller;

import java.sql.SQLException;
import java.util.Date;

import com.digitalpersona.onetouch.DPFPTemplate;
import com.mypay.gui.RegistrationEvent;
import com.mypay.model.Database;
import com.mypay.model.FingerPrint;
import com.mypay.model.Gender;
import com.mypay.model.User;

public class Controller {
	Database db = new Database();
	private static DPFPTemplate template;
	
	public FingerPrint getFingerPrint() {
		System.out.println(template.serialize());
		FingerPrint fp = new FingerPrint(template.serialize());
		return fp;
	}
	
	public void registerUser() throws Exception {
		connect();
		db.registerUser(getFingerPrint());
		close();
	}
	
	public void addPerson(RegistrationEvent ev) {
		String firstName = ev.getFirstName();
		String lastName = ev.getLastName();
		Date birthDate = ev.getBirthDate();
		String gender = ev.getGender();
		String userName = ev.getUserName();
		String password = ev.getPassword();
		
		Gender genderCat;
		
		if(gender.equals("Hombre")) {
			genderCat = Gender.MALE;
		}
		else if(gender.equals("Mujer")) {
			genderCat = Gender.FEMALE;
		}
		else {
			genderCat = Gender.NOT_SPECIFIED;
		}
		
		
		User user = new User(firstName, lastName, genderCat, birthDate, userName, password, getFingerPrint());
		
		db.addUser(user);
		
		//import java.text.SimpleDateFormat;
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//String birthDate = sdf.format(new Date());
		//System.out.println(firstName+" "+lastName+" "+birthDate+" "+gender+" "+userName+" "+password);
		
	}

	public void connect() throws Exception {
		db.connect();
	}
	
	public void close() {
		db.disconnect();
	}
	
	public static DPFPTemplate getTemplate() {
		return template;
	}
	
	public static void setTemplate(DPFPTemplate template) {
		Controller.template = template;
	}
	

	
}
