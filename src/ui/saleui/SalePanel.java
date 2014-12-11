package ui.saleui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.DatePickerGroup;
import ui.util.FrameUtil;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class SalePanel extends JPanel {
	
	private DatePickerGroup start;

	private DatePickerGroup end;
	
	private MyButton createSale;

	private MyButton find;

	private MyButton show;
	
	private JFrame frame;
	
	private PanelConfig cfg;
	
	private SaleListPane tablepane;
	
    public SalePanel(JFrame frame) {
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
		this.start = new DatePickerGroup(cfg.getDatepicker().element("start"));
		this.end = new DatePickerGroup(cfg.getDatepicker().element("end"));
		this.add(start);
		this.add(end);
		// 销售单列表
		this.tablepane = new SaleListPane(new TableConfig(this.cfg.getTablepane()),false);
		this.add(tablepane);
		// 标签
		this.add(new MyLabel(cfg.getLabels().element("start")));
		this.add(new MyLabel(cfg.getLabels().element("end")));
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
		this.find.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String time1 = FrameUtil.getFormattedDate(start.getDate());
				String time2 = FrameUtil.getFormattedDate(end.getDate());
				if((time1!=null)&&(time2!=null)&&(time1.compareTo(time2)>0)){
					MyOptionPane.showMessageDialog(frame, "请输入有效日期！","错误提示",
							MyOptionPane.ERROR_MESSAGE);
				}else{
					tablepane.showFindTable(time1,time2);
				}
			}
		});
		this.add(find);
	}
	
	public void showCreate() {
		new CreateSaleDialog(frame,this);
	}

	public void udpateData() {
		this.tablepane.updateData();
	}
}
