/**
 * 自定义标签类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyLabel extends JLabel {

	public MyLabel(String text, int w, int h, int x, int y) {
		this.setText(text);
		this.setSize(w, h);
		this.setLocation(x, y);
	}

	public MyLabel(Element ele) {
		this.setText(ele.attributeValue("text"));
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		if (ele.attributeValue("path") != null) {
			this.setIcon(new ImageIcon(ele.attributeValue("path")));
		}
		if (ele.attributeValue("font") != null) {
			this.setFont(new Font(ele.attributeValue("font"), Font.PLAIN,
					Integer.parseInt(ele.attributeValue("fontsize"))));
		}
		if (ele.attributeValue("color") != null) {
			int r = Integer.parseInt(ele.attributeValue("r"));
			int g = Integer.parseInt(ele.attributeValue("g"));
			int b = Integer.parseInt(ele.attributeValue("b"));
			this.setForeground(new Color(r, g, b));
		}
	}

	public MyLabel(String text, Element ele) {
		this.setText(text);
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		if (ele.attributeValue("font") != null) {
			this.setFont(new Font(ele.attributeValue("font"), Font.PLAIN,
					Integer.parseInt(ele.attributeValue("fontsize"))));
		}
	}
}
