package ui.stockui.stockcheck;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.DatePickerGroup;
import ui.util.ExcelSaver;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.SavePathDialog;
import util.ResultMessage;
import util.Time;
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
public class StockCheckPanel extends JPanel implements ExcelSaver {

	private MyButton createExcel;
	
	private MyButton createCheck;
	
	private MyButton find;
	
	private MyLabel today;
	
	private MyLabel past;
	
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
		this.past = new MyLabel(this.cfg.getLabels().element("past"));
		this.past.setVisible(false);
		this.today = new MyLabel(this.cfg.getLabels().element("list"));
		this.add(today);
		this.add(past);
		this.add(new MyLabel(this.cfg.getLabels().element("date")));
		this.initButtons();
	}
	
	private void initButtons() {
		this.find = new MyButton(cfg.getButtons().element("find"));
		this.find.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String time = date.getFormatedDate();
				if(time.equals("")){
					MyOptionPane.showMessageDialog(frame, "请选择一个日期！");
					return;
				}
				tablepane.showFindCheck(time);
				past.setText(time+"库存快照");
				past.setVisible(true);
				today.setVisible(false);
			}
		});
		this.add(find);
		this.createCheck = new MyButton(this.cfg.getButtons().element("createcheck"));
		this.createCheck.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				tablepane.showCheck();
				past.setVisible(false);
				today.setVisible(true);
				String currentTime = Time.getCurrentTime();
				date.setDate(currentTime.substring(0,10));		 	
			}
		});
		this.add(this.createCheck);
		
		this.createExcel = new MyButton(this.cfg.getButtons().element("excel"));
		this.createExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(date.getFormatedDate().equals("")){
					MyOptionPane.showMessageDialog(frame, "请先选择一日库存快照！");
					return;
				}
				if(MyOptionPane.showConfirmDialog(frame, "确认导出当前所示列表到Excel文件？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE)==
						MyOptionPane.YES_OPTION){
					new SavePathDialog(frame, StockCheckPanel.this);
				}
			}
		});
		this.add(this.createExcel);
	}
	
	@Override
	public ResultMessage setSavePath(String path) {
		return controller.exportExcel(path,date.getFormatedDate());
	}

	@Override
	public String getDefaultPath() {
		return controller.getDefaultPath(date.getText());
	}

}
