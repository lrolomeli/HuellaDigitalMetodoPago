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
	private JButton consultarSaldoBtn;
	private JButton transferirBtn;
	private int idUsuario;
	private MovimientoListener movimientoListener;

	
	public MyPayPanel(int idUsuario) {

		this.idUsuario = idUsuario;
		
		totalLabel = new JLabel("Monto Total:    ");
		totalField = new JTextField(10);
		
		cuentaLabel = new JLabel("Numero de Cuenta a transferir:    ");
		cuentaField = new JTextField(10);
		
		consultarSaldoBtn = new JButton("Consultar Estado de Cuenta");
		transferirBtn = new JButton("Transferir");
		
		
		transferirBtn.addActionListener(this);
		consultarSaldoBtn.addActionListener(this);
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		
		// First Row
		gc.gridy = 0;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(totalLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(totalField, gc);

		
		// Second Row
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		add(cuentaLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(cuentaField, gc);
		
		// Third Row
		gc.gridx = 1;
		gc.gridy++;
		gc.anchor = GridBagConstraints.CENTER;
		add(transferirBtn, gc);
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		add(consultarSaldoBtn, gc);
		
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
		
		if(clicked == consultarSaldoBtn) {
			if(null != movimientoListener) {
				try {
					MovimientoEvent moev = new MovimientoEvent(this, idUsuario);
					movimientoListener.consultarSaldo(moev);
				}catch (Exception e) {
					
				}
				
			}
			
		}
		else if(clicked == transferirBtn) {
			if(null != movimientoListener) {
				try {
					int cuentaDestino = getCuenta();
					float cantidad = getTotal();
					MovimientoEvent moev = new MovimientoEvent(this, idUsuario, cantidad, false, cuentaDestino);
					if(idUsuario!=cuentaDestino && cantidad>0)
						movimientoListener.transferir(moev);
					else
						displayErrorMessage("No se ha podido completar la operacion");
				}catch (Exception e) {
					displayErrorMessage("Rellena los campos necesarios");
				}
			}
		}
		
	}
	
	
}
