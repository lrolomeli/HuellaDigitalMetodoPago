package com.fingerprintpay;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.verification.*;
import java.awt.*;

public class VerificationForm extends CaptureForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();
	//Dejar esta variable "dinamica"
	private FingerPrintReader fpReader;
	
	VerificationForm(Frame owner, FingerPrintReader fpReader) {
		super(owner);
		this.fpReader = fpReader;
	}
	
	@Override protected void init()
	{
		super.init();
		this.setTitle("Fingerprint Enrollment");
		updateStatus(0);
	}

	@Override protected void process(DPFPSample sample) {
		super.process(sample);

		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

		// Check quality of the sample and start verification if it's good
		if (features != null)
		{
			// Compare the feature set with our template
			DPFPVerificationResult result = 
				verificator.verify(features, fpReader.getTemplate());
			updateStatus(result.getFalseAcceptRate());
			if (result.isVerified())
				makeReport("La lectura de huella coincide.");
			else
				makeReport("No corresponde a la huella registrada.");
		}
	}
	
	private void updateStatus(int FAR)
	{
		// Show "False accept rate" value
		setStatus(String.format("False Accept Rate (FAR) = %1$s", FAR));
	}

}
