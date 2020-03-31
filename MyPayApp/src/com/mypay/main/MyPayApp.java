package com.mypay.main;

import javax.swing.SwingUtilities;

import com.mypay.gui.MyPayFrame;


public class MyPayApp {
	
	public static void main(String args[]) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MyPayFrame();				
				
			}
		});
		
	}

}
