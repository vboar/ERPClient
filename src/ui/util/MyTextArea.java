package ui.util;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyTextArea extends JPanel{

	private JTextArea area;
	
	public MyTextArea(Element ele){
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));	
		this.setLayout(null);
		this.area = new JTextArea();
		JScrollPane jsp = new JScrollPane();
		jsp.setBounds(0, 0, this.getWidth(), this.getHeight());
		jsp.setViewportView(area);
		this.add(jsp);
		this.setVisible(true);
	}

	public JTextArea getArea() {
		return area;
	}
	
	
	
}
