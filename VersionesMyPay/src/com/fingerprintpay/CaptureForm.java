package com.fingerprintpay;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.capture.*;
import com.digitalpersona.onetouch.capture.event.*;
import com.digitalpersona.onetouch.processing.*;

/*Este codigo es completamente parte del sdk del sensor digital persona*/
/*Extiende de JDialog por lo tanto es una ventana que muestra un mensaje y nada mas.*/
public class CaptureForm
	extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
	private JLabel picture = new JLabel();
	private JTextField prompt = new JTextField();
	private JTextArea log = new JTextArea();
	private JTextField status = new JTextField("[status line]");
	
    public CaptureForm(Frame owner) {
        super(owner, true);
        setTitle("Lector de Huellas");

		setLayout(new BorderLayout());
		rootPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		picture.setPreferredSize(new Dimension(240, 280));
		picture.setBorder(BorderFactory.createLoweredBevelBorder());
		prompt.setFont(UIManager.getFont("Panel.font"));
		prompt.setEditable(false);
		prompt.setColumns(40);
		prompt.setMaximumSize(prompt.getPreferredSize());
		prompt.setBorder(
				BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Prompt:"),
					BorderFactory.createLoweredBevelBorder()
				));
		log.setColumns(40);
		log.setEditable(false);
		log.setFont(UIManager.getFont("Panel.font"));
		JScrollPane logpane = new JScrollPane(log);
		logpane.setBorder(
				BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Status:"),
					BorderFactory.createLoweredBevelBorder()
				));
		
		status.setEditable(false);
		status.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		status.setFont(UIManager.getFont("Panel.font"));
		
		JButton quit = new JButton("Close");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	owner.setVisible(true);
            	setVisible(false); 
            	}
            });
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
					setVisible(false);
					owner.setVisible(true);
			}
		});

		JPanel right = new JPanel(new BorderLayout());
		right.setBackground(Color.getColor("control"));
		right.add(prompt, BorderLayout.PAGE_START);
		right.add(logpane, BorderLayout.CENTER);

		JPanel center = new JPanel(new BorderLayout());
		center.setBackground(Color.getColor("control"));
		center.add(right, BorderLayout.CENTER);
		center.add(picture, BorderLayout.LINE_START);
		center.add(status, BorderLayout.PAGE_END);
			
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		bottom.setBackground(Color.getColor("control"));
		bottom.add(quit);

		setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.PAGE_END);
		
		//Al abrir la ventana o mostrar la ventana de capture form
		//Se comienza a ejecutar el metodo de init y start
		//Y al cerrar la ventana se ejecuta el metodo stop
		addComponentListener(new ComponentAdapter() {
			@Override 
			public void componentShown(ComponentEvent e) {
				//El abrir la ventana
				init();
				start();
			}
			@Override 
			public void componentHidden(ComponentEvent e) {
				stop();
			}
			
		});
		
		pack();
        setLocationRelativeTo(null);
	}

	protected void init()
	{
		capturer.addDataListener(new DPFPDataAdapter() {
			@Override public void dataAcquired(final DPFPDataEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("La huella fue capturada correctamente");
					setPrompt("Vuelva a colocar la misma huella.");
					process(e.getSample());
				}});
			}
		});
		capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
			@Override public void readerConnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
		 			makeReport("El lector ha sido conectado.");
				}});
			}
			@Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("El lector ha sido desconectado.");
				}});
			}
		});
		capturer.addSensorListener(new DPFPSensorAdapter() {
			@Override public void fingerTouched(final DPFPSensorEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("Has colocado la huella en el sensor.");
				}});
			}
			@Override public void fingerGone(final DPFPSensorEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("Has retirado la huella del sensor.");
				}});
			}
		});
		capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
			@Override public void onImageQuality(final DPFPImageQualityEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD))
						makeReport("La huella se registro con buena calidad.");
					else
						makeReport("No es buena la calidad de la huella registra de nuevo.");
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
		setPrompt("Lector listo para capturar, agrega tu huella.");
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
	
}
