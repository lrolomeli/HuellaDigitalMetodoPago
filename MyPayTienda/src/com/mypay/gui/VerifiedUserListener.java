package com.mypay.gui;

import java.util.EventListener;

public interface VerifiedUserListener  extends EventListener {

	public void usuarioVerificado(VerifiedUserEvent vuev);
	
}
