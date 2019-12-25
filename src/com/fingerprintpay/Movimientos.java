package com.fingerprintpay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Movimientos{

		private final JButton cobro;
	    private final JButton devolucion;
	    private final JButton regresar;
	    private Handler handler;
	    private JPanel center;
	    private JPanel bottom;
	    private JFrame movimientos;
	    private JFrame callingJFrame;
	    
	    private Movimientos() {
	    	movimientos = new JFrame("MyPay");
	    	cobro = new JButton("Cobro");
	    	devolucion = new JButton("Devolucion");
	    	regresar = new JButton("Regresar a Menu Principal");
	        handler = new Handler();
	        
	        center = new JPanel();
	        bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	        
	    	movimientos.setState(Frame.NORMAL);
	    	movimientos.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    	movimientos.setResizable(true);
	    	cobro.addActionListener(handler);
	    	devolucion.addActionListener(handler);
	    	regresar.addActionListener(handler);
	        
	        //GridLayout(filas, columnas, espacioEntreColumnas, espacioEntreFilas)
	        center.setLayout(new GridLayout(2, 1, 5, 5));
	        
	        //Son los bordes vacios que rodean a los botones de la ventana 20 pixeles
	        // BorderFactory.createEmptyBorder(arriba, izquierda, abajo, derecha)
	        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        
	        center.add(cobro);
	        center.add(devolucion);
	        
			bottom.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
			bottom.add(regresar);

	        
	        movimientos.setLayout(new BorderLayout());
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
	    
		public Movimientos(JFrame callingJFrame) {

			this();
			this.callingJFrame = callingJFrame;
		}
	    
	    private class Handler implements ActionListener
	    {     
	        @Override
	        public void actionPerformed(ActionEvent ev)
	        {
	        	movimientos.setVisible(false);
	        	
	            if(ev.getSource() == cobro) 
	            {
	            	new Cobro(movimientos);
	            }
	            else if(ev.getSource() == devolucion)
	            {
	            	movimientos.dispose();
	            	callingJFrame.setVisible(true);
	            }
				else if (ev.getSource() == regresar) {
					movimientos.dispose();
					callingJFrame.setVisible(true);
				}
	                
	        }
	        
	    }
}
