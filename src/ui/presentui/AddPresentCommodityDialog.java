package ui.presentui;

import javax.swing.JFrame;

import ui.util.AddCommodityDialog;
import ui.util.AddCommodityLineItem;
import ui.util.FuzzySearch;
import config.ERPConfig;

/**
 * 添加赠送单商品信息对话框
 * @author JanelDQ
 * @date 2014/12/3
 */
@SuppressWarnings("serial")
public class AddPresentCommodityDialog extends AddCommodityDialog implements FuzzySearch{

	/**
	 * 构造函数
	 * @param panel 赠送单面板
	 * @param frame 主窗口
	 */
	public AddPresentCommodityDialog(AddCommodityLineItem panel,JFrame frame){
		super(panel,frame,true);
		this.cfg = ERPConfig.getADDPRESENTCOMMODITY_DIALOG_CONFIG();
		// 设置对话框基本属性
		this.setTitle(cfg.getTitle());
		this.setBounds(cfg.getX(), cfg.getW(), cfg.getW(), cfg.getH());
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
        // 初始化组件
		this.initComponent();
		this.setVisible(true);
	}

}
