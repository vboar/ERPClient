package ui.util;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MyLabel extends JLabel{

	public MyLabel(String text, int w, int h, int x,int y){
		this.setText(text);
		this.setSize(w,h);
		this.setLocation(x, y);
		this.setHorizontalAlignment(CENTER);
	}
}
