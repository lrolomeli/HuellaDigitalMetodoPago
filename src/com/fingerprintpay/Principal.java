package com.fingerprintpay;

import javax.swing.*;

public class Principal{
	
	private static void aplicacion() {
		new VentanaPrincipal();
		
	}
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
    	Runnable aplicacion = new Runnable() 
    	{	
			@Override
			public void run() 
			{
				aplicacion();
			}
		};
		
        SwingUtilities.invokeLater(aplicacion);
    }

}
