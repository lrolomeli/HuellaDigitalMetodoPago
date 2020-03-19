package com.mypay.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mypay.controller.Controller;

public class MyPayRegistration extends JFrame {
	private RegistrationPanel registrationPanel;
	private Controller controller;
	private FingerPrintReader fingerPrintReader;
	
	public MyPayRegistration() {
		super("MyPay Registration");
		
		
		registrationPanel = new RegistrationPanel();
		controller = new Controller();
		
		fingerPrintReader = new FingerPrintReader(this);
		fingerPrintReader.init();
		//Esta interrupcion se ejecuta al presionar los botones del panel de registro
		registrationPanel.setRegistrationListener(new RegistrationListener() {
			public void registrationEventOcurred(RegistrationEvent ev) {
				//controller.addPerson(ev);
				try {
					controller.registerUser();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			public void registrarHuellaEventOcurred(RegistrarHuellaEvent ev) {
				fingerPrintReader.setVisible(true);
			}
		});
		
		//Esta interrupcion se ejecuta cuando se ha capturado la huellaa
		fingerPrintReader.setFingerPrintListener(new FingerPrintListener(){
			public void fingerPrintReadyToSend(FingerPrintEvent ev){
				fingerPrintReader.setVisible(false);
				JOptionPane.showMessageDialog(MyPayRegistration.this, "Huella Registrada");
				JButton btn = registrationPanel.getFPButton();
				btn.setEnabled(false);
				btn.setBackground(Color.GREEN);
			}

		});
		

		
		add(registrationPanel);
		
		
		setSize(400, 750);
		Dimension dim = new Dimension(400, 750);
		setMinimumSize(dim);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
