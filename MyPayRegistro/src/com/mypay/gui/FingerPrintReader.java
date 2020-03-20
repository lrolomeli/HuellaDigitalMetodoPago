package com.mypay.gui;

import java.awt.Frame;

import javax.swing.JOptionPane;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.processing.DPFPTemplateStatus;
import com.mypay.controller.Controller;

public class FingerPrintReader extends DigitalPersona{
	private DPFPEnrollment enroller = DPFPGlobal.getEnrollmentFactory().createEnrollment();
	
	private FingerPrintListener listener;
	
	public FingerPrintReader(Frame owner) {
		super(owner);
		super.init();
	}
	
	public void setFingerPrintListener(FingerPrintListener listener) {
		this.listener = listener;
	}
	
	@Override 
	public void init()
	{
		super.init();
		this.setTitle("Registra tu huella");
		updateStatus();
	}
	
	public void start() {
		super.start();
		updateStatus();
	}
	
	//Esta funcion se llama cuando el sensor lee una huella
	@Override 
	public void process(DPFPSample sample) {
		super.process(sample);
		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

		// Check quality of the sample and add to enroller if it's good
		if (features != null) try
		{
			makeReport("Se ha creado correctamente el registro de tu huella.");
			enroller.addFeatures(features);		// Add feature set to template.
		}
		catch (DPFPImageQualityException ex) { }
		finally {
			updateStatus();
			if(enroller.getTemplateStatus()==DPFPTemplateStatus.TEMPLATE_STATUS_READY) {
				stop();
				Controller.setTemplate(enroller.getTemplate());
				
				if(null!=listener) {
					enroller.clear();
					updateStatus();
					FingerPrintEvent ev = new FingerPrintEvent(FingerPrintReader.this);
					listener.fingerPrintReadyToSend(ev);
				}
			}
			else if(enroller.getTemplateStatus()==DPFPTemplateStatus.TEMPLATE_STATUS_FAILED){
				enroller.clear();
				stop();
				updateStatus();
				Controller.setTemplate(null);
				JOptionPane.showMessageDialog(FingerPrintReader.this, "La huella no corresponde o no se capturo bien. Intenta otra vez.", "Registro de Huella", JOptionPane.ERROR_MESSAGE);
				start();
			}

		}
	}
	
	public void clean() {
		super.clean();
		enroller.clear();
		updateStatus();
		Controller.setTemplate(null);
	}
	
	private void updateStatus()
	{
		// Show number of samples needed.
		setStatus(String.format("Fingerprint samples needed: %1$s", enroller.getFeaturesNeeded()));
	}
	
}
