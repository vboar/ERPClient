package config;

import org.dom4j.Element;

public class UserInfoDialogConfig extends ComponentConfig{

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
	
	public UserInfoDialogConfig(Element userinfo){
		this.buttons = userinfo.element("buttons");
		this.labels = userinfo.element("labels");
		this.textFields = userinfo.element("textfields");
		this.comboboxes = userinfo.element("comboboxes");
		this.w = Integer.parseInt(userinfo.attributeValue("width"));
		this.h = Integer.parseInt(userinfo.attributeValue("height"));
		this.x = Integer.parseInt(userinfo.attributeValue("x"));
		this.y = Integer.parseInt(userinfo.attributeValue("y"));
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
	
}
