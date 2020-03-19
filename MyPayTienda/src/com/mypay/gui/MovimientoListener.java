package com.mypay.gui;

import java.util.EventListener;

public interface MovimientoListener  extends EventListener{
	public void abonar(MovimientoEvent moev);
	public void verificar(MovimientoEvent moev);
}
