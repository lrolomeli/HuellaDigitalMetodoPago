package com.mypay.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

public class RegistrationPanel extends JPanel implements ActionListener {

	private JLabel firstNameLabel;
	private JTextField firstNameField;
	private JLabel lastNameLabel;
	private JTextField lastNameField;
	private JLabel birthDateLabel;
	private JLabel genderLabel;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private JRadioButton otherGender;
	private ButtonGroup genderGroup;
	private JLabel userNameLabel;
	private JTextField userNameField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton registrationButton;
	private JButton registerFPButton;
	private RegistrationListener registrationListener;
	private JDateChooser birthDateChooser;
	
	public RegistrationPanel() {
		firstNameLabel = new JLabel("Nombre");
		firstNameField = new JTextField(20);
		lastNameLabel = new JLabel("Apellido");
		lastNameField = new JTextField(20);
		
		birthDateLabel = new JLabel("Fecha de Nacimiento");		
		birthDateChooser = new JDateChooser();
		birthDateChooser.setFont(new Font("Panel.font", Font.PLAIN, 12));
		
		
		genderLabel = new JLabel("Genero");
		userNameLabel = new JLabel("Usuario");
		userNameField = new JTextField(20);
		passwordLabel = new JLabel("Contrasena");
		passwordField = new JPasswordField(20);
		registrationButton = new JButton("DAR DE ALTA");
		registerFPButton = new JButton("REGISTRA TU HUELLA");
		
		registrationButton.setBackground(Color.white);
		registerFPButton.setBackground(Color.white);
		
		firstNameLabel.setFont(new Font("Serif", Font.BOLD, 15));
		lastNameLabel.setFont(new Font("Serif", Font.BOLD, 15));
		birthDateLabel.setFont(new Font("Serif", Font.BOLD, 15));
		genderLabel.setFont(new Font("Serif", Font.BOLD, 15));
		userNameLabel.setFont(new Font("Serif", Font.BOLD, 15));
		passwordLabel.setFont(new Font("Serif", Font.BOLD, 15));
		
		firstNameField.setFont(new Font("Panel.font", Font.PLAIN, 14));
		firstNameField.setHorizontalAlignment(JTextField.CENTER);
		lastNameField.setFont(new Font("Panel.font", Font.PLAIN, 14));
		lastNameField.setHorizontalAlignment(JTextField.CENTER);
		userNameField.setFont(new Font("Panel.font", Font.PLAIN, 14));
		userNameField.setHorizontalAlignment(JTextField.CENTER);
		passwordField.setFont(new Font("Panel.font", Font.PLAIN, 14));
		passwordField.setHorizontalAlignment(JTextField.CENTER);
		
		
		registrationButton.addActionListener(this);
		registerFPButton.addActionListener(this);
		maleRadio = new JRadioButton("Hombre");
		maleRadio.setActionCommand("Hombre");
		maleRadio.setBackground(Color.white);
		femaleRadio = new JRadioButton("Mujer");
		femaleRadio.setActionCommand("Mujer");
		femaleRadio.setBackground(Color.white);
		otherGender = new JRadioButton("Otro");
		otherGender.setBackground(Color.white);
		otherGender.setActionCommand("Otro");		
		
		otherGender.setSelected(true);
		
		genderGroup = new ButtonGroup();
		
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		genderGroup.add(otherGender);
		
		setBackground(Color.white);		
		
		Border innerBorder = BorderFactory.createTitledBorder("Registro");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		add(registerFPButton, gc);
		
		// Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		add(firstNameLabel, gc);
		
		//Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.CENTER;
		add(firstNameField, gc);
		
		// Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lastNameLabel, gc);
		
		//Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.CENTER;
		add(lastNameField, gc);
		
		//Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		add(birthDateLabel, gc);
		
		//Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.CENTER;
		add(birthDateChooser, gc);
		
		//Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		add(genderLabel, gc);
		
		//Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weighty = 0.01;
		add(maleRadio, gc);
		
		// Next Row
		gc.gridy++;
		add(femaleRadio, gc);
		
		// Next Row
		gc.gridy++;
		add(otherGender, gc);
		
		//Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.weighty = 0.1;
		add(userNameLabel, gc);
		
		//Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.CENTER;
		add(userNameField, gc);
		
		//Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_START;
		add(passwordLabel, gc);
		
		//Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.CENTER;
		add(passwordField, gc);
		
		//Next Row
		gc.gridy++;
		gc.anchor = GridBagConstraints.CENTER;
		add(registrationButton, gc);

	}
		
	public void setRegistrationListener(RegistrationListener registrationListener) {
		this.registrationListener = registrationListener;
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	public JButton getFPButton() {
		return registerFPButton;
	}
	
	public String getFirstName() {
		String s = firstNameField.getText();
		if(!s.isEmpty()) return s;
		else return null;
	}
	
	public String getLastName() {
		String s = lastNameField.getText();
		if(!s.isEmpty()) return s;
		else return null;
	}
	
	public Date getBirthDate() {
		return birthDateChooser.getDate();
	}
	
	public String getGender() {
		return genderGroup.getSelection().getActionCommand();
	}

	public String getUserName() {
		String s = userNameField.getText();
		if(!s.isEmpty()) return s;
		else return null;
	}
	
	public String getPassword() {
		String s = new String(passwordField.getPassword());
		if(!s.isEmpty()) return s;
		else return null;
	}
	
	public void cleanMess() {
		firstNameField.setText("");
		lastNameField.setText("");
		userNameField.setText("");
		passwordField.setText("");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(registrationButton==e.getSource()) {
			try {
				String firstName = getFirstName();	
				if(firstName==null) {
					displayErrorMessage("Escribe tu nombre");
					return;
				}
				String lastName = getLastName();	
				if(lastName==null) {
					displayErrorMessage("Escribe tu apellido");
					return;
				}
				Date birthDate = getBirthDate();
				if(birthDate==null) {
					displayErrorMessage("Agrega tu fecha de nacimiento");
					return;
				}
				String gender = getGender();
				String userName = getUserName();
				if(userName==null) {
					displayErrorMessage("Elige un nombre de usuario");
					return;
				}
				String password = getPassword();
				if(password==null){
					displayErrorMessage("No se aceptan contrasenas vacias");
					return;
				}

				if(null != registrationListener) {
					RegistrationEvent rev = new RegistrationEvent(this, firstName, lastName, birthDate, gender, userName, password);					
					registrationListener.registrationEventOcurred(rev);
				}
			}catch (Exception ex) {
				displayErrorMessage("Verifica que tus datos sean correctos");
				ex.printStackTrace();
			}

		}
		else if(registerFPButton==e.getSource()){
			RegistrarHuellaEvent rhev = new RegistrarHuellaEvent(this);
			if(null != registrationListener) {
				registrationListener.registrarHuellaEventOcurred(rhev);
			}
		}

	}
}
