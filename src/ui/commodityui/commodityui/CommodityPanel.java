package ui.commodityui.commodityui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyLabel;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.commodityblservice.CommodityBLService;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class CommodityPanel extends JPanel{
	
	private MyButton addbtn;
	
	private MyButton deletebtn;
	
	private MyButton updatebtn;
	
	private MyButton findbtn;
	
	private MyButton showAll;

	private MyLabel commoditylist;
	
	private MyLabel title;
	
	private CommodityTreePane treepane;
	
	private Image bg;

	private JFrame frame;
	
	private PanelConfig pcfg;
	
	private CommodityBLService controller;
		
	public CommodityPanel(JFrame frame){
		this.frame = frame;
		this.controller = ControllerFactoryImpl.getInstance().getCommodityController();
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.bg = pcfg.getBg();
		this.initComponent(pcfg);
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(),pcfg.getH(),null);
	}
	
	private void initComponent(PanelConfig cfg) {
		this.initButtons(cfg.getButtons());
		this.initLabels(cfg.getLabels());
		this.initTreePane();
	}

	private void initTreePane() {
		this.treepane = new CommodityTreePane(pcfg.getTree(),this.frame);
		this.add(this.treepane);
	}


	private void initLabels(Element labels) {
		this.title = new MyLabel(labels.element("title"));
		this.add(this.title);
		this.commoditylist = new MyLabel(labels.element("commoditylist"));
		this.add(this.commoditylist);
	}

	private void initButtons(Element buttons) {
		this.initAddBtn(buttons.element("add"));
		this.initDeleteBtn(buttons.element("delete"));
		this.initUpdateBtn(buttons.element("update"));
		this.initFindBtn(buttons.element("find"));
		this.initShowAllBtn(buttons.element("showall"));
	}

	private void initAddBtn(Element element) {
		this.addbtn = new MyButton(element);
		this.add(this.addbtn);
	}

	private void initDeleteBtn(Element element) {
		this.deletebtn = new MyButton(element);
		this.add(this.deletebtn);
	}
	
	private void initUpdateBtn(Element element){
		this.updatebtn = new MyButton(element);
		this.add(this.updatebtn);
	}

	private void initFindBtn(Element element) {
		this.findbtn = new MyButton(element);
		this.add(this.findbtn);
	}
	
	private void initShowAllBtn(Element element) {
		this.showAll = new MyButton(element);
		this.add(this.showAll);
	}

}
