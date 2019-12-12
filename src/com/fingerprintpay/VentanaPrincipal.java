package com.fingerprintpay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class VentanaPrincipal{

		private final JButton cuentaMyPay;
	    private final JButton movimientos;
	    private Handler handler;
	    private JPanel center;
	    private JFrame ventanaPrincipal;
	    
	    VentanaPrincipal() {
	    	ventanaPrincipal = new JFrame("MyPay");
	        cuentaMyPay = new JButton("Cuenta MyPay");
	        movimientos = new JButton("Movimientos");
	        handler = new Handler();
	        center = new JPanel();
	        
	    	ventanaPrincipal.setState(Frame.NORMAL);
	    	ventanaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    	ventanaPrincipal.setResizable(true);
	        cuentaMyPay.addActionListener(handler);
	        movimientos.addActionListener(handler);
	        
	        //GridLayout(filas, columnas, espacioEntreColumnas, espacioEntreFilas)
	        center.setLayout(new GridLayout(2, 1, 5, 5));
	        
	        //Son los bordes vacios que rodean a los botones de la ventana 20 pixeles
	        // BorderFactory.createEmptyBorder(arriba, izquierda, abajo, derecha)
	        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        
	        center.add(cuentaMyPay);
	        center.add(movimientos);
	        
	        ventanaPrincipal.setLayout(new BorderLayout());
	        ventanaPrincipal.add(center, BorderLayout.CENTER);
	        
	        //Se utiliza para darle un tamaño inicial minimo
	        //segun los componentes que se agregaron al diseño
	        
	        //Obtiene el ancho y alto necesarios para ajustar los elementos
	        //y que puedan visualizarse todos con el tamaño minimo de pantalla
	        ventanaPrincipal.pack();
	        
	        ventanaPrincipal.setSize((int) (ventanaPrincipal.getSize().width * 1.6), ventanaPrincipal.getSize().height);
	        
	        //Coloca la ventana en el centro de la pantalla.
	        ventanaPrincipal.setLocationRelativeTo(null);
	        
	        //Hace visible la ventana
	        ventanaPrincipal.setVisible(true);
	        
	    }
	    
	    private class Handler implements ActionListener
	    {     
	        @Override
	        public void actionPerformed(ActionEvent ev)
	        {
	        	ventanaPrincipal.setVisible(false);
	        	
	            if(ev.getSource() == movimientos) 
	            {
	                new Movimientos(ventanaPrincipal);
	            }
	            else if(ev.getSource() == cuentaMyPay)
	            {
	            	new CuentaMyPay(ventanaPrincipal);
	            }
	                
	        }
	        
	    }
}
