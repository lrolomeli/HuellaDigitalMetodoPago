package com.fingerprintpay;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.processing.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


//EnrollmentForm ejecuta los metodos de capture form
//La manera de ejecutarlos es mediante el constructor padre.

//Todavia no entiendo como se mandan llamar los metodos de enrollment form
public class EnrollmentForm extends CaptureForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DPFPEnrollment enroller = DPFPGlobal.getEnrollmentFactory().createEnrollment();
	
	//Dejar esta variable "dinamica"
	private FingerPrintReader fpReader;
	
	public EnrollmentForm(Frame owner, FingerPrintReader fpReader) {
		//Esta es la llamada que inicia el metodo init 
		super(owner);
		this.fpReader = fpReader;
	}
	
	@Override 
	protected void init()
	{
		super.init();
		this.setTitle("Lector de Huellas");
		updateStatus();
	}

	@Override 
	protected void process(DPFPSample sample) {
		super.process(sample);
		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

		// Check quality of the sample and add to enroller if it's good
		if (features != null) try
		{
			makeReport("Agregando caracteristicas de la muestra.");
			enroller.addFeatures(features);		// Add feature set to template.
		}
		catch (DPFPImageQualityException ex) { }
		finally {
			updateStatus();

			// Check if template has been created.
			switch(enroller.getTemplateStatus())
			{
				case TEMPLATE_STATUS_UNKNOWN:	// report success and stop capturing

				break;
				case TEMPLATE_STATUS_INSUFFICIENT:	// report success and stop capturing

				break;
				case TEMPLATE_STATUS_READY:	// report success and stop capturing
					stop();
					fpReader.setTemplate(enroller.getTemplate());
					setPrompt("Ya se ha registrado la huella, puede verificarla");
					((JFrame)getOwner()).setVisible(true);
					break;

				case TEMPLATE_STATUS_FAILED:	// report failure and restart capturing
					enroller.clear();
					stop();
					updateStatus();
					fpReader.setTemplate(null);
					JOptionPane.showMessageDialog(EnrollmentForm.this, "La muestra no corresponde. Repita registro de huella.", "Lector de Huellas", JOptionPane.ERROR_MESSAGE);
					start();
					break;
			}
		}
	}
	
	private void updateStatus()
	{
		// Show number of samples needed.
		setStatus(String.format("Muestras necesarias: %1$s", enroller.getFeaturesNeeded()));
	}
	
}