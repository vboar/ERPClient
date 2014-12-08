package ui.saleui;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.util.MyRadioButton;
import ui.util.MyTabbedPane;
import vo.PromotionVO;
import businesslogicservice.saleblservice.SaleBLService;
import config.DialogConfig;
import config.ERPConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class ShowChoosePromotionDialog extends JDialog{

	private MyTabbedPane tabpane;
	
	private PromotionTablePanel viptable;
	
	private PromotionTablePanel pricetable;
	
	private MyRadioButton vipBtn;
	
	private ArrayList<PromotionVO> pricelist;
	
	private ArrayList<PromotionVO> viplist;
	
	private SaleBLService controller;
	
	private JFrame frame;
	
	private DialogConfig cfg;
	
	public ShowChoosePromotionDialog(JFrame frame, ArrayList<PromotionVO> viplist,
			ArrayList<PromotionVO> pricelist){
		super(frame,true);
		this.pricelist = pricelist;
		this.viplist = viplist;
		this.cfg = ERPConfig.getCHOOSEPROMOTION_DIALOG_CONFIG();
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

	private void initComponent() {
		this.tabpane = new MyTabbedPane();
		this.tabpane.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.pricetable = new PromotionTablePanel(
				new TableConfig(cfg.getTablepane()),pricelist);
		this.viptable = new PromotionTablePanel(
				new TableConfig(cfg.getTablepane()),viplist);
		this.tabpane.add(this.viptable,"VIP客户优惠");
		this.tabpane.add(this.pricetable,"总价优惠");
		this.add(this.tabpane);
	}
	
	
	
}
