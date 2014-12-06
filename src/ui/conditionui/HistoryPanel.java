package ui.conditionui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyDatePicker;
import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class HistoryPanel extends JPanel{

	private MyComboBox client;
	
	private MyComboBox type;
	
	private MyComboBox operator;
	
	private MyComboBox store;
	
	private MyDatePicker start;
	
	private MyDatePicker end;
	
	private MyButton find;
	
	private JFrame frame;
	
	private PanelConfig cfg;
	
	public HistoryPanel(JFrame frame){
    	this.frame = frame;
    	this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基础属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
		this.repaint();
		
	}

	private void initComponent() {
		// 初始化日期选择器
		this.start = new MyDatePicker(cfg.getDatepicker().element("start"));
		this.end = new MyDatePicker(cfg.getDatepicker().element("end"));
		this.add(start);
		this.add(end);
		// 初始化复选框
		this.client = new MyComboBox(cfg.getComboboxes().element("client"));
		this.operator = new MyComboBox(cfg.getComboboxes().element("operator"));
		this.type = new MyComboBox(cfg.getComboboxes().element("type"));
		this.store = new MyComboBox(cfg.getComboboxes().element("store"));
		this.add(client);
		this.add(operator);
		this.add(type);
		this.add(store);
		// 添加标签
		this.add(new MyLabel(cfg.getLabels().element("require")));
		this.add(new MyLabel(cfg.getLabels().element("time")));
		this.add(new MyLabel(cfg.getLabels().element("start")));
		this.add(new MyLabel(cfg.getLabels().element("end")));
		this.add(new MyLabel(cfg.getLabels().element("type")));
		this.add(new MyLabel(cfg.getLabels().element("client")));
		this.add(new MyLabel(cfg.getLabels().element("operator")));
		this.add(new MyLabel(cfg.getLabels().element("store")));
		// 初始化按钮
		this.find = new MyButton(cfg.getButtons().element("find"));
		this.add(find);
	}
	
}
