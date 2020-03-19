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
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class MyPayPanel extends JPanel implements ActionListener{
	
	private JLabel cuentaLabel;
	private JTextField cuentaField;
	
	private JLabel totalLabel;
	private JTextField totalField;
	private JButton abonarBtn;
	private JButton cobrarBtn;
	
	private MovimientoListener movimientoListener;

	
	public MyPayPanel() {
		
		cuentaLabel = new JLabel("Numero de Cuenta:");
		cuentaField = new JTextField(10);

		totalLabel = new JLabel("Monto Total:");
		totalField = new JTextField(10);
		
		abonarBtn = new JButton("Abonar");
		cobrarBtn = new JButton("Cobrar");
		
		
		abonarBtn.addActionListener(this);
		cobrarBtn.addActionListener(this);
		
		
		Border outerBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		Border innerBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		
		// First Row
		gc.gridy = 0;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(cuentaLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(cuentaField, gc);

		
		// Second Row
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		add(totalLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(totalField, gc);
		
		// Third Row
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.CENTER;
		add(abonarBtn, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(cobrarBtn, gc);
		
	}
	
	public int getCuenta() {
		return Integer.parseInt(cuentaField.getText());
	}
	
	public float getTotal() {
		return Float.parseFloat(totalField.getText());
	}
	
	public String getTotalAsString() {
		return totalField.getText();
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	public void setMovimientoListener(MovimientoListener movimientoListener) {
		this.movimientoListener = movimientoListener;
	}

	public void actionPerformed(ActionEvent ev) {
		JButton clicked = (JButton) ev.getSource();
		
		if(clicked == abonarBtn) {
			if(null != movimientoListener) {
				try {
					MovimientoEvent moev = new MovimientoEvent(this, getCuenta(), getTotal(), true);
					movimientoListener.abonar(moev);
				}catch (Exception e) {
					displayErrorMessage("Rellena los campos necesarios");
				}
				
			}
			
		}
		else if(clicked == cobrarBtn) {
			if(null != movimientoListener) {
				try {
					MovimientoEvent moev = new MovimientoEvent(this, getCuenta(), getTotal(), false);
					movimientoListener.verificar(moev);
				}catch (Exception e) {
					displayErrorMessage("Rellena los campos necesarios");
				}
			}
		}
		
	}
	
	
}
