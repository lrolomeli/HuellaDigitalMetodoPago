package com.mypay.gui;

import java.util.EventListener;

public interface FingerPrintListener  extends EventListener {

	public void fingerPrintReadyToSend(FingerPrintEvent vuev);
	
}
