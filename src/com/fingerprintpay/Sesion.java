package com.fingerprintpay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Sesion {
	    private final JButton abonoPrepago;
	    private final JButton asociarHuella;
	    private final JButton cerrarSesion;
	    
	    private JFrame sesion;
        private Handler handler;
        private JPanel center;
        private JFrame callingJFrame;
	    
	    private Sesion() {
	        
	        sesion = new JFrame("Sesion MyPay Abierta");
	        abonoPrepago = new JButton("Abono Prepago");
	        asociarHuella = new JButton("Asociar Huella");
	        cerrarSesion = new JButton("Cerrar Sesion");
	        
	        handler = new Handler();
	        center = new JPanel();
	        
	        sesion.setState(Frame.NORMAL);
	        sesion.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        sesion.setResizable(true);
	        
	       
	        abonoPrepago.addActionListener(handler);
	        asociarHuella.addActionListener(handler);
	        cerrarSesion.addActionListener(handler);
	        
	        center.setLayout(new GridLayout(3, 2, 5, 5));
	        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
	        center.add(asociarHuella);
	        center.add(abonoPrepago);
	        center.add(cerrarSesion);
	        sesion.setLayout(new BorderLayout());
	        sesion.add(center, BorderLayout.CENTER);
	        
	        sesion.pack();
	        sesion.setSize((int) (sesion.getSize().width * 1.6), sesion.getSize().height);
	        sesion.setLocationRelativeTo(null);
	        
			sesion.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					if (JOptionPane.showConfirmDialog(sesion,
							"Seguro que deseas cerrar?") == JOptionPane.OK_OPTION) {
						sesion.setVisible(false);
						sesion.dispose();
						callingJFrame.setVisible(true);
					}
				}
			});
	        sesion.setVisible(true);
	    }
	    
	    public Sesion(JFrame callingJFrame) {
			this();
			this.callingJFrame = callingJFrame;
	    }
	    
	    private class Handler implements ActionListener
	    {     
	        @Override
	        public void actionPerformed(ActionEvent ev)
	        {
	        	
	        	
	            if(ev.getSource() == abonoPrepago) 
	            {
	            	sesion.setVisible(false);
	            	sesion.dispose();
	            	callingJFrame.setVisible(true);
	            }
	            
	            else if(ev.getSource() == asociarHuella) 
	            {
	            	sesion.setVisible(false);
	            	new FingerPrintReader(sesion);
	            }
	            else if (ev.getSource() == cerrarSesion) {
	            	if (JOptionPane.showConfirmDialog(sesion,
							"Seguro que deseas abandonar la sesion?") == JOptionPane.OK_OPTION) {
						sesion.setVisible(false);
						sesion.dispose();
						callingJFrame.setVisible(true);
					}
	            }
	            
	            else
	            {
	            	return;
	            }
	                
	        }
	        
	    }
}

