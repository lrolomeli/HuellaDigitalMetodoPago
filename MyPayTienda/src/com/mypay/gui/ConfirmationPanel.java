package com.mypay.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class ConfirmationPanel extends JPanel {
	
	private JLabel accountLabel;
	private JTextField accountField;
	private JLabel passwordLabel;
	private JTextField passwordField;
	
	public ConfirmationPanel() {
		
		
		accountLabel = new JLabel("Cuenta:   ");
		accountField = new JTextField(10);
		passwordLabel = new JLabel("Contrasena:   ");
		passwordField = new JTextField(10);
		
		
		Border outerBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		Border innerBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0.5;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		
		// First Row
		gc.gridy = 0;
		gc.gridx = 0;
		
		gc.anchor = GridBagConstraints.LINE_END;
		add(accountLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(accountField, gc);
		
		// Second Row
		gc.gridy++;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(passwordLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(passwordField, gc);
		
		
	}
}
