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
	 * 日期选择器配置
	 */
	private Element datePicker;
	
	/**
	 * 单选框配置
	 */
	private Element checkBox;
	
	/**
	 * 表格配置
	 */
	private Element tablepane;
	
	/**
	 * 大输入框配置
	 */
	private Element textarea;
	
	/**
	 * 构造函数
	 * @param info 配置对象
	 */
	public DialogConfig(Element info){
		this.buttons = info.element("buttons");
		this.labels = info.element("labels");
		this.textFields = info.element("textfields");
		this.comboboxes = info.element("comboboxes");
		this.datePicker = info.element("datepicker");
		this.checkBox = info.element("checkbox");
		this.tablepane = info.element("tablepane");
		this.title = info.attributeValue("title");
		this.textarea = info.element("textarea");
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

	public Element getDatePicker() {
		return datePicker;
	}

	public Element getCheckBox() {
		return checkBox;
	}

	public Element getTablepane() {
		return tablepane;
	}

	public Element getTextarea() {
		return textarea;
	}
	
}
