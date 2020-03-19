package com.mypay.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.mypay.controller.Controller;

public class MyPayFrame extends JFrame implements ActionListener{
	
	private MyPayPanel myPayPanel;
	private JButton payButton;
	private MyPayDialog myPayDialog;
	private Controller controller;
	
	public MyPayFrame() {
		super("MyPay");
		
		setLayout(new BorderLayout());
		
		myPayPanel = new MyPayPanel();
		payButton = new JButton("PAGAR");
		myPayDialog = new MyPayDialog(this);
		controller = new Controller();
		
		payButton.addActionListener(this);
		
		myPayPanel.setMyPayListener(new MyPayListener() {
			public void myPayEventOcurred(MyPayEvent e) {
				Double bill = e.getBill();
				System.out.println(bill);
			}			
		});
		
		add(myPayPanel, BorderLayout.CENTER);
		add(payButton, BorderLayout.SOUTH);
		
		
		setSize(350, 220);
		Dimension dim = new Dimension(350, 220);
		setMinimumSize(dim);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(payButton == e.getSource()) {
			myPayDialog.setVisible(true);
		}

	}
	
}
