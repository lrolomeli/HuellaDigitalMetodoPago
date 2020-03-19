package com.mypay.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyPayDialog extends JDialog {
	
	private JLabel description;
	private ConfirmationPanel confirmationPanel;
	private JButton confirmButton;

	public MyPayDialog(JFrame parent) {
		super(parent, "Confirmar Pago", true);
		
		setLayout(new BorderLayout(5, 5));
		
		description = new JLabel("Ingresa tu Cuenta y Contrasena para confirmar tu pago", SwingConstants.CENTER);
		confirmationPanel = new ConfirmationPanel();
		confirmButton = new JButton("Confirmar Pago");
		
		
		add(description, BorderLayout.NORTH);
		add(confirmationPanel, BorderLayout.CENTER);
		add(confirmButton, BorderLayout.SOUTH);
		
		setSize(350, 220);
		Dimension dim = new Dimension(350, 220);
		setMinimumSize(dim);
		
	}
}
