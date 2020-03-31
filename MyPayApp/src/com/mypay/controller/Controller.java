package com.mypay.controller;


import java.sql.SQLException;

import com.mypay.gui.MovimientoEvent;
import com.mypay.gui.MyPayLoginEvent;
import com.mypay.model.Database;

public class Controller {
	private Database db = new Database();
	
	public Controller() {

	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void close() {
		db.disconnect();
	}
	
	public String login(MyPayLoginEvent mplev) throws SQLException {
		return db.login(mplev.getAccount(), mplev.getPassword()); 
		
	}
	
	public Float consultarSaldo(MovimientoEvent moev) throws SQLException {
		return db.getSaldo(moev.getIdUsuario());
	}
	
	public boolean transferir(MovimientoEvent moev) throws SQLException {
		int cuentaDestino = moev.getCuentaDestino();
		float cantidad = moev.getCantidad();
		int idUsuario = moev.getIdUsuario();
		
		Float saldoDest = db.getSaldo(cuentaDestino);
		if(null==saldoDest) return false;
		
		Float saldoSrc = db.getSaldo(idUsuario);
		if(null==saldoSrc) return false;
		if(cantidad>saldoSrc.floatValue())return false;
		
		return db.transfer(saldoSrc.floatValue(), saldoDest.floatValue(), cantidad, idUsuario, cuentaDestino);
	}
	
}
