package config;

import org.dom4j.Element;

/**
 * 对话框配置对象
 * @author JanelDQ
 * @date 2014/11/28
 */
public class DialogConfig extends ComponentConfig{

	/**
	 * 标题
	 */
	private String title;
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

	/**
	 * 构造函数
	 * @param info 配置对象
	 */
	public DialogConfig(Element info){
		this.buttons = info.element("buttons");
		this.labels = info.element("labels");
		this.textFields = info.element("textfields");
		this.comboboxes = info.element("comboboxes");
		this.title = info.attributeValue("title");
		this.w = Integer.parseInt(info.attributeValue("width"));
		this.h = Integer.parseInt(info.attributeValue("height"));
		this.x = Integer.parseInt(info.attributeValue("x"));
		this.y = Integer.parseInt(info.attributeValue("y"));
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

	public String getTitle() {
		return title;
	}
	
}
