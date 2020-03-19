package com.fingerprintpay;

import java.util.EventObject;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AsociarHuella implements TemplateListener{

	private EnrollmentForm eform;
	private FingerPrintReader fpr;
	
	public AsociarHuella(JFrame owner){
		
		fpr = new FingerPrintReader(this);
		eform = new EnrollmentForm(owner, fpr);
		eform.setVisible(true);
	}

	@Override
	public void enteradoCambioHuella(EventObject e) {
		if(e instanceof TemplateEvent) {
			eform.setVisible(false);
			JOptionPane.showMessageDialog(null, "Huella Asociada", "MyPay Agregar Huella",
					JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	
}
