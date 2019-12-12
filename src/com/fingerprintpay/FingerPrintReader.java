/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fingerprintpay;

import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.digitalpersona.onetouch.*;

/**
 *
 * @author luisr
 */
public class FingerPrintReader extends JFrame{

	public static String TEMPLATE_PROPERTY = "template";
	private DPFPTemplate template;
	private final JButton enroll;
	private final JButton verify;
	private final JButton cancel;
	private JFrame callingJFrame;
	private Handler handler;
	private JPanel center;
	private JPanel bottom;
	private EnrollmentForm eform;
	private VerificationForm vform;

	private FingerPrintReader() {
		super("Lector de Huellas");
		setState(Frame.NORMAL);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(true);

		enroll = new JButton("Fingerprint Enrollment");
		verify = new JButton("Fingerprint Verification");
		cancel = new JButton("cancelar");
		handler = new Handler();
		center = new JPanel();
		bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		verify.setEnabled(false);
		enroll.addActionListener(handler);
		verify.addActionListener(handler);
		cancel.addActionListener(handler);

		addPropertyChangeListener(FingerPrintReader.TEMPLATE_PROPERTY, new PCL());

		center.setLayout(new GridLayout(4, 1, 0, 5));
		center.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
		center.add(enroll);
		center.add(verify);

		bottom.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
		bottom.add(cancel);

		setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.PAGE_END);

		pack();
		setSize((int) (getSize().width * 1.6), getSize().height);
		setLocationRelativeTo(null);
		setTemplate(null);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				if (JOptionPane.showConfirmDialog(FingerPrintReader.this,
						"Seguro que deseas cerrar?") == JOptionPane.OK_OPTION) {
					setVisible(false);
					dispose();
					callingJFrame.setVisible(true);
				}
			}
		});

		setVisible(true);
	}

	public FingerPrintReader(JFrame callingJFrame) {

		this();
		this.callingJFrame = callingJFrame;
	}

	private void onEnroll() {
		eform = new EnrollmentForm(this);
		eform.setVisible(true);
	}

	private void onVerify() {
		vform = new VerificationForm(this);
		vform.setVisible(true);
	}
	
	private class PCL implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			// Cuando haya un evento del tipo cambio de propiedad
			// Se habilitara el boton de verify en caso de que se halla registrado una
			// huella
			if (getTemplate() != null){
				eform.dispose();
				JOptionPane.showMessageDialog(FingerPrintReader.this,
                		"Huella Asociada",
                		"MyPay Agregar Huella", JOptionPane.INFORMATION_MESSAGE);
				verify.setEnabled(true);
			}
		}
	}

	private class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			setVisible(false);
			if (ev.getSource() == enroll)	
				onEnroll();
			else if (ev.getSource() == verify)
				onVerify();
			else if (ev.getSource() == cancel) {
				dispose();
				callingJFrame.setVisible(true);
			}
		}
	}
	
	public DPFPTemplate getTemplate() {
		return template;
	}
	
	public void setTemplate(DPFPTemplate template) {
		DPFPTemplate old = this.template;
		this.template = template;
		firePropertyChange(TEMPLATE_PROPERTY, old, template);
	}
	
}
