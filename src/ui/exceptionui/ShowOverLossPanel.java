package ui.exceptionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.DatePickerGroup;
import ui.util.FrameUtil;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

/**
 * 查看报溢报损单列表
 * @author JanelDQ
 * @date 2014/12/5
 */
@SuppressWarnings("serial")
public class ShowOverLossPanel extends JPanel{

	private DatePickerGroup start;

	private DatePickerGroup end;

	private MyButton find;

	private MyButton showAll;
	
	private ExceptionListTablePane tablepane;
	
	private PanelConfig pcfg;
	
	private boolean isloss;
	
	/**
	 * 构造函数
	 * @param isloss true显示报损单，false显示报溢单
	 */
	public ShowOverLossPanel(boolean isloss) {
		this.isloss = isloss;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.setVisible(true);
	}
	
	/**
	 * 初始化组件
	 */
	private void initComponent() {
		// 初始化日期选择器
		this.start = new DatePickerGroup(pcfg.getDatepicker().element("start"));
		this.end = new DatePickerGroup(pcfg.getDatepicker().element("end"));
		this.add(this.start);
		this.add(this.end);
		
		// 初始化标签
		if(isloss)	this.add(new MyLabel("报损单列表",pcfg.getLabels().element("list")));
		else this.add(new MyLabel("报溢单列表",pcfg.getLabels().element("list")));
		this.add(new MyLabel(pcfg.getLabels().element("start")));
		this.add(new MyLabel(pcfg.getLabels().element("end")));
		
		// 初始化按钮
		this.initButtons(pcfg.getButtons());
		
		// 初始化列表面板
		this.tablepane = new ExceptionListTablePane(new TableConfig(
				pcfg.getTablepane()),this.isloss);
		this.add(this.tablepane);
	}

	/**
	 * 初始化按钮
	 * @param ele 配置对象
	 */
	private void initButtons(Element ele){
		// 查找按钮
		this.find = new MyButton(pcfg.getButtons().element("find"));
		this.find.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String time1 = FrameUtil.getFormattedDate(start.getDate());
				String time2 = FrameUtil.getFormattedDate(end.getDate());
				if ((time1 != null) && (time2 != null) && (time1.compareTo(time2) > 0)) {
					MyOptionPane.showMessageDialog(ShowOverLossPanel.this, "请输入有效日期！", "错误提示",
							MyOptionPane.ERROR_MESSAGE);
					return;
				}
				tablepane.showFindTable(time1,time2);
			}
		});
		this.add(this.find);
		// 显示全部按钮
		this.showAll = new MyButton(ele.element("showall"));
		this.showAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tablepane.showFindTable(null,null);
			}
		});
		this.add(this.showAll);
	}
}
