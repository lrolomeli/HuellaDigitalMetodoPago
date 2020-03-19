/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fingerprintpay;

import com.digitalpersona.onetouch.*;

/**
 *
 * @author luisr
 */
public class FingerPrintReader{

	private DPFPTemplate template;
	private TemplateListener listener;

	public FingerPrintReader(TemplateListener listener) {
		this.template = null;
		this.listener = listener;
	}
	
	public DPFPTemplate getTemplate() {
		return template;
	}

	public void setTemplate(DPFPTemplate template) {
		DPFPTemplate old = this.template;
		this.template = template;
		TemplateEvent event = new TemplateEvent(this, old, template);
		notificarCambio(event);
	}
	
	public void notificarCambio(TemplateEvent event){
        listener.enteradoCambioHuella(event);
	}

}
