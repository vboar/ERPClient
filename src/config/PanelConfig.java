package config;

import org.dom4j.Element;

public class PanelConfig {
	
	/**
	 * 类名
	 */
	private String className;
	
	/**
	 * 按钮配置
	 */
	private Element buttons;
	
	/**
	 * 标签配置
	 */
	private Element labels;
	
	/**
	 * 输入框配置
	 */
	private Element textFields;
	
	/**
	 * 复选框配置
	 */
	private Element comboboxes;

	private int width;
	
	private int height;
	
	private int x;
	
	private int y;
	
	public PanelConfig(Element panel) {
		this.className = panel.attributeValue("className");
		// 获取按钮属性
		this.buttons = panel.element("buttons");
		// 获取标签属性
		this.labels = panel.element("labels");
		// 获取输入框属性
		this.textFields = panel.element("textfields");
		// 获取复选框属性
		this.comboboxes = panel.element("comboboxes");
		this.width = Integer.parseInt(panel.attributeValue("width"));
		this.height = Integer.parseInt(panel.attributeValue("height"));
		this.x = Integer.parseInt(panel.attributeValue("x"));
		this.y = Integer.parseInt(panel.attributeValue("y"));
	}	


	public Element getButtons() {
		return buttons;
	}

	public Element getLabels() {
		return labels;
	}

	public Element getTextFields() {
		return textFields;
	}
	
	public Element getComboboxes() {
		return comboboxes;
	}

	public String getClassName() {
		return className;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
