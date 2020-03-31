
package com.mypay.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.capture.*;
import com.digitalpersona.onetouch.capture.event.*;
import com.digitalpersona.onetouch.processing.*;
import com.mypay.controller.Controller;


public class DigitalPersona extends JDialog
{
	private DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
	private JLabel picture = new JLabel();
	private JTextField prompt = new JTextField();
	private JTextArea log = new JTextArea();
	private JTextField status = new JTextField("[status line]");
	
    public DigitalPersona(Frame owner) {
    	//En cuanto se abria la ventana de verificacion se corria el metodo init y el metodo start
        super (owner, true);
        setTitle("Registro de huella MyPay");
		setLayout(new BorderLayout());
		rootPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(Color.DARK_GRAY);
		
		picture.setPreferredSize(new Dimension(150, 250));
		picture.setBorder(BorderFactory.createLoweredBevelBorder());
		picture.setBackground(Color.DARK_GRAY);
		prompt.setBackground(Color.DARK_GRAY);
		prompt.setFont(new Font("Panel.font", Font.PLAIN, 14));
		prompt.setForeground(Color.WHITE);
		prompt.setEditable(false);
		prompt.setColumns(30);
		prompt.setMaximumSize(prompt.getPreferredSize());
		prompt.setBorder(
				BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 5, 10), "Indicaciones:",
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Panel.font", Font.PLAIN, 12), Color.WHITE),
					BorderFactory.createLoweredBevelBorder()
				));
		log.setBackground(Color.DARK_GRAY);
		log.setForeground(Color.WHITE);
		log.setColumns(30);
		log.setEditable(false);
		log.setFont(new Font("Panel.font", Font.PLAIN, 14));
		JScrollPane logpane = new JScrollPane(log);
		logpane.setBackground(Color.DARK_GRAY);
		logpane.setBorder(
				BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 5, 10), "Estado:",
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Panel.font", Font.PLAIN, 12), Color.WHITE),
					BorderFactory.createLoweredBevelBorder()
				));
		status.setBackground(Color.DARK_GRAY);
		status.setForeground(Color.WHITE);
		status.setEditable(false);
		status.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		status.setFont(UIManager.getFont("Panel.font"));
		
		JButton quit = new JButton("Cerrar");
		quit.setBackground(Color.DARK_GRAY);
		quit.setForeground(Color.WHITE);
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	clean();
            }
        });
        
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				clean();
			}
			
		});

		JPanel right = new JPanel(new BorderLayout());
		right.setBackground(Color.DARK_GRAY);
		right.add(prompt, BorderLayout.PAGE_START);
		right.add(logpane, BorderLayout.CENTER);

		JPanel center = new JPanel(new BorderLayout());
		center.setBackground(Color.DARK_GRAY);
		center.add(right, BorderLayout.LINE_START);
		center.add(picture, BorderLayout.CENTER);
		center.add(status, BorderLayout.AFTER_LAST_LINE);
		
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		bottom.setBackground(Color.LIGHT_GRAY);
		bottom.add(quit);

		setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.PAGE_END);
		
		pack();
        setLocationRelativeTo(null);
	}
    
    protected void clean() {
		stop();
		setVisible(false);
    }

	protected void init()
	{
		capturer.addDataListener(new DPFPDataAdapter() {
			@Override public void dataAcquired(final DPFPDataEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("El sensor ha leido la huella.");
					setPrompt("Huella leida. Vuelve a colocar la misma huella.");
					process(e.getSample());
					
				}});
			}
		});
		capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
			@Override public void readerConnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("Lector de Huellas Conectado.");
				}});
			}
			@Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("Lector de Huellas Desconectado.");
				}});
			}
		});
		capturer.addSensorListener(new DPFPSensorAdapter() {
			@Override public void fingerTouched(final DPFPSensorEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					//makeReport("<3");
				}});
			}
			@Override public void fingerGone(final DPFPSensorEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					//makeReport("</3");
				}});
			}
		});
		capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
			@Override public void onImageQuality(final DPFPImageQualityEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD))
						makeReport(":)");
					else
						makeReport(":(");
				}});
			}
		});
	}

	protected void process(DPFPSample sample)
	{
		// Draw fingerprint sample image.
		drawPicture(convertSampleToBitmap(sample));

	}

	protected void start()
	{
		capturer.startCapture();
		setPrompt("Captura tu huella y olvidate de cargar efectivo.");
	}

	protected void stop()
	{
		capturer.stopCapture();
	}
	
	public void setStatus(String string) {
		status.setText(string);
	}
	public void setPrompt(String string) {
		prompt.setText(string);
	}
	public void makeReport(String string) {
		log.append(string + "\n");
	}
	
	public void drawPicture(Image image) {
		picture.setIcon(new ImageIcon(
			image.getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	protected Image convertSampleToBitmap(DPFPSample sample) {
		return DPFPGlobal.getSampleConversionFactory().createImage(sample);
	}

	protected DPFPFeatureSet extractFeatures(DPFPSample sample, DPFPDataPurpose purpose)
	{
		DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
		try {
			return extractor.createFeatureSet(sample, purpose);
		} catch (DPFPImageQualityException e) {
			return null;
		}
	}
	
	public JTextArea getLogArea() {
		return log;
	}
	
	
}
