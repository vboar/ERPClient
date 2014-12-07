package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ERPConfig {
	
	private static FrameConfig HOMEFRAME_CONFIG = null;
	
	private static FrameConfig LOGINFRAME_CONFIG = null;
	
	private static DialogConfig USERINFO_DIALOG_CONFIG = null;

	private static DialogConfig CATEGORYINFO_DIALOG_CONFIG = null;
	
	private static DialogConfig CUSTOMERINFO_DIALOG_CONFIG = null;
	
	private static DialogConfig ACCOUNTINFO_DIALOG_CONFIG = null;
	
	private static DialogConfig COMMODITYINFO_DIALOG_CONFIG = null;

	private static DialogConfig ADDRECEIPTACCOUNT_DIALOG_CONFIG = null;
	
	private static DialogConfig ADDPRESENTCOMMODITY_DIALOG_CONFIG = null;
	
	private static DialogConfig ADDCOMMODITY_DIALOG_CONFIG = null;
	
	private static DialogConfig ADDEXCEPTIONLINEITEM_DIALOG_CONFIG = null;
	
	private static DialogConfig LEVEL_GIFT_DIALOG_CONFIG = null;
	
	private static DialogConfig TOTAL_GIFT_DIALOG_CONFIG = null;
	
	private static DialogConfig SPECIAL_OFFER_DIALOG_CONFIG = null;

	private static DialogConfig ADDCASHINFO_DIALOG_CONFIG = null;
	
	private static DialogConfig ADDSALE_DIALOG_CONFIG = null;
	
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
			USERINFO_DIALOG_CONFIG = new DialogConfig(erp.element("userinfodialog"));
			// 创建添加商品分类对话框配置对象
			CATEGORYINFO_DIALOG_CONFIG = new DialogConfig(erp.element("categoryinfodialog"));
			// 创建添加/修改客户对话框配置对象
			CUSTOMERINFO_DIALOG_CONFIG = new DialogConfig(erp.element("customerinfodialog"));
			// 创建添加/修改账户对话框配置对象
			ACCOUNTINFO_DIALOG_CONFIG = new DialogConfig(erp.element("accountinfodialog"));
			// 创建添加/修改商品对话框配置对象
			COMMODITYINFO_DIALOG_CONFIG = new DialogConfig(erp.element("commodityinfodialog"));
			// 创建添加收款账户账户对话框配置对象
			ADDRECEIPTACCOUNT_DIALOG_CONFIG = new DialogConfig(erp.element("addreceiptaccountinfodialog"));
			// 创建现金费用单添加条目对框框配置对象
			ADDCASHINFO_DIALOG_CONFIG = new DialogConfig(erp.element("addcashinfodialog"));
			// 创建添加赠送单商品对话框配置对象
			ADDPRESENTCOMMODITY_DIALOG_CONFIG = new DialogConfig(erp.element("addpresentcommodityinfodialog"));
			// 创建添加库存报溢报损单商品对话框配置对象
			ADDEXCEPTIONLINEITEM_DIALOG_CONFIG = new DialogConfig(erp.element("addexceptionlineitemdialog"));
			// 创建添加针对客户级别的促销策略对话框配置对象
			LEVEL_GIFT_DIALOG_CONFIG = new DialogConfig(erp.element("customergiftinfodialog"));
			// 创建添加针对总价的促销策略对话框配置对象
			TOTAL_GIFT_DIALOG_CONFIG = new DialogConfig(erp.element("totalgiftinfodialog"));
			// 创建添加特价包促销策略对话框配置对象
			SPECIAL_OFFER_DIALOG_CONFIG = new DialogConfig(erp.element("specialofferinfodialog"));
			// 创建添加组合商品对话框配置对象
			ADDCOMMODITY_DIALOG_CONFIG = new DialogConfig(erp.element("addcommodityinfodialog"));
			ADDSALE_DIALOG_CONFIG = new DialogConfig(erp.element("addsaledialog"));
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

	public static DialogConfig getUSERINFO_DIALOG_CONFIG() {
		return USERINFO_DIALOG_CONFIG;
	}

	public static DialogConfig getCATEGORYINFO_DIALOG_CONFIG() {
		return CATEGORYINFO_DIALOG_CONFIG;
	}	

	public static DialogConfig getCUSTOMERINFO_DIALOG_CONFIG() {
		return CUSTOMERINFO_DIALOG_CONFIG;
	}

	public static DialogConfig getACCOUNTINFO_DIALOG_CONFIG() {
		return ACCOUNTINFO_DIALOG_CONFIG;
	}

	public static DialogConfig getCOMMODITYINFO_DIALOG_CONFIG() {
		return COMMODITYINFO_DIALOG_CONFIG;
	}

	public static DialogConfig getADDRECEIPTACCOUNT_DIALOG_CONFIG() {
		return ADDRECEIPTACCOUNT_DIALOG_CONFIG;
	}

	public static DialogConfig getADDPRESENTCOMMODITY_DIALOG_CONFIG() {
		return ADDPRESENTCOMMODITY_DIALOG_CONFIG;
	}

	public static DialogConfig getADDEXCEPTIONLINEITEM_DIALOG_CONFIG() {
		return ADDEXCEPTIONLINEITEM_DIALOG_CONFIG;
	}

	public static DialogConfig getLEVEL_GIFT_DIALOG_CONFIG() {
		return LEVEL_GIFT_DIALOG_CONFIG;
	}

	public static DialogConfig getTOTAL_GIFT_DIALOG_CONFIG() {
		return TOTAL_GIFT_DIALOG_CONFIG;
	}

	public static DialogConfig getADDCOMMODITY_DIALOG_CONFIG() {
		return ADDCOMMODITY_DIALOG_CONFIG;
	}

	public static DialogConfig getSPECIAL_OFFER_DIALOG_CONFIG() {
		return SPECIAL_OFFER_DIALOG_CONFIG;
	}

	public static DialogConfig getADDCASHINFO_DIALOG_CONFIG() {
		return ADDCASHINFO_DIALOG_CONFIG;
	}

	public static DialogConfig getADDSALE_DIALOG_CONFIG() {
		return ADDSALE_DIALOG_CONFIG;
	}

}
