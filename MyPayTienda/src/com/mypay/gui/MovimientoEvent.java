package com.mypay.gui;

import java.util.EventObject;

public class MovimientoEvent extends EventObject{

	private int idUsuario;
	private float cantidad;
	private boolean tipo;
	
	
	public MovimientoEvent(Object source) {
		super(source);
	}
	
	public MovimientoEvent(Object source, int idUsuario, float cantidad, boolean tipo) {
		super(source);
		this.idUsuario = idUsuario;
		this.cantidad = cantidad;
		this.tipo = tipo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public float getCantidad() {
		return cantidad;
	}

	public boolean isTipo() {
		return tipo;
	}

	
	
}
