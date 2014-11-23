/**
 * 自定义标签类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.util;

import javax.swing.JLabel;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyLabel extends JLabel{

	public MyLabel(String text, int w, int h, int x,int y){
		this.setText(text);
		this.setSize(w,h);
		this.setLocation(x, y);
		this.setHorizontalAlignment(CENTER);
	}
	
	public MyLabel(Element ele){
		this.setText(ele.attributeValue("text"));
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));;
		this.setHorizontalAlignment(CENTER);
		
	}
	
	public MyLabel(String text,Element ele){
		this.setText(text);
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));;
		this.setHorizontalAlignment(CENTER);
		
	}
}
