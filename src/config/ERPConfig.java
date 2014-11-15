package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ERPConfig {
	
	private static FrameConfig HOMEFRAME_CONFIG = null;
	
	private static FrameConfig LOGINFRAME_CONFIG = null;
	
	static{
		try {
			// 创建XML读取器
			SAXReader reader = new SAXReader();
			// 读取XML文件
			Document doc = reader.read("cfg.xml");
			// 获得XML文件根节点
			Element frame = doc.getRootElement();
			// 创建登录界面配置对象
			LOGINFRAME_CONFIG = new FrameConfig(frame.element("loginframe"));
			// 创建主界面配置对象
			HOMEFRAME_CONFIG = new FrameConfig(frame.element("frame"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public static FrameConfig getHOMEFRAME_CONFIG() {
		return HOMEFRAME_CONFIG;
	}

	public static FrameConfig getLOGINFRAME_CONFIG() {
		return LOGINFRAME_CONFIG;
	}	
	
}
