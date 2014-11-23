/**
 * 自定义输入框类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.util;

import javax.swing.JTextField;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyTextField extends JTextField{

	public MyTextField(int w, int h, int x, int y){
		this.setSize(w, h);
		this.setLocation(x, y);
	}
	
	public MyTextField(Element ele){
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));;
		
	}
}
