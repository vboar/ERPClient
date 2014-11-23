/**
 * 自定义复选框类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.util;

import java.util.List;

import javax.swing.JComboBox;

import org.dom4j.Element;

@SuppressWarnings("serial")
public class MyComboBox extends JComboBox<String> {

	public MyComboBox(Element ele){
		this.setSize(Integer.parseInt(ele.attributeValue("w")),
				Integer.parseInt(ele.attributeValue("h")));
		this.setLocation(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")));
		@SuppressWarnings("unchecked")
		List<Element> strs = ele.elements("string");
		for(int i=0; i<strs.size(); ++i){
			this.addItem(strs.get(i).attributeValue("str"));
		}
		this.setSelectedIndex(0);
	}
}
