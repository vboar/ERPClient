package ui.util;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyRadioButton extends JRadioButton{
	
	public MyRadioButton(String text){
		super(text);
		this.setFocusPainted(false);
	}
	
	public MyRadioButton(String text, int w,int h, int x,int y){
		this.setText(text);
		this.setSize(w, h);
		this.setLocation(x, y);
		this.setFocusPainted(false);
	}
	
	public MyRadioButton(Element ele){
		this.setText(ele.attributeValue("text"));
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		if(ele.attributeValue("path")!=null){
			this.setIcon(new ImageIcon(ele.attributeValue("path")));
			this.setBorderPainted(false);
			this.setContentAreaFilled(false);
		}
		this.setFocusPainted(false);
	}
	
}
