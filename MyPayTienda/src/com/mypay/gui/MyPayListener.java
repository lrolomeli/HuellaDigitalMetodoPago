package com.mypay.gui;

import java.util.EventListener;

public interface MyPayListener extends EventListener{
	public void myPayEventOcurred(MyPayEvent e);
}
