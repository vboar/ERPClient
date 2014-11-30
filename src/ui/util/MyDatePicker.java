package ui.util;

import java.text.SimpleDateFormat;

import org.dom4j.Element;
import org.jdesktop.swingx.JXDatePicker;

@SuppressWarnings("serial")
public class MyDatePicker extends JXDatePicker {

	public MyDatePicker(Element ele){
		this.setBounds(Integer.parseInt(ele.attributeValue("x")), 
				Integer.parseInt(ele.attributeValue("y")), 
				Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.setFormats(dateFormat);
	}
	
}
