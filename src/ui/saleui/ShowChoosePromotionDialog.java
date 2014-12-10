package ui.saleui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
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
	
	private CreateSalePanel panel;
	
	private DialogConfig cfg;
	
	public ShowChoosePromotionDialog(final JFrame frame, ArrayList<PromotionVO> viplist,
			ArrayList<PromotionVO> pricelist, final CreateSalePanel panel){
		super(frame,true);
		this.panel = panel;
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
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "确认选择此促销优惠？","确认提示",
						MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					panel.calTotal();
					dispose();
				}
			}
		});
		this.setVisible(true);
		
	}

	private void initComponent() {
		this.tabpane = new MyTabbedPane();
		this.tabpane.setBounds(0, 0, this.getWidth(), this.getHeight()-140);
		if(pricelist!=null&&pricelist.size()>0){
			this.pricetable = new PromotionTablePanel(
				new TableConfig(cfg.getTablepane()),pricelist);
			this.tabpane.add(this.pricetable,"总价优惠");
		}
		if(viplist!=null&&viplist.size()>0){
			this.viptable = new PromotionTablePanel(
				new TableConfig(cfg.getTablepane()),viplist);
			this.tabpane.add(this.viptable,"VIP客户优惠");
		}	
		this.add(this.tabpane);
		this.vip = new MyLabel(cfg.getLabels().element("viplab"));
		this.price = new MyLabel(cfg.getLabels().element("pricelab"));
		this.add(vip);
		this.add(price);
		this.add(new MyLabel(cfg.getLabels().element("vip")));
		this.add(new MyLabel(cfg.getLabels().element("price")));
		this.addBtn = new MyButton(cfg.getButtons().element("add"));
		this.addBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				selectlist.clear();
				if(viptable!=null&&viptable.getSeleted()!=null){
					vip.setText(viptable.getSeleted().id);
					selectlist.add(viptable.getSeleted());
				}
				if(pricetable!=null&&pricetable.getSeleted()!=null){
					price.setText(pricetable.getSeleted().id);
					selectlist.add(pricetable.getSeleted());
				}
				panel.getPromotionlist().clear();
				panel.getPromotionlist().addAll(selectlist);
			}
		});
		this.add(this.addBtn);
	}
	
	
	
}
