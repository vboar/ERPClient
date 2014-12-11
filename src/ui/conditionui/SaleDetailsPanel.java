package ui.conditionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.DatePickerGroup;
import ui.util.FrameUtil;
import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import vo.CommodityVO;
import vo.RequirementVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.commodityblservice.CommodityBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class SaleDetailsPanel extends JPanel implements FuzzySearch{
	
	private MyComboBox customer;
	
	private MySpecialTextField commodity;
	
	private MyComboBox salesman;
	
	private MyComboBox storage;
	
	private DatePickerGroup start;
	
	private DatePickerGroup end;
	
	private MyButton find;
	
	private SaleDetailsTablePane table;
	
	private JFrame frame;
	
	private PanelConfig cfg;
	
	private CommodityBLService commodityCtrl;
	private HashMap<String, CommodityVO> vomap;
	
	public SaleDetailsPanel(JFrame frame){
    	this.frame = frame;
    	this.commodityCtrl = ControllerFactoryImpl.getInstance().getCommodityController();
    	this.vomap = new HashMap<String,CommodityVO>();
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
		this.start = new DatePickerGroup(cfg.getDatepicker().element("start"));
		this.end = new DatePickerGroup(cfg.getDatepicker().element("end"));
		this.add(start);
		this.add(end);
		// 初始化复选框
		this.customer = new MyComboBox(cfg.getComboboxes().element("client"));
		this.salesman = new MyComboBox(cfg.getComboboxes().element("operator"));
		this.storage = new MyComboBox(cfg.getComboboxes().element("store"));
		this.add(customer);
		this.add(salesman);
		this.add(storage);
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
		this.find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findSales();
			}
		});
		this.add(find);
	}

	protected void findSales() {
		// 根据时间区间、商品名、客户名、业务员和仓库查询
		RequirementVO vo = new RequirementVO();
		String time1 = FrameUtil.getFormattedDate(this.start.getDate());
		String time2 =  FrameUtil.getFormattedDate(this.end.getDate());
		if((time1!=null)&&(time2!=null)&&(time1.compareTo(time2)>0)){
			MyOptionPane.showMessageDialog(frame, "请输入有效日期！","错误提示",
					MyOptionPane.ERROR_MESSAGE);
			return;
		}
		vo.time1 = time1;
		vo.time2 = time2;
		if(salesman.getSelectedItem()!=null)
			vo.salesman = salesman.getSelectedItem().toString();
		if(storage.getSelectedItem()!=null)
			vo.storage = storage.getSelectedItem().toString();	
		if(customer.getSelectedItem()!=null)
			vo.customer = this.customer.getSelectedItem().toString();
		vo.commodityName = this.commodity.getText();
		if(!table.showFindTable(vo)){
			MyOptionPane.showMessageDialog(frame, "未找到符合条件的销售记录！");
		}
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CommodityVO> list = this.commodityCtrl.fuzzyFind(keyword);
		if(list!=null){
			ArrayList<String> result = new ArrayList<String>();
			for(int i=0; i<list.size(); ++i){
				String str = list.get(i).name+"-"+list.get(i).model;
				result.add(list.get(i).name+"-"+list.get(i).model);
				vomap.put(str, list.get(i));
			}
			return result;
		}
		return null;
	}

}
