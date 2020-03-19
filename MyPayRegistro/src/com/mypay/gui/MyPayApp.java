package com.mypay.gui;

import javax.swing.SwingUtilities;

public class MyPayApp {
	
	public static void main(String args[]) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new MyPayRegistration();
			}
		});
		
	}

}
