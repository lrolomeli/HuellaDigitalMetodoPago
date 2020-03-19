package com.mypay.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mypay.controller.Controller;

public class MyPayFrame extends JFrame{
	
	private MyPayPanel myPayPanel;
	private Controller controller;
	private FingerPrintReader fingerPrintReader;
	
	public MyPayFrame() {
		super("MyPay");
		
		setLayout(new BorderLayout());
		
		controller = new Controller();
		myPayPanel = new MyPayPanel();
		fingerPrintReader = new FingerPrintReader(this);
		fingerPrintReader.init();
		
		fingerPrintReader.setVerifiedUserListener(new VerifiedUserListener(){
			public void usuarioVerificado(VerifiedUserEvent vuev){
				connect();
				try {
					fingerPrintReader.setVisible(false);
					fingerPrintReader.stop();
					if(!controller.cobrar()) {
						JOptionPane.showMessageDialog(MyPayFrame.this, "No cuentas con saldo suficiente.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				Controller.setTemplate(null);
				controller.close();
			}

		});
		
		myPayPanel.setMovimientoListener(new MovimientoListener() {
			public void verificar(MovimientoEvent moev) {
				fingerPrintReader.getLogArea().selectAll();
				fingerPrintReader.getLogArea().replaceSelection("");
				fingerPrintReader.stop();
				connect();
				try {
					controller.verificar(moev);
					fingerPrintReader.start();
					fingerPrintReader.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				controller.close();
			}
			public void abonar(MovimientoEvent moev) {
				connect();
				try {
					controller.abonar(moev);
				} catch (Exception e) {
					e.printStackTrace();
				}
				controller.close();
			}
		});
		
		add(myPayPanel, BorderLayout.CENTER);
		
		
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
