package com.mypay.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mypay.controller.Controller;

public class MyPayFrame extends JFrame{
	
	private MyPayDialog myPayDialog;
	private Controller controller;
	private MyPayLoginPanel myPayLoginPanel;
	
	
	public MyPayFrame() {
		super("MyPay");
		
		setLayout(new BorderLayout());
		
		myPayLoginPanel = new MyPayLoginPanel();
		controller = new Controller();
		
		
		//cuando se pique un boton en inicio de sesion panel
		//aqui se llama
		myPayLoginPanel.setMyPayLoginListener(new MyPayLoginListener() {
			public void iniciarSesion(MyPayLoginEvent mplev) {
				//corroborar el usuario
				connect();
				//llamar al metodo para detectar si el numero de cuenta y la contraseña son correctos
				
				String name;
				try {
					name = controller.login(mplev);
					if(null != name){
						//Si el inicio de sesion es correcto
						myPayDialog = new MyPayDialog(MyPayFrame.this, name, controller, mplev.getAccount());
						myPayDialog.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(MyPayFrame.this, "Usuario o contrasena incorrectos.");
					}
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		add(myPayLoginPanel);
		
		setSize(350, 220);
		Dimension dim = new Dimension(350, 220);
		setMinimumSize(dim);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MyPayFrame.this, "No se ha podido conectar a la base de datos", "Problema de Conexion con la Base de Datos", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
