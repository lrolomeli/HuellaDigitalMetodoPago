package com.mypay.gui;

import java.util.EventListener;

public interface MyPayLoginListener extends EventListener{

	public void iniciarSesion(MyPayLoginEvent mplev);
	
}
