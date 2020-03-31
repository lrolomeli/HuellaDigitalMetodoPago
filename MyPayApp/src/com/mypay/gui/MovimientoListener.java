package com.mypay.gui;

import java.util.EventListener;

public interface MovimientoListener  extends EventListener{
	public void transferir(MovimientoEvent moev);
	public void consultarSaldo(MovimientoEvent moev);
}
