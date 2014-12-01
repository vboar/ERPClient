package ui.stockui.stockinfo;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.stockblservice.StockBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;
import org.dom4j.Element;
import ui.util.MyButton;
import ui.util.MyDatePicker;
import ui.util.MyLabel;
import ui.util.MyOptionPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class StockInfoPanel extends JPanel {

	private MyDatePicker start;
	
	private MyDatePicker end;
	
	private MyButton find;
	
	private MyButton showAll;
	
	private StockTablePane stockInfoTable;
	
	private StockBLService controller;
	
	private PanelConfig cfg;
	
	private Image bg;

	public StockInfoPanel(JFrame frame){
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.controller = ControllerFactoryImpl.getInstance().getStockController();
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		this.bg = cfg.getBg();
		this.initComponent();
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(), cfg.getH(),null);
	}

	private void initComponent() {
		this.initTable(cfg.getTablepane());
		this.initDatePicker(cfg.getDatepicker());
		this.initLabels(cfg.getLabels());
		this.initButtons(cfg.getButtons());;
	}
	
	private void initTable(Element tablepane) {
		this.stockInfoTable = new StockTablePane(new TableConfig(tablepane), controller);
		this.add(this.stockInfoTable);
	}
		
	private void initDatePicker(Element ele){
		this.start = new MyDatePicker(ele.element("start"));
		this.end = new MyDatePicker(ele.element("end"));
		this.add(this.start);
		this.add(this.end);
	}
	
	private void initLabels(Element ele){
		this.add(new MyLabel(ele.element("title")));
		this.add(new MyLabel(ele.element("stocklist")));
		this.add(new MyLabel(ele.element("start")));
		this.add(new MyLabel(ele.element("end")));
	}
	
	private void initButtons(Element ele){
		this.find = new MyButton(cfg.getButtons().element("find"));
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
				stockInfoTable.showFindTable(controller.showStockInfo(time1, time2));
			}
		});
		this.add(this.find);
		this.showAll = new MyButton(ele.element("showall"));
		this.showAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				stockInfoTable.showFindTable(controller.showStockInfo(null, null));
			}
		});
		this.add(this.showAll);
	}
}
