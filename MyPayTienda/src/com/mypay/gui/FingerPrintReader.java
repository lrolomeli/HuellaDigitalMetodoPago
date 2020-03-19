package com.mypay.gui;

import java.awt.Frame;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.verification.*;
import com.mypay.controller.Controller;

public class FingerPrintReader extends DigitalPersona{
	private DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();
	private VerifiedUserListener listener;
	
	public FingerPrintReader(Frame owner) {
		super(owner);
	}
	
	public void setVerifiedUserListener(VerifiedUserListener listener) {
		this.listener = listener;
	}
	
	@Override 
	public void init()
	{
		super.init();
		updateStatus(0);
	}

	//Esta funcion se llama cuando el sensor lee una huella
	@Override 
	public void process(DPFPSample sample) {
		super.process(sample);
		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

		// Check quality of the sample and start verification if it's good
		if (features != null)
		{
			// Compare the feature set with our template
			DPFPVerificationResult result = verificator.verify(features, Controller.getTemplate());
			updateStatus(result.getFalseAcceptRate());
			
			if (result.isVerified()) {
				super.stop();
				makeReport("Huella aceptada");
				if(null != listener) {

					VerifiedUserEvent vuev = new VerifiedUserEvent(this);
					try {
						listener.usuarioVerificado(vuev);
					} catch (Exception e) {
						makeReport("No se ha podido concretar la transaccion");
					}
				}
			}
			else {
				makeReport("Huella NO aceptada");
			}
		}
	}
	
	private void updateStatus(int FAR)
	{
		// Show "False accept rate" value
		setStatus(String.format("False Accept Rate (FAR) = %1$s", FAR));
	}
	
}
