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
	private Registro registro;
	private HuellaLista huellaLista;
	
	public MyPayRegistration() {
		super("MyPay Registration");
		
		
		registrationPanel = new RegistrationPanel();
		controller = new Controller();
		fingerPrintReader = new FingerPrintReader(MyPayRegistration.this);
		registro = new Registro();
		huellaLista = new HuellaLista();

		//Esta interrupcion se ejecuta al presionar los botones del panel de registro
		registrationPanel.setRegistrationListener(registro);
		
		//Esta interrupcion se ejecuta cuando se ha capturado la huella
		fingerPrintReader.setFingerPrintListener(huellaLista);
		
		add(registrationPanel);
		
		setSize(400, 750);
		Dimension dim = new Dimension(400, 750);
		setMinimumSize(dim);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}	
	
	class Registro implements RegistrationListener{

		public void registrationEventOcurred(RegistrationEvent ev) {
			try {
				int accountNumber = controller.registerUser(ev);
				if(accountNumber==0) {
					JOptionPane.showMessageDialog(MyPayRegistration.this, "Registra tu huella");
				}
				else if(accountNumber>0) {
					JOptionPane.showMessageDialog(MyPayRegistration.this, "Tu Numero de Cuenta es: "+accountNumber);
					registrationPanel.cleanMess();
					JButton btn = registrationPanel.getFPButton();
					btn.setEnabled(true);
					btn.setBackground(null);
					btn.setText("REGISTRA TU HUELLA");
					Controller.setTemplate(null);
				}
				else {
					JOptionPane.showMessageDialog(MyPayRegistration.this, "No se ha podido crear la cuenta. Reporte el Problema al sig telefono o por correo mypay");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void registrarHuellaEventOcurred(RegistrarHuellaEvent ev) {
			fingerPrintReader.start();
			fingerPrintReader.getLogArea().selectAll();
			fingerPrintReader.getLogArea().replaceSelection("");
			fingerPrintReader.setVisible(true);
		}
		
	}
	
	class HuellaLista implements FingerPrintListener {
		
		public void fingerPrintReadyToSend(FingerPrintEvent ev){
			fingerPrintReader.setVisible(false);
			JOptionPane.showMessageDialog(MyPayRegistration.this, "Huella Registrada");
			JButton btn = registrationPanel.getFPButton();
			btn.setEnabled(false);
			btn.setBackground(Color.GREEN);
			btn.setText("HUELLA LISTA");
		}
	}

}
