package ui.stockui.stockcheck;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.stockblservice.StockBLService;
import ui.util.DatePickerGroup;
import ui.util.ExcelSaver;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.SavePathDialog;
import util.ResultMessage;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

/**
 * 库存盘点面板
 * @author JanelDQ
 * @date 2014/12/2
 */
@SuppressWarnings("serial")
public class StockCheckPanel extends JPanel implements ExcelSaver {

	private MyButton createExcel;
	
	private MyButton createCheck;
	
	private DatePickerGroup date;
	
	private StockCheckTablePane tablepane;
	
	private PanelConfig cfg;
	
	private Image bg;
	
	private JFrame frame;
	
	private StockBLService controller;
	
    public StockCheckPanel(JFrame frame) {
    	this.frame = frame;
    	this.controller = ControllerFactoryImpl.getInstance().getStockController();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
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
		this.tablepane = new StockCheckTablePane(new TableConfig(this.cfg.getTablepane()));
		this.add(this.tablepane);
		this.date = new DatePickerGroup(this.cfg.getDatepicker().element("date"));
		this.add(this.date);
		this.add(new MyLabel(this.cfg.getLabels().element("title")));	
		this.add(new MyLabel(this.cfg.getLabels().element("list")));
		this.add(new MyLabel(this.cfg.getLabels().element("date")));
		this.initButtons();
	}
	
	private void initButtons() {
		this.createCheck = new MyButton(this.cfg.getButtons().element("createcheck"));
		this.createCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tablepane.showCheck();
			}
		});
		this.add(this.createCheck);
		
		this.createExcel = new MyButton(this.cfg.getButtons().element("excel"));
		this.createExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SavePathDialog(frame, StockCheckPanel.this);
			}
		});
		this.add(this.createExcel);
	}

	public void exportExcel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultMessage setSavePath(String path) {
		return controller.exportExcel(path);
	}

	@Override
	public String getDefaultPath() {
		return controller.getDefaultPath();
	}

}
