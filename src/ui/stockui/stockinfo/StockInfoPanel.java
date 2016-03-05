package ui.stockui.stockinfo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.DatePickerGroup;
import ui.util.FrameUtil;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.stockblservice.StockBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

/**
 * 库存查看面板
 * @author JanelDQ
 * @date 2014/12/2
 */
@SuppressWarnings("serial")
public class StockInfoPanel extends JPanel {

	private DatePickerGroup start;
	
	private DatePickerGroup end;
	
	private MyButton find;
	
	private MyButton showAll;
	
	private StockTablePane stockInfoTable;
	
	private StockBLService controller;
	
	private PanelConfig cfg;
	
	private JFrame frame;
	
	private Image bg;

	public StockInfoPanel(JFrame frame){
		this.frame = frame;
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
		this.start = new DatePickerGroup(ele.element("start"));
		this.end = new DatePickerGroup(ele.element("end"));
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
				String time1 = FrameUtil.getFormattedDate(start.getDate());
				String time2 = FrameUtil.getFormattedDate(end.getDate());
				if((time1!=null)&&(time2!=null)&&(time1.compareTo(time2)>0)){
					MyOptionPane.showMessageDialog(frame, "请输入有效时间区间！");
					return;
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
