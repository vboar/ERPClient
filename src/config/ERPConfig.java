package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ERPConfig {
	
	private static FrameConfig HOMEFRAME_CONFIG = null;
	
	private static FrameConfig LOGINFRAME_CONFIG = null;
	
	private static InfoDialogConfig USERINFO_DIALOG_CONFIG = null;
	
<<<<<<< HEAD
	private static InfoDialogConfig CATEGORYINFO_DIALOG_CONFIG = null;
=======
	private static InfoDialogConfig CUSTOMERINFO_DIALOG_CONFIG = null;
>>>>>>> c64ab488bab5387be8c5f679556a1362b1e5c98b
	
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
			// 创建添加/修改用户对话框配置对象
			USERINFO_DIALOG_CONFIG = new InfoDialogConfig(erp.element("userinfodialog"));
<<<<<<< HEAD
			// 创建添加商品分类对话框配置对象
			CATEGORYINFO_DIALOG_CONFIG = new InfoDialogConfig(erp.element("categoryinfodialog"));
=======
			// 创建添加/修改客户对话框配置对象
			CUSTOMERINFO_DIALOG_CONFIG = new InfoDialogConfig(erp.element("customerinfodialog"));
>>>>>>> c64ab488bab5387be8c5f679556a1362b1e5c98b
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

<<<<<<< HEAD
	public static InfoDialogConfig getCATEGORYINFO_DIALOG_CONFIG() {
		return CATEGORYINFO_DIALOG_CONFIG;
	}	
=======
	public static InfoDialogConfig getCUSTOMERINFO_DIALOG_CONFIG() {
		return CUSTOMERINFO_DIALOG_CONFIG;
	}
>>>>>>> c64ab488bab5387be8c5f679556a1362b1e5c98b
	
}
