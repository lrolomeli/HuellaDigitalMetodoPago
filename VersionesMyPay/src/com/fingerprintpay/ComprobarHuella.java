package com.fingerprintpay;

import java.util.EventObject;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ComprobarHuella implements TemplateListener{

	private VerificationForm vform;
	private FingerPrintReader fpr;
	
	public ComprobarHuella(JFrame owner){
		
		fpr = new FingerPrintReader(this);
		vform = new VerificationForm(owner, fpr);
		vform.setVisible(true);
	}

	@Override
	public void enteradoCambioHuella(EventObject e) {
		if(e instanceof TemplateEvent) {
			vform.setVisible(false);
			JOptionPane.showMessageDialog(null, "Huella Asociada", "MyPay Agregar Huella",
					JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	
}