package com.fingerprintpay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class CuentaMyPay{

		private final JButton iniciarSesion;
	    private final JButton registrarse;
	    private final JButton regresar;
	    private Handler handler;
	    private JPanel center;
	    private JPanel bottom;
	    private JFrame cuentaMyPay;
	    private JFrame callingJFrame;
	    
	    private CuentaMyPay() {
	    	cuentaMyPay = new JFrame("Cuenta MyPay");
	        iniciarSesion = new JButton("Iniciar Sesion");
	        registrarse = new JButton("Registrarse");
	        regresar = new JButton("Regresar a Menu Principal");
	        handler = new Handler();
	        
	        center = new JPanel();
	        bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	        
	    	cuentaMyPay.setState(Frame.NORMAL);
	    	cuentaMyPay.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    	cuentaMyPay.setResizable(true);
	        iniciarSesion.addActionListener(handler);
	        registrarse.addActionListener(handler);
	        regresar.addActionListener(handler);
	        
	        //GridLayout(filas, columnas, espacioEntreColumnas, espacioEntreFilas)
	        center.setLayout(new GridLayout(2, 1, 5, 5));
	        
	        //Son los bordes vacios que rodean a los botones de la ventana 20 pixeles
	        // BorderFactory.createEmptyBorder(arriba, izquierda, abajo, derecha)
	        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        
	        center.add(iniciarSesion);
	        center.add(registrarse);
	        
			bottom.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
			bottom.add(regresar);
	        
	        cuentaMyPay.setLayout(new BorderLayout());
	        cuentaMyPay.add(center, BorderLayout.CENTER);
	        cuentaMyPay.add(bottom, BorderLayout.PAGE_END);
	        
	        //Se utiliza para darle un tamaño inicial minimo
	        //segun los componentes que se agregaron al diseño
	        
	        //Obtiene el ancho y alto necesarios para ajustar los elementos
	        //y que puedan visualizarse todos con el tamaño minimo de pantalla
	        cuentaMyPay.pack();
	        
	        cuentaMyPay.setSize((int) (cuentaMyPay.getSize().width * 1.6), cuentaMyPay.getSize().height);
	        
	        //Coloca la ventana en el centro de la pantalla.
	        cuentaMyPay.setLocationRelativeTo(null);
	        
			cuentaMyPay.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					cuentaMyPay.setVisible(false);
					cuentaMyPay.dispose();
					callingJFrame.setVisible(true);
				}
			});
	        
	        //Hace visible la ventana
	        cuentaMyPay.setVisible(true);
	        
	    }
	    
		public CuentaMyPay(JFrame callingJFrame) {

			this();
			this.callingJFrame = callingJFrame;
		}
	    
	    private class Handler implements ActionListener
	    {     
	        @Override
	        public void actionPerformed(ActionEvent ev)
	        {
	        	cuentaMyPay.setVisible(false);
	        	
	            if(ev.getSource() == registrarse) 
	            {
	                new Registro(cuentaMyPay);
	            }
	            else if(ev.getSource() == iniciarSesion)
	            {
	            	new IniciarSesion(cuentaMyPay);
	            }
				else if (ev.getSource() == regresar) {
					cuentaMyPay.dispose();
					callingJFrame.setVisible(true);
				}
	        }
	        
	    }
}
