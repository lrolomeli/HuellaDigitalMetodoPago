package com.fingerprintpay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Registro {
	
	    private Usuario user;
	    private final JTextField txtNombre;
	    private final JTextField txtApellido;
	    private final JTextField txtUsuario;
	    private final JPasswordField password;
	    private final JButton crearCuenta;
	    private JFrame registro;
        private FHandler fHandler;
        private Handler handler;
        private JPanel center;
	    
	    Registro() {
	        
	        registro = new JFrame("Aplicacion");
	        txtNombre = new JTextField("nombre");
	        txtApellido = new JTextField("apellido");
	        txtUsuario = new JTextField("usuario");
	        crearCuenta = new JButton("Crear Cuenta");
	        password = new JPasswordField();
	        fHandler = new FHandler();
	        handler = new Handler();
	        center = new JPanel();
	        
	        registro.setState(Frame.NORMAL);
	        registro.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        registro.setResizable(true);
	        
	        txtApellido.addFocusListener(fHandler);
	       
	        crearCuenta.addActionListener(handler);
	        
	        
	        center.setLayout(new GridLayout(4, 1, 0, 5));
	        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
	        center.add(txtNombre);
	        center.add(txtApellido);
	        center.add(txtUsuario);
	        center.add(password);
	        center.add(crearCuenta);
	        registro.setLayout(new BorderLayout());
	        registro.add(center, BorderLayout.CENTER);
	        
	        registro.pack();
	        registro.setSize((int) (registro.getSize().width * 1.6), registro.getSize().height);
	        registro.setLocationRelativeTo(null);
	        registro.setVisible(true);
	    }
	    
	    private class FHandler implements FocusListener{
	        public void focusGained(FocusEvent e) {
	        	if(e.getSource()==txtApellido) 
	        	{
	        		if(txtApellido.getText().equals("apellido"))
	        			txtApellido.setText("");
	        	}
	        }

	        public void focusLost(FocusEvent e) {
	            if(txtApellido.getText().equals(""))
	                txtApellido.setText("apellido");
	        }
	    }
	    
	    private class Handler implements ActionListener
	    {     
	        @Override
	        public void actionPerformed(ActionEvent ev)
	        {
	            if(ev.getSource() == crearCuenta) 
	            {
	                user = new Usuario(txtNombre.getText(), txtApellido.getText(), 'M', 5, 1, 1996, txtUsuario.getText(), password.getSelectedText());
	                user.agregarHuella(Dedo.INDICE, Mano.DERECHA);
	                new FingerPrintReader(registro);
	            }
	            else
	            {
	            	return;
	            }
	                
	        }
	        
	    }
}

