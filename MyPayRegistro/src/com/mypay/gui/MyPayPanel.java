package com.mypay.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class MyPayPanel extends JPanel implements ActionListener{
	
	private JLabel priceLabel;
	private JTextField priceField;
	private JButton addingButton;
	private JLabel totalLabel;
	private JLabel chargeLabel;
	private Double bill;
	private MyPayListener myPayListener;
	
	public MyPayPanel() {
		
		priceLabel = new JLabel("Precio del Producto:   ");
		priceField = new JTextField(10);
		addingButton = new JButton("+");
		totalLabel = new JLabel("Total de Compra:    $   ");
		chargeLabel = new JLabel("0.0");
		bill = new Double(0);
		
		addingButton.addActionListener(this);
		
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
		add(priceLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(priceField, gc);
		
		gc.gridx = 2;
		gc.anchor = GridBagConstraints.CENTER;
		add(addingButton, gc);
		
		// Second Row
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		add(totalLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(chargeLabel, gc);
		
	}
	
	public void setMyPayListener(MyPayListener myPayListener) {
		this.myPayListener = myPayListener;
	}

	public void actionPerformed(ActionEvent e) {
		
		try {
			bill += Double.parseDouble(priceField.getText());
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
		
		chargeLabel.setText(bill.toString());
		//Aqui sucede el cambio
		MyPayEvent ev = new MyPayEvent(this, bill);
		
		if(null != myPayListener) {
			myPayListener.myPayEventOcurred(ev);
		}
	}

}
