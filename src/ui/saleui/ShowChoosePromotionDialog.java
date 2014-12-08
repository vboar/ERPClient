package ui.saleui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyTabbedPane;
import vo.PromotionVO;
import config.DialogConfig;
import config.ERPConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class ShowChoosePromotionDialog extends JDialog{

	private MyTabbedPane tabpane;
	
	private PromotionTablePanel viptable;
	
	private PromotionTablePanel pricetable;
	
	private MyButton addBtn;
	
	private MyLabel vip;
	
	private MyLabel price;
	
	private ArrayList<PromotionVO> pricelist;
	
	private ArrayList<PromotionVO> viplist;
	
	private ArrayList<PromotionVO> selectlist;
	
	private JFrame frame;
	
	private CreatePanel panel;
	
	private DialogConfig cfg;
	
	public ShowChoosePromotionDialog(JFrame frame, ArrayList<PromotionVO> viplist,
			ArrayList<PromotionVO> pricelist, CreatePanel panel){
		super(frame,true);
		this.panel = panel;
		this.frame = frame;
		this.pricelist = pricelist;
		this.viplist = viplist;
		this.selectlist = new ArrayList<PromotionVO>();
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
		this.tabpane.setBounds(0, 0, this.getWidth(), this.getHeight()-100);
		this.pricetable = new PromotionTablePanel(
				new TableConfig(cfg.getTablepane()),pricelist);
		this.viptable = new PromotionTablePanel(
				new TableConfig(cfg.getTablepane()),viplist);
		this.tabpane.add(this.viptable,"VIP客户优惠");
		this.tabpane.add(this.pricetable,"总价优惠");
		this.add(this.tabpane);
		this.vip = new MyLabel(cfg.getLabels().element("vip"));
		this.add(vip);
		this.add(price);
		this.price = new MyLabel(cfg.getLabels().element("price"));
		this.addBtn = new MyButton(cfg.getButtons().element("add"));
		this.addBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				selectlist.clear();
				if(viptable.getSeleted()!=null){
					vip.setText(viptable.getSeleted().giftList.toString());
					selectlist.add(viptable.getSeleted());
				}
				if(pricetable.getSeleted()!=null){
					price.setText(pricetable.getSeleted().giftList.toString());
					selectlist.add(pricetable.getSeleted());
				}
				panel.getPromotionlist().clear();
				panel.getPromotionlist().addAll(selectlist);
			}
		});
		this.add(this.addBtn);
	}
	
	
	
}
