package ui.conditionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.DatePickerGroup;
import ui.util.ExcelSaver;
import ui.util.FrameUtil;
import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import ui.util.SavePathDialog;
import util.ResultMessage;
import vo.CommodityVO;
import vo.CustomerVO;
import vo.RequirementVO;
import vo.UserVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.businessconditionblservice.SaleDetailsBLService;
import businesslogicservice.commodityblservice.CommodityBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class SaleDetailsPanel extends JPanel implements FuzzySearch, ExcelSaver {

	private MyComboBox customer;

	private MySpecialTextField commodity;

	private MyComboBox salesman;

	private MyComboBox storage;

	private DatePickerGroup start;

	private DatePickerGroup end;

	private MyButton find;
	
	private MyButton export;

	private SaleDetailsTablePane table;

	private JFrame frame;

	private PanelConfig cfg;

	private CommodityBLService commodityCtrl;
	private SaleDetailsBLService controller;
	private HashMap<String, CommodityVO> vomap;

	public SaleDetailsPanel(JFrame frame) {
		this.frame = frame;
		this.controller = ControllerFactoryImpl.getInstance()
				.getSaleDetailsController();
		this.commodityCtrl = ControllerFactoryImpl.getInstance()
				.getCommodityController();
		this.vomap = new HashMap<String, CommodityVO>();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		// 设置面板基础属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
		this.repaint();

	}

	@SuppressWarnings("unchecked")
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
		this.storage = new MyComboBox(cfg.getComboboxes().element("store"));
		// 添加客户信息
		this.customer = new MyComboBox(cfg.getComboboxes().element("client"));
		this.customer.addItem("所有");
		ArrayList<CustomerVO> cutomerlist = ControllerFactoryImpl.getInstance().getCustomerController()
				.fuzzyFind("");
		for(int j=0; j<cutomerlist.size();++j){
			this.customer.addItem(cutomerlist.get(j).id+cutomerlist.get(j).name);
		}	
		// 添加业务员信息
		this.salesman = new MyComboBox(cfg.getComboboxes().element("operator"));
		this.salesman.addItem("所有");
		ArrayList<UserVO> userlist =ControllerFactoryImpl.getInstance().getUserController()
				.fuzzyFindOperator("");
		for(int i=0; i<userlist.size();++i){
			this.salesman.addItem(userlist.get(i).id);
		}
		this.add(customer);
		this.add(salesman);
		this.add(storage);
		
		// 初始化输入框
		this.commodity = new MySpecialTextField(cfg.getTextFields().element(
				"commodity"), this);
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
		this.export = new MyButton(cfg.getButtons().element("export"));
		this.export.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				new SavePathDialog(frame, SaleDetailsPanel.this);
			}
		});
		this.add(export);
		this.add(find);
	}

	protected void findSales() {
		if (!table.showFindTable(getRequirementVO())) {
			MyOptionPane.showMessageDialog(frame, "未找到符合条件的销售记录！");
		}
	}

	private RequirementVO getRequirementVO() {
		// 根据时间区间、商品名、客户名、业务员和仓库查询
		RequirementVO vo = new RequirementVO();
		String time1 = FrameUtil.getFormattedDate(this.start.getDate());
		String time2 = FrameUtil.getFormattedDate(this.end.getDate());
		if ((time1 != null) && (time2 != null) && (time1.compareTo(time2) > 0)) {
			MyOptionPane.showMessageDialog(frame, "请输入有效日期！", "错误提示",
					MyOptionPane.ERROR_MESSAGE);
		} else {
			vo.time1 = time1;
			vo.time2 = time2;
		}
		if(salesman.getSelectedItem().toString().equals("所有")) vo.salesman = null;
		else vo.salesman = salesman.getSelectedItem().toString();
		if(customer.getSelectedItem().toString().equals("所有")) vo.customer = null;
		else vo.customer = this.customer.getSelectedItem().toString().substring(0,5);
		vo.storage = storage.getSelectedItem().toString();
		if(this.vomap.get(this.commodity.getText())!=null)
			vo.commodityName = this.vomap.get(this.commodity.getText()).name;
		else vo.commodityName = this.commodity.getText();
		return vo;
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<CommodityVO> list = this.commodityCtrl.fuzzyFind(keyword);
		if (list != null) {
			ArrayList<String> result = new ArrayList<String>();
			for (int i = 0; i < list.size(); ++i) {
				String str = list.get(i).name + "-" + list.get(i).model;
				result.add(list.get(i).name + "-" + list.get(i).model);
				vomap.put(str, list.get(i));
			}
			return result;
		}
		return null;
	}

	@Override
	public ResultMessage setSavePath(String path) {
		return controller.exportExcel(path, getRequirementVO());
	}

	@Override
	public String getDefaultPath() {
		return controller.getDefaultPath();
	}
}
