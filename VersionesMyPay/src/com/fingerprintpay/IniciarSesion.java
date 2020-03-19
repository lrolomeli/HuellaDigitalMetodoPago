package com.fingerprintpay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class IniciarSesion {
	
		private final JLabel labelCorreo;
	    private final JTextField txtCorreo;
		private final JLabel labelContrasena;
		private final JPasswordField contrasena;

	    private final JButton entrar;
	    private final JButton regresar;
	    
	    private JFrame iniciarSesion;
        private Handler handler;
        private JPanel center;
        private JFrame callingJFrame;
	    
	    private IniciarSesion() {
	        
	        iniciarSesion = new JFrame("Iniciar Sesion");
	        labelCorreo = new JLabel("Correo Electronico: ");
	        txtCorreo = new JTextField("");
	        labelContrasena = new JLabel("Contrasena: ");
	        contrasena = new JPasswordField();
	        entrar = new JButton("Entrar");
	        regresar = new JButton("Regresar");
	        
	        handler = new Handler();
	        center = new JPanel();
	        
	        iniciarSesion.setState(Frame.NORMAL);
	        iniciarSesion.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        iniciarSesion.setResizable(true);
	        
	       
	        entrar.addActionListener(handler);
	        regresar.addActionListener(handler);
	        
	        
	        center.setLayout(new GridLayout(3, 2, 5, 5));
	        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
	        center.add(labelCorreo);
	        center.add(txtCorreo);
	        center.add(labelContrasena);
	        center.add(contrasena);
	        center.add(regresar);
	        center.add(entrar);
	        iniciarSesion.setLayout(new BorderLayout());
	        iniciarSesion.add(center, BorderLayout.CENTER);
	        
	        iniciarSesion.pack();
	        iniciarSesion.setSize((int) (iniciarSesion.getSize().width * 1.6), iniciarSesion.getSize().height);
	        iniciarSesion.setLocationRelativeTo(null);
			iniciarSesion.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					iniciarSesion.setVisible(false);
					iniciarSesion.dispose();
					callingJFrame.setVisible(true);
				}
			});
	        iniciarSesion.setVisible(true);
	    }
	    
	    public IniciarSesion(JFrame callingJFrame) {
			this();
			this.callingJFrame = callingJFrame;
	    }
	    
	    private class Handler implements ActionListener
	    {     
	        @Override
	        public void actionPerformed(ActionEvent ev)
	        {
	        	
	        	iniciarSesion.setVisible(false);
	            if(ev.getSource() == entrar) 
	            {
	            	new Sesion(iniciarSesion);
	            }
	            
	            else if(ev.getSource() == regresar) 
	            {
					iniciarSesion.dispose();
					callingJFrame.setVisible(true);
	            }
	            
	            else
	            {
	            	return;
	            }
	                
	        }
	        
	    }
}

