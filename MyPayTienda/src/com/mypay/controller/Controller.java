package com.mypay.controller;


import java.sql.SQLException;

import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.mypay.gui.MovimientoEvent;
import com.mypay.model.Database;
import com.mypay.gui.FingerPrintReader;
import com.mypay.gui.VerifiedUserEvent;
import com.mypay.gui.VerifiedUserListener;

public class Controller {
	private Database db = new Database();
	private static DPFPTemplate template=null;
	private int idUsuario;
	private float cantidad;
	private boolean tipo;
	
	public Controller() {

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
	
	public void verificar(MovimientoEvent moev) throws Exception {
		
		idUsuario = moev.getIdUsuario();
		cantidad = moev.getCantidad();
		tipo = moev.isTipo();
		byte[] data = db.getFingerPrint(idUsuario);
		template = DPFPGlobal.getTemplateFactory().createTemplate();
		template.deserialize(data);
		
	}
	
	public boolean cobrar() throws Exception {		
		Float saldo = db.getSaldo(idUsuario);
		if(null!=saldo) {
			if(saldo.floatValue() > cantidad) {
				db.transaction(idUsuario, saldo.floatValue(), cantidad, tipo);
			}
			else {
				return false;
			}
				
		}
		return true;	
			
	}
	
	public void abonar(MovimientoEvent moev) throws Exception {
		
		idUsuario = moev.getIdUsuario();
		cantidad = moev.getCantidad();
		tipo = moev.isTipo();
		//int tipo = (moev.isTipo()) ? 1 : 0;
		Float saldo = db.getSaldo(idUsuario);
		if(null!=saldo)
			db.transaction(idUsuario, saldo.floatValue(), cantidad, tipo);
		
	}
	
}
