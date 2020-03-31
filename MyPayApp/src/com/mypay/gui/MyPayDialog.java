package com.mypay.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.mypay.controller.Controller;

public class MyPayDialog extends JDialog implements ActionListener{
	
	private JLabel description;
	private JButton logoutBtn;
	private MyPayPanel myPayPanel;
	private String nombreCuenta;
	private Controller controller;


	public MyPayDialog(JFrame parent, String nombreCuenta, Controller controller, int account) {
		super(parent, "Cuenta MyPay", true);
		this.nombreCuenta = nombreCuenta;
		this.controller = controller;
		
		
		myPayPanel = new MyPayPanel(account);

		
		myPayPanel.setMovimientoListener(new MovimientoListener() {
			public void transferir(MovimientoEvent moev) {
				try {
					controller.connect();
				} catch (Exception e1) {
				}
				try {
					
					if(controller.transferir(moev)) {
						JOptionPane.showMessageDialog(MyPayDialog.this, "Transaccion Completa.");
					}
					else {
						JOptionPane.showMessageDialog(MyPayDialog.this, "Error en la transaccion compruebe su estado de cuenta y los datos de transferencia.", "Error Transferencia", JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e) {
				}
				controller.close();
			}

			public void consultarSaldo(MovimientoEvent moev) {
				try {
					controller.connect();
				} catch (Exception e1) {
				}
				try {
					Float saldo = controller.consultarSaldo(moev);
					if(null!=saldo)
						JOptionPane.showMessageDialog(MyPayDialog.this, "Su saldo es de: "+saldo+" pesos");
				} catch (SQLException e) {
				}
				controller.close();
				
			}

		});
		
		add(myPayPanel, BorderLayout.CENTER);
		
		setLayout(new BorderLayout(5, 5));
		
		description = new JLabel("Bienvenido "+nombreCuenta+" a su cuenta My Pay", SwingConstants.CENTER);

		logoutBtn = new JButton("Cerrar Sesion");
		logoutBtn.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(description, BorderLayout.NORTH);
		add(myPayPanel, BorderLayout.CENTER);
		add(logoutBtn, BorderLayout.SOUTH);
		
		setSize(500, 300);
		Dimension dim = new Dimension(350, 220);
		setMinimumSize(dim);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(logoutBtn == e.getSource()) {
			nombreCuenta = null;
			dispose();
			

		}

	}
	
}
