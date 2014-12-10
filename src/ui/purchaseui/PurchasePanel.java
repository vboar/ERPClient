package ui.purchaseui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class PurchasePanel extends JPanel{

//	private MyDatePicker start;
//
//	private MyDatePicker end;
	
	private MyButton createSale;

	private MyButton find;

	private MyButton show;
	
	private PurchaseListPane tablepane;
	
	private PanelConfig cfg;
	
	private JFrame frame;
	
    public PurchasePanel(JFrame frame) {
		this.frame = frame;
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基本属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.repaint();
    }

	private void initComponent() {
		this.add(new MyLabel(cfg.getLabels().element("list")));
		// 创建销售单按钮
		this.createSale = new MyButton(cfg.getButtons().element("createsale"));
		this.createSale.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreate();
			}
		});
		this.add(this.createSale);
		// 查看列表按钮
		this.show = new MyButton(cfg.getButtons().element("show"));
		this.show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tablepane.showFindTable(null, null);
			}
		});
		this.add(this.show);
		// 查找按钮
		this.find = new MyButton(cfg.getButtons().element("find"));
//		this.find.addActionListener(new ActionListener() {			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Date day1 = start.getDate();
//				Date day2 = end.getDate();
//				String time1 = null;
//				String time2 = null;
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//				if((day1!=null)&&(day2!=null)){
//					time1 = dateFormat.format(day1);
//					time2 = dateFormat.format(day2);
//					if(time1.compareTo(time2)>0){
//						MyOptionPane.showMessageDialog(SalePanel.this, "请输入有效日期！","错误提示",
//								MyOptionPane.ERROR_MESSAGE);
//					}
//				}else if((day1==null)&&(day2!=null)){
//					time2 = dateFormat.format(day2);
//				}else if(day1!=null){
//					time1 = dateFormat.format(day1);
//				}
//				tablepane.showFindTable(time1,time2);
//			}
//		});
		this.add(find);
	}
	
	public void showCreate() {
		new CreatePurchaseDialog(frame,this);
	}

	public void udpateData() {
		this.tablepane.updateData();
	}
	
}
