package ui.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JCheckBox;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyCheckBox extends JCheckBox{

	public MyCheckBox(String text, int w, int h, int x,int y){
		this.setText(text);
		this.setSize(w,h);
		this.setLocation(x, y);
	}
	
	public MyCheckBox(Element ele){
		this.setText(ele.attributeValue("text"));
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		if(ele.attributeValue("font")!=null){
			this.setFont(new Font(ele.attributeValue("font"),Font.PLAIN,
					Integer.parseInt(ele.attributeValue("fontsize"))));
		}
		if(ele.attributeValue("color")!=null){
			int r=Integer.parseInt(ele.attributeValue("r"));
			int g=Integer.parseInt(ele.attributeValue("g"));
			int b=Integer.parseInt(ele.attributeValue("b"));
			this.setForeground(new Color(r,g,b));
		}
		this.setOpaque(false);			
	}
}
