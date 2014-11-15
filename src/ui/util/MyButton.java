package ui.util;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class MyButton extends JButton{

	public MyButton(String text, int w,int h, int x,int y){
		this.setText(text);
		this.setSize(w, h);
		this.setLocation(x, y);
	}
}
