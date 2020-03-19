package com.fingerprintpay;

import java.util.EventListener;
import java.util.EventObject;

public interface TemplateListener extends EventListener{
    public void enteradoCambioHuella(EventObject e);
}