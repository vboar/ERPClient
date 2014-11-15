package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class PanelConfig {
	
	/**
	 * 类名
	 */
	private String className;
	
	/**
	 * 按钮属性
	 */
	private ArrayList<ButtonConfig> buttonsCfg;

	/**
	 * 标签属性
	 */
	private ArrayList<LabelConfig> labelsCfg;
	
	/**
	 * 输入框属性
	 */
	private ArrayList<TextFieldConfig> textFieldsCfg;
	
	public PanelConfig(Element panel) {
		this.className = panel.attributeValue("className");
		// 获取按钮属性
		buttonsCfg = new ArrayList<ButtonConfig>();
		this.setButtonsCfg(panel);
		// 获取标签属性
		labelsCfg = new ArrayList<LabelConfig>();
		this.setLabelsCfg(panel);
		// 获取输入框属性
		textFieldsCfg = new ArrayList<TextFieldConfig>();
		this.setTextFieldCfg(panel);
	}
		
	private void setTextFieldCfg(Element panel) {
		@SuppressWarnings("unchecked")
		List<Element> textfields = panel.elements("textfield");
		// 获取标签属性
		for (Element textfield : textfields) {
			// 设置单个标签属性
			TextFieldConfig tc = new TextFieldConfig(
					Integer.parseInt(textfield.attributeValue("w")),
					Integer.parseInt(textfield.attributeValue("h")),
					Integer.parseInt(textfield.attributeValue("x")),
					Integer.parseInt(textfield.attributeValue("y")));
			textFieldsCfg.add(tc);
		}
	}

	public void setLabelsCfg(Element panel){
		@SuppressWarnings("unchecked")
		List<Element> labels = panel.elements("label");
		// 获取标签属性
		for (Element label : labels) {
			// 设置单个标签属性
			LabelConfig bl = new LabelConfig(label.attributeValue("text"),
					Integer.parseInt(label.attributeValue("w")),
					Integer.parseInt(label.attributeValue("h")),
					Integer.parseInt(label.attributeValue("x")),
					Integer.parseInt(label.attributeValue("y")));
			labelsCfg.add(bl);
		}
	}

	public void setButtonsCfg(Element panel){
		@SuppressWarnings("unchecked")
		List<Element> buttons = panel.elements("button");
		// 获取按钮属性
		for (Element button : buttons) {
			// 设置单个按钮属性
			ButtonConfig bc = new ButtonConfig(button.attributeValue("text"),
					Integer.parseInt(button.attributeValue("w")),
					Integer.parseInt(button.attributeValue("h")),
					Integer.parseInt(button.attributeValue("x")),
					Integer.parseInt(button.attributeValue("y")));
			buttonsCfg.add(bc);
		}
	}
	
	public ArrayList<ButtonConfig> getButtonsCfg() {
		return buttonsCfg;
	}

	public ArrayList<LabelConfig> getLabelsCfg() {
		return labelsCfg;
	}

	public ArrayList<TextFieldConfig> getTextFieldsCfg() {
		return textFieldsCfg;
	}

	public String getClassName() {
		return className;
	}

}
