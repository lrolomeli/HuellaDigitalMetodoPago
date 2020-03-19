package com.fingerprintpay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;



public class Cobro{

	private final JLabel costo;
	private final JTextField txtCosto;
		private final JButton agregar;
	    private final JButton confirmar;
	    private final JButton cancelar;
	    private final JButton quitar;
	    private Handler handler;
	    private JPanel north;
	    private JPanel center;
	    private JPanel bottom;
	    private JFrame movimientos;
	    private JFrame callingJFrame;
	    private JList<Double> lista;
	    private DefaultListModel<Double> listModel;
	    
	    private Cobro() {
	    	movimientos = new JFrame("MyPay");
	    	costo = new JLabel("Costo", JLabel.CENTER);
	    	txtCosto = new JTextField();
	    	agregar = new JButton("Agregar");
	    	lista = new JList<Double>();
	    	listModel = new DefaultListModel<Double>();
	    	confirmar = new JButton("Confirmar Orden");
	    	cancelar = new JButton("cancelar");
	    	quitar = new JButton("quitar");
	        handler = new Handler();
	        
	        north = new JPanel();
	        center = new JPanel();
	        bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	        
	        costo.setFont(new Font("Terminal", Font.PLAIN, 14));
	        
	    	movimientos.setState(Frame.NORMAL);
	    	movimientos.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    	movimientos.setResizable(true);
	    	lista.setModel(listModel);
	    	lista.setVisibleRowCount(8);
	    	
	    	agregar.addActionListener(handler);
	    	confirmar.addActionListener(handler);
	    	cancelar.addActionListener(handler);
	    	quitar.addActionListener(handler);
	        
	        //GridLayout(filas, columnas, espacioEntreColumnas, espacioEntreFilas)
	    	north.setLayout(new GridLayout(1, 3, 1, 5));
	    	center.setLayout(new GridLayout(1, 2, 5, 5));
	    	bottom.setLayout(new GridLayout(1, 3, 5, 5));
	        
	        //Son los bordes vacios que rodean a los botones de la ventana 20 pixeles
	        // BorderFactory.createEmptyBorder(arriba, izquierda, abajo, derecha)
	        north.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	        
	        north.add(costo);
	        north.add(txtCosto);
	        north.add(agregar);
	        
	        center.add(new JScrollPane(lista));
	        bottom.add(quitar);
	        
			bottom.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			bottom.add(cancelar);
			bottom.add(confirmar);
	        
	        movimientos.setLayout(new BorderLayout());
	        movimientos.add(north, BorderLayout.NORTH);
	        movimientos.add(center, BorderLayout.CENTER);
	        movimientos.add(bottom, BorderLayout.PAGE_END);
	        //Se utiliza para darle un tamaño inicial minimo
	        //segun los componentes que se agregaron al diseño
	        
	        //Obtiene el ancho y alto necesarios para ajustar los elementos
	        //y que puedan visualizarse todos con el tamaño minimo de pantalla
	        movimientos.pack();
	        
	        movimientos.setSize((int) (movimientos.getSize().width * 1.6), movimientos.getSize().height);
	        
	        //Coloca la ventana en el centro de la pantalla.
	        movimientos.setLocationRelativeTo(null);
	        
			movimientos.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
						movimientos.setVisible(false);
						movimientos.dispose();
						callingJFrame.setVisible(true);
				}
			});
	        
	        //Hace visible la ventana
	        movimientos.setVisible(true);
	        
	    }
	    
		public Cobro(JFrame callingJFrame) {

			this();
			this.callingJFrame = callingJFrame;
		}
	    
		public void agregarElemento() 
		{
				Double valor = Double.parseDouble(txtCosto.getText());
				listModel.addElement(valor);			
		}
		
		public void quitarElemento() {
			listModel.removeElementAt(lista.getSelectedIndex());
		}
		
	    private class Handler implements ActionListener
	    {     
	        @Override
	        public void actionPerformed(ActionEvent ev)
	        {
	        	
	            if(ev.getSource() == agregar) 
	            {
	            	try {
	            		agregarElemento();
	            	}catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Inserte un precio valido", "My Pay", JOptionPane.ERROR_MESSAGE);
					}
	            	
	            }
	            else if(ev.getSource() == confirmar)
	            {
	            	movimientos.setVisible(false);
	            	movimientos.dispose();
	            	callingJFrame.setVisible(true);
	            }
				else if (ev.getSource() == cancelar) {
					movimientos.setVisible(false);
					movimientos.dispose();
					callingJFrame.setVisible(true);
				}
				else if (ev.getSource() == quitar) 
				{
	            	try {
	            		quitarElemento();
	            	}catch (Exception e) {
	            		JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun elemento", "My Pay", JOptionPane.ERROR_MESSAGE);
					}
				}
	                
	        }
	        
	    }
}
