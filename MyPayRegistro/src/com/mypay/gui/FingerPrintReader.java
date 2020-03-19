package com.mypay.gui;

import java.awt.Frame;

import javax.swing.JOptionPane;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.mypay.controller.Controller;

public class FingerPrintReader extends DigitalPersona{
	private DPFPEnrollment enroller = DPFPGlobal.getEnrollmentFactory().createEnrollment();
	
	private FingerPrintListener listener;
	
	public FingerPrintReader(Frame owner) {
		super(owner);
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
	
	//Esta funcion se llama cuando el sensor lee una huella
	@Override 
	public void process(DPFPSample sample) {
		super.process(sample);
		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

		// Check quality of the sample and add to enroller if it's good
		if (features != null) try
		{
			makeReport("The fingerprint feature set was created.");
			enroller.addFeatures(features);		// Add feature set to template.
		}
		catch (DPFPImageQualityException ex) { }
		finally {
			updateStatus();
			// Check if template has been created.
			switch(enroller.getTemplateStatus())
			{
				case TEMPLATE_STATUS_READY:	// report success and stop capturing
					stop();
					Controller.setTemplate(enroller.getTemplate());
					if(null!=listener) {
						FingerPrintEvent ev = new FingerPrintEvent(FingerPrintReader.this);
						listener.fingerPrintReadyToSend(ev);
					}
					this.setVisible(false);
					setPrompt("Click Close, and then click Fingerprint Verification.");
					break;

				case TEMPLATE_STATUS_FAILED:	// report failure and restart capturing
					enroller.clear();
					stop();
					updateStatus();
					Controller.setTemplate(null);
					JOptionPane.showMessageDialog(FingerPrintReader.this, "The fingerprint template is not valid. Repeat fingerprint enrollment.", "Fingerprint Enrollment", JOptionPane.ERROR_MESSAGE);
					start();
					break;
			case TEMPLATE_STATUS_INSUFFICIENT:
				break;
			case TEMPLATE_STATUS_UNKNOWN:
				break;
			default:
				break;

			}

		}
	}
	
	private void updateStatus()
	{
		// Show number of samples needed.
		setStatus(String.format("Fingerprint samples needed: %1$s", enroller.getFeaturesNeeded()));
	}
	
}
