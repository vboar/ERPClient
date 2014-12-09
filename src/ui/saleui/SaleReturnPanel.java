package ui.saleui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class SaleReturnPanel extends JPanel{
	
//	private MyDatePicker start;
//
//	private MyDatePicker end;
	
	private MyButton autocreate;
	
	private MyButton manualcreate;

	private MyButton find;

	private MyButton show;
	
	private JFrame frame;
	
	private PanelConfig cfg;
	
	private SaleListPane tablepane;
	
	private AutoCreatePanel autopane;
	
    public SaleReturnPanel(JFrame frame) {
		this.frame = frame;
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基本属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.repaint();
		this.setVisible(true);
    }
	
	private void initComponent() {
		// 销售退货单列表
		this.tablepane = new SaleListPane(new TableConfig(this.cfg.getTablepane()),true);
		this.add(tablepane);
		this.add(new MyLabel(cfg.getLabels().element("list")));
		// 自动创建按钮
		this.autocreate = new MyButton(cfg.getButtons().element("autocreate"));
		this.autocreate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreate();
			}
		});
		this.add(this.autocreate);
		// 手动创建按钮
		this.manualcreate = new MyButton(cfg.getButtons().element("manualcreate"));
		this.manualcreate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreate();
			}
		});
		this.add(this.manualcreate);
		// 查看列表按钮
		this.show = new MyButton(cfg.getButtons().element("show"));
		this.show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaleReturnPanel.this.removeAll();
				SaleReturnPanel.this.add(tablepane);
				SaleReturnPanel.this.add(show);
				SaleReturnPanel.this.add(autocreate);
				SaleReturnPanel.this.add(manualcreate);
				SaleReturnPanel.this.repaint();
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
		this.remove(tablepane);
		autopane = new AutoCreatePanel(frame);
		this.add(autopane);
		repaint();
	}

	public void udpateData() {
		this.tablepane.updateData();
	}
}
