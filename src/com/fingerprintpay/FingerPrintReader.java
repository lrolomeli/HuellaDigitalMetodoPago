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
public class FingerPrintReader extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static String TEMPLATE_PROPERTY = "template";
    private DPFPTemplate template;
    private final JButton enroll = new JButton("Fingerprint Enrollment");
    private final JButton verify = new JButton("Fingerprint Verification");
    private final JButton quit = new JButton("close");
    
    FingerPrintReader() {
        
        super("Lector de Huellas");
        setState(Frame.NORMAL);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);

        Handler handler = new Handler();
        enroll.addActionListener(handler);
        verify.addActionListener(handler);
        quit.addActionListener(handler);
        
        this.addPropertyChangeListener(TEMPLATE_PROPERTY, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                verify.setEnabled(template != null);
                if (evt.getNewValue() == evt.getOldValue()) {
                    return;
                }
                if (template != null) {
                    JOptionPane.showMessageDialog(FingerPrintReader.this, "The fingerprint template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 1, 0, 5));
        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
        center.add(enroll);
        center.add(verify);
        

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        bottom.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        bottom.add(quit);

        setLayout(new BorderLayout());
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.PAGE_END);

        pack();
        setSize((int) (getSize().width * 1.6), getSize().height);
        setLocationRelativeTo(null);
        setTemplate(null);
        setVisible(true);
    }

    private void onEnroll() {
        EnrollmentForm form = new EnrollmentForm(this);
        form.setVisible(true);
    }

    private void onVerify() {
        VerificationForm form = new VerificationForm(this);
        form.setVisible(true);
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }
    
    private class Handler implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent ev){
            if(ev.getSource() == enroll)
                onEnroll();
            else if(ev.getSource() == verify)
                onVerify();
            else if(ev.getSource() == quit)
                dispose();
            else
                return;
        }
        
    }
}
