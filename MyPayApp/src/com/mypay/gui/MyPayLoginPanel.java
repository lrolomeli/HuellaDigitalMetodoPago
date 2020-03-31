package com.mypay.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class MyPayLoginPanel extends JPanel {
	
	private JLabel accountLabel;
	private JTextField accountField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton loginButton;
	private MyPayLoginListener listener;
	
	public MyPayLoginPanel() {
		
		
		accountLabel = new JLabel("Cuenta:   ");
		accountField = new JTextField(10);
		passwordLabel = new JLabel("Contrasena:   ");
		passwordField = new JPasswordField(10);
		loginButton = new JButton("Iniciar Sesion");
		
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
		
		// Second Row
		gc.gridy++;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(loginButton, gc);
		
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obtainFields();
			}
		});
			
	}
	
	public void obtainFields() {
		try {
			int account = getAccount();
			String password = getPassword();
			if(0==account){
				JOptionPane.showMessageDialog(null, "Escribe un numero de cuenta valido");
				return;
			}
			if(null==password) {
				JOptionPane.showMessageDialog(null, "No se permiten contrasenas vacias");
				return;
			}
			MyPayLoginEvent mplev = new MyPayLoginEvent(MyPayLoginPanel.this, account, password);	
			if(null!=listener)
				listener.iniciarSesion(mplev);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Revise que haya escrito bien los parametros");
		}

	}
	
	public int getAccount() {
		String s = accountField.getText();
		if(!s.isEmpty()) return Integer.parseInt(s);
		else return 0;
	}
	
	public String getPassword() {
		String s = new String(passwordField.getPassword());
		if(!s.isEmpty()) return s;
		else return null;
	}
	
	public void setMyPayLoginListener(MyPayLoginListener listener){
		this.listener = listener;
	}
}
