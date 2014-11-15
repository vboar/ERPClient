package ui.util;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MyTextField extends JTextField{

	public MyTextField(int w, int h, int x, int y){
		this.setSize(w, h);
		this.setLocation(x, y);
	}
}
