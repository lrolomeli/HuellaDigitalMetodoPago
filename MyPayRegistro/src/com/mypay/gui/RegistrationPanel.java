package com.mypay.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.toedter.calendar.JCalendar;

public class RegistrationPanel extends JPanel implements ActionListener {

	private JLabel firstNameLabel;
	private JTextField firstNameField;
	private JLabel lastNameLabel;
	private JTextField lastNameField;
	private JLabel birthDateLabel;
	private JCalendar birthDateCalendar;
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
	
	public RegistrationPanel() {
		firstNameLabel = new JLabel("Nombre:");
		firstNameField = new JTextField(20);
		lastNameLabel = new JLabel("Apellido:   ");
		lastNameField = new JTextField(20);
		birthDateLabel = new JLabel("Fecha de Nacimiento:");
		birthDateCalendar = new JCalendar();
		genderLabel = new JLabel("Genero:");
		userNameLabel = new JLabel("Usuario:");
		userNameField = new JTextField(20);
		passwordLabel = new JLabel("Contrasena:");
		passwordField = new JPasswordField(20);
		registrationButton = new JButton("DAR DE ALTA");
		registerFPButton = new JButton("REGISTRA TU HUELLA");
		
		registrationButton.addActionListener(this);
		registerFPButton.addActionListener(this);
		maleRadio = new JRadioButton("Hombre");
		maleRadio.setActionCommand("Hombre");
		femaleRadio = new JRadioButton("Mujer");
		femaleRadio.setActionCommand("Mujer");
		otherGender = new JRadioButton("Otro");
		otherGender.setActionCommand("Otro");		
		
		otherGender.setSelected(true);
		
		genderGroup = new ButtonGroup();
		
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		genderGroup.add(otherGender);
		
		Border innerBorder = BorderFactory.createTitledBorder("Registro");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		
		// First Row
		gc.gridy = 0;
		gc.gridx = 0;
		add(firstNameLabel, gc);
		
		gc.gridx = 1;
		add(firstNameField, gc);
		
		// Second Row
		gc.gridy++;
		gc.gridx = 0;
		add(lastNameLabel, gc);
		
		gc.gridx = 1;
		add(lastNameField, gc);
		
		// Third Row
		gc.gridy++;
		gc.gridx = 0;
		add(birthDateLabel, gc);
		
		gc.gridx = 1;
		add(birthDateCalendar, gc);
		
		// Fourth Row
		gc.weighty = 0.01;
		gc.gridy++;
		gc.gridx = 0;
		add(genderLabel, gc);
		
		gc.gridx = 1;
		add(maleRadio, gc);
		
		// Next Row
		gc.gridy++;
		gc.gridx = 1;
		add(femaleRadio, gc);
		
		// Next Row
		gc.gridy++;
		gc.gridx = 1;
		add(otherGender, gc);
		
		// Next Row
		gc.weighty = 0.1;
		gc.gridy++;
		
		gc.gridx = 0;
		add(userNameLabel, gc);
		
		gc.gridx = 1;
		add(userNameField, gc);
		
		// Next Row
		gc.gridy++;
		
		gc.gridx = 0;
		add(passwordLabel, gc);
		
		gc.gridx = 1;
		add(passwordField, gc);
		
		// Next Row
		gc.gridy++;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(registrationButton, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(registerFPButton, gc);

	}
	
	
	
	public void setRegistrationListener(RegistrationListener registrationListener) {
		this.registrationListener = registrationListener;
	}
	
	public JButton getFPButton() {
		return registerFPButton;
	}

	public void actionPerformed(ActionEvent e) {
		if(registrationButton==e.getSource()) {
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			Date birthDate = birthDateCalendar.getDate();
			String gender = genderGroup.getSelection().getActionCommand();
			String userName = userNameField.getText();
			String password = new String(passwordField.getPassword());
			RegistrationEvent rev = new RegistrationEvent(this, firstName, lastName, birthDate, gender, userName, password);
			
			if(null != registrationListener) {
				registrationListener.registrationEventOcurred(rev);
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
