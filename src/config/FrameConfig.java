package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig {

	/**
	 * 窗口标题
	 */
	private String title;
	/**
	 * 窗口高度
	 */
	private int height;
	/**
	 * 窗口宽度
	 */
	private int width;
	/**
	 * 窗口拔高
	 */
	private int windowUp;
	
	/**
	 * 面板配置
	 */
	private ArrayList<PanelConfig> panelsCfg;
	
	public FrameConfig(Element frame) {
		// 获取窗口标题
		this.title = frame.attributeValue("title");
		// 获取窗口宽度
		this.width = Integer.parseInt(frame.attributeValue("width"));
		// 获取窗口高度
		this.height = Integer.parseInt(frame.attributeValue("height"));
		// 获取窗口拔高
		this.windowUp = Integer.parseInt(frame.attributeValue("windowUp"));
		// 获取面板配置
		this.panelsCfg = new ArrayList<PanelConfig>();
		@SuppressWarnings("unchecked")
		List<Element> panels = frame.elements("panel");
		for(Element panel: panels){
			PanelConfig pcfg = new PanelConfig(panel);
			this.panelsCfg.add(pcfg);
		}
	}

	public String getTitle() {
		return title;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getWindowUp() {
		return windowUp;
	}

	public ArrayList<PanelConfig> getPanelsCfg() {
		return panelsCfg;
	}

}
