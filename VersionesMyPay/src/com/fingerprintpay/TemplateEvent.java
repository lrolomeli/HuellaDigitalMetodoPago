package com.fingerprintpay;

import java.util.EventObject;
import com.digitalpersona.onetouch.DPFPTemplate;

public class TemplateEvent extends EventObject{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected DPFPTemplate templateAnterior;
    protected DPFPTemplate nuevoTemplate;

    public TemplateEvent(Object source, DPFPTemplate anterior, DPFPTemplate nuevo){
        super(source);
        templateAnterior = anterior;
        nuevoTemplate = nuevo;
    }

    public DPFPTemplate getTemplateAnterior() {
        return this.templateAnterior;
    }

    public DPFPTemplate getNuevoTemplate() {
        return this.nuevoTemplate;
    }

       
}
