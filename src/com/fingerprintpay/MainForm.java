package com.fingerprintpay;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainForm extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Usuario user;
    private final JTextField txtNombre;
    private final JTextField txtApellido;
    private final JTextField txtUsuario;
    private final JPasswordField password;
    
    MainForm() {
        super("Aplicacion");
        setState(Frame.NORMAL);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        setResizable(true);
        
        txtNombre = new JTextField("nombre");
        txtApellido = new JTextField("apellido");
        txtUsuario = new JTextField("usuario");
        password = new JPasswordField();
        
        final JButton crearCuenta = new JButton("Crear Cuenta");
        
        txtApellido.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(txtApellido.getText().equals("apellido"))
                    txtApellido.setText("");
            }

            public void focusLost(FocusEvent e) {
                if(txtApellido.getText().equals(""))
                    txtApellido.setText("apellido");
            }
        });
        
        crearCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainForm.user = new Usuario(txtNombre.getText(), txtApellido.getText(), 'M', 5, 1, 1996, txtUsuario.getText(), password.getSelectedText());
                MainForm.user.agregarHuella(Dedo.INDICE, Mano.DERECHA);
                new FingerPrintReader();
            }
        });
        
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 1, 0, 5));
        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
        center.add(txtNombre);
        center.add(txtApellido);
        center.add(txtUsuario);
        center.add(password);
        center.add(crearCuenta);
        setLayout(new BorderLayout());
        add(center, BorderLayout.CENTER);
        
        pack();
        setSize((int) (getSize().width * 1.6), getSize().height);
        setLocationRelativeTo(null);
        setVisible(true);
        //new FingerPrintReader();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainForm();
            }
        });
    }

}
