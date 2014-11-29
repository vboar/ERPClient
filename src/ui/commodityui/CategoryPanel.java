package ui.commodityui;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import businesslogic.commoditybl.CategoryController;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class CategoryPanel extends JPanel{
	
	private JFrame homeframe;
	
	private PanelConfig pcfg;
	
	private CategoryTreePane treepane;
	
	private CategoryController controller;
	
	private Image bg;
		
	public CategoryPanel(JFrame frame){
		this.homeframe = frame;
		this.controller = ControllerFactoryImpl.getInstance().getCategoryController();
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.bg = pcfg.getBg();
		this.initComponent(pcfg);
		this.repaint();
		this.setVisible(true);
	}

	private void initComponent(PanelConfig pcfg) {
		this.treepane = new CategoryTreePane(pcfg.getTree(),homeframe);
		this.add(this.treepane);
	}

}
