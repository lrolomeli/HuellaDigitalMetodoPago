package com.mypay.gui;

import java.util.EventObject;

public class MyPayEvent extends EventObject{

	private Double bill;
	
	private MyPayEvent(Object source) {
		super(source);
	}
	
	public MyPayEvent(Object source, Double bill) {
		super(source);
		this.bill = bill;
	}

	public Double getBill() {
		return bill;
	}

	public void setBill(Double bill) {
		this.bill = bill;
	}
	
}
