/**
 * 自定义按钮类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.util;

import javax.swing.JButton;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyButton extends JButton{

	public MyButton(String text, int w,int h, int x,int y){
		this.setText(text);
		this.setSize(w, h);
		this.setLocation(x, y);
	}
	
	public MyButton(Element ele){
		this.setText(ele.attributeValue("text"));
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));;
		
	}
}
