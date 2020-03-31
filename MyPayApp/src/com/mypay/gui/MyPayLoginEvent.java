package com.mypay.gui;

import java.util.EventObject;

public class MyPayLoginEvent extends EventObject{
	
	private int account;
	private String password;

	public MyPayLoginEvent(Object source) {
		super(source);
		
	}
	
	public MyPayLoginEvent(Object source, int account, String password) {
		super(source);
		this.account = account;
		this.password = password;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	
	
}
