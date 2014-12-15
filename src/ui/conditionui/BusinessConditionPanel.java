package ui.conditionui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.util.DatePickerGroup;
import ui.util.ExcelSaver;
import ui.util.FrameUtil;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.SavePathDialog;
import util.ResultMessage;
import vo.BusinessConditionVO;
import vo.RequirementVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.businessconditionblservice.BusinessConditionBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class BusinessConditionPanel extends JPanel implements ExcelSaver{

	private CostTable cost;

	private IncomeTable income;

	private MyButton show;

	private MyButton export;

	private MyLabel total;

	private DatePickerGroup start;

	private DatePickerGroup end;

	private PanelConfig cfg;

	private Image bg;

	private JFrame frame;

	private BusinessConditionBLService controller;

	public BusinessConditionPanel(JFrame frame) {
		this.frame = frame;
		this.controller = ControllerFactoryImpl.getInstance()
				.getBusinessConditionBLService();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap()
				.get((this.getClass().getName()));
		// 设置面板基础属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		this.bg = cfg.getBg();
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
		this.repaint();

	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, cfg.getW(), cfg.getH(), null);
	}

	private void initComponent() {
		// 初始化日期选择器
		this.start = new DatePickerGroup(cfg.getDatepicker().element("start"));
		this.end = new DatePickerGroup(cfg.getDatepicker().element("end"));
		this.add(start);
		this.add(end);
		this.cost = new CostTable(new TableConfig(cfg.getTables().element(
				"cost")));
		this.income = new IncomeTable(new TableConfig(cfg.getTables().element(
				"income")));
		this.add(cost);
		this.add(income);
		// 初始化按钮
		this.show = new MyButton(cfg.getButtons().element("show"));
		this.show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showData();
			}
		});
		this.export = new MyButton(cfg.getButtons().element("export"));
		this.export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SavePathDialog(frame, BusinessConditionPanel.this);
			}
		});
		this.add(export);
		this.add(show);
		// 总价标签
		this.total = new MyLabel(cfg.getLabels().element("total"));
		this.total.setHorizontalAlignment(JLabel.RIGHT);
		this.add(total);
	}

	public void showData() {
		String time1 = FrameUtil.getFormattedDate(this.start.getDate());
		String time2 = FrameUtil.getFormattedDate(this.end.getDate());
		if ((time1 != null) && (time2 != null) && (time1.compareTo(time2) > 0)) {
			MyOptionPane.showMessageDialog(frame, "请输入有效日期！", "错误提示",
					MyOptionPane.ERROR_MESSAGE);
			return;
		}
		BusinessConditionVO vo = controller.show(time1, time2);
		// 表格
		if (vo != null) {
			this.cost.updateData(vo);
			this.income.updateData(vo);
			this.total.setText(Double.toString(vo.profit));
		} else {
			MyOptionPane.showMessageDialog(frame, "抱歉，未找到相关信息！");
		}
	}

	@Override
	public ResultMessage setSavePath(String path) {
		RequirementVO vo = new RequirementVO();
		vo.time1 = FrameUtil.getFormattedDate(this.start.getDate());
		vo.time2 = FrameUtil.getFormattedDate(this.end.getDate());
		return controller.exportExcel(path,vo);
	}

	@Override
	public String getDefaultPath() {
		return controller.getDefaultPath();
	}

}
