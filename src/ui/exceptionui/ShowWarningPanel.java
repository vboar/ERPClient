package ui.exceptionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyDatePicker;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

/**
 * 查看报警单列表面板
 * @author JanelDQ
 * @date 2014/12/5
 */
@SuppressWarnings("serial")
public class ShowWarningPanel extends JPanel{
	
	private MyDatePicker start;

	private MyDatePicker end;

	private MyButton find;

	private MyButton showAll;
	
	private WarningListTablePane tablepane;
	
	private PanelConfig pcfg;

	/**
	 * 构造函数
	 */
	public ShowWarningPanel() {
		// 获得配置对象
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		// 设置布局大小坐标
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
	}
	
	/**
	 * 初始化组件
	 */
	private void initComponent() {
		// 初始化日期选择器
		this.start = new MyDatePicker(pcfg.getDatepicker().element("start"));
		this.end = new MyDatePicker(pcfg.getDatepicker().element("end"));
		this.add(this.start);
		this.add(this.end);
		
		// 初始化标签
		this.add(new MyLabel(pcfg.getLabels().element("list")));
		this.add(new MyLabel(pcfg.getLabels().element("start")));
		this.add(new MyLabel(pcfg.getLabels().element("end")));
		
		// 初始化按钮
		this.initButtons(pcfg.getButtons());
		// 初始化表格
		this.tablepane = new WarningListTablePane(new TableConfig(pcfg.getTablepane()));
		this.add(this.tablepane);
	}
	
	private void initButtons(Element ele){
		this.find = new MyButton(pcfg.getButtons().element("find"));
		this.find.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Date day1 = start.getDate();
				Date day2 = end.getDate();
				String time1 = null;
				String time2 = null;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				if((day1!=null)&&(day2!=null)){
					time1 = dateFormat.format(day1);
					time2 = dateFormat.format(day2);
					if(time1.compareTo(time2)>0){
						MyOptionPane.showMessageDialog(null, "请输入有效日期！","错误提示",
								MyOptionPane.ERROR_MESSAGE);
					}
				}else if((day1==null)&&(day2!=null)){
					time2 = dateFormat.format(day2);
				}else if(day1!=null){
					time1 = dateFormat.format(day1);
				}
				tablepane.showFindTable(time1,time2);
			}
		});
		this.add(this.find);
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
