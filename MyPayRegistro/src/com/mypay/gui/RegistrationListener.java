package com.mypay.gui;

import java.util.EventListener;

public interface RegistrationListener extends EventListener{
	public void registrationEventOcurred(RegistrationEvent ev);
	public void registrarHuellaEventOcurred(RegistrarHuellaEvent ev);
}
