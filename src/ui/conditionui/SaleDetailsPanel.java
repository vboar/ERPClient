package ui.conditionui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyDatePicker;
import ui.util.MyLabel;
import ui.util.MySpecialTextField;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class SaleDetailsPanel extends JPanel implements FuzzySearch{
	
	private MyComboBox client;
	
	private MySpecialTextField commodity;
	
	private MyComboBox operator;
	
	private MyComboBox store;
	
	private MyDatePicker start;
	
	private MyDatePicker end;
	
	private MyButton find;
	
	private SaleDetailsTablePane table;
	
	private JFrame frame;
	
	private PanelConfig cfg;
	
	public SaleDetailsPanel(JFrame frame){
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
		// 表格面板
		this.table = new SaleDetailsTablePane(new TableConfig(cfg.getTablepane()));
		this.add(this.table);
		// 初始化日期选择器
		this.start = new MyDatePicker(cfg.getDatepicker().element("start"));
		this.end = new MyDatePicker(cfg.getDatepicker().element("end"));
		this.add(start);
		this.add(end);
		// 初始化复选框
		this.client = new MyComboBox(cfg.getComboboxes().element("client"));
		this.operator = new MyComboBox(cfg.getComboboxes().element("operator"));
		this.store = new MyComboBox(cfg.getComboboxes().element("store"));
		this.add(client);
		this.add(operator);
		this.add(store);
		// 初始化输入框
		this.commodity = new MySpecialTextField(cfg.getTextFields().element("commodity"), this);
		this.add(this.commodity);
		// 添加标签
		this.add(new MyLabel(cfg.getLabels().element("require")));
		this.add(new MyLabel(cfg.getLabels().element("time")));
		this.add(new MyLabel(cfg.getLabels().element("start")));
		this.add(new MyLabel(cfg.getLabels().element("end")));
		this.add(new MyLabel(cfg.getLabels().element("commodity")));
		this.add(new MyLabel(cfg.getLabels().element("client")));
		this.add(new MyLabel(cfg.getLabels().element("operator")));
		this.add(new MyLabel(cfg.getLabels().element("store")));
		// 初始化按钮
		this.find = new MyButton(cfg.getButtons().element("find"));
		this.add(find);
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
