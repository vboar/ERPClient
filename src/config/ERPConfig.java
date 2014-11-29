package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ERPConfig {
	
	private static FrameConfig HOMEFRAME_CONFIG = null;
	
	private static FrameConfig LOGINFRAME_CONFIG = null;
	
	private static InfoDialogConfig USERINFO_DIALOG_CONFIG = null;
	
	private static InfoDialogConfig CATEGORYINFO_DIALOG_CONFIG = null;
	
	static{
		try {
			// 创建XML读取器
			SAXReader reader = new SAXReader();
			// 读取XML文件
			Document doc = reader.read("cfg.xml");
			// 获得XML文件根节点
			Element erp = doc.getRootElement();
			// 创建登录界面配置对象
			LOGINFRAME_CONFIG = new FrameConfig(erp.element("loginframe"));
			// 创建主界面配置对象
			HOMEFRAME_CONFIG = new FrameConfig(erp.element("frame"));
			// 创建添加用户对话框配置对象
			USERINFO_DIALOG_CONFIG = new InfoDialogConfig(erp.element("userinfodialog"));
			// 创建添加商品分类对话框配置对象
			CATEGORYINFO_DIALOG_CONFIG = new InfoDialogConfig(erp.element("categoryinfodialog"));
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

	public static InfoDialogConfig getUSERINFO_DIALOG_CONFIG() {
		return USERINFO_DIALOG_CONFIG;
	}

	public static InfoDialogConfig getCATEGORYINFO_DIALOG_CONFIG() {
		return CATEGORYINFO_DIALOG_CONFIG;
	}	
	
}
