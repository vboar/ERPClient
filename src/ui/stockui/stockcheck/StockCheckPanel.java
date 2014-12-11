package ui.stockui.stockcheck;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.DatePickerGroup;
import ui.util.MyButton;
import ui.util.MyJFileChooser;
import ui.util.MyLabel;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.stockblservice.StockBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

/**
 * 库存盘点面板
 * @author JanelDQ
 * @date 2014/12/2
 */
@SuppressWarnings("serial")
public class StockCheckPanel extends JPanel {

	private MyButton createExcel;
	
	private MyButton createCheck;
	
	private MyButton show;
	
	private DatePickerGroup date;
	
	private StockCheckTablePane tablepane;
	
	private MyJFileChooser filesaver;
	
	private PanelConfig cfg;
	
	private Image bg;
	
//	private JFrame frame;
	
	private StockBLService controller;
	
    public StockCheckPanel(JFrame frame) {
 //   	this.frame = frame;
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
		this.filesaver = new MyJFileChooser();
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
				controller.addCheck(tablepane.getList());
			}
		});
		this.add(this.createCheck);
		
		this.createExcel = new MyButton(this.cfg.getButtons().element("excel"));
		this.createExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				filesaver.showSaveDialog(null);
				String path = filesaver.getPath();
				System.out.println(path);
			}
		});
		this.add(this.createExcel);
		
		this.show = new MyButton(this.cfg.getButtons().element("showAll"));
		this.show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.add(this.show);
	}

	public void exportExcel() {
		// TODO Auto-generated method stub
		
	}

}
