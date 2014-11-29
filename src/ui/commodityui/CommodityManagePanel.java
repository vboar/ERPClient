package ui.commodityui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ui.util.MyButton;
import config.ERPConfig;
import config.PanelConfig;

public class CommodityManagePanel extends JPanel{

	private MyButton categoryManageBtn;

	private MyButton commodityManageBtn;
	
	private MyButton presentBtn;
	
	private MyButton stockBtn;
	
	private MyButton stockCheckBtn;
	
	private MyButton exceptionBtn;
	
	private PanelConfig pcfg;
	
	private CategoryPanel mainPanel;
	
	public CommodityManagePanel(CategoryPanel panel){
		this.mainPanel = panel;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.repaint();
	}

	private void initComponent() {
		this.categoryManageBtn = new MyButton(pcfg.getButtons().element("category"));
		this.commodityManageBtn = new MyButton(pcfg.getButtons().element("commodity"));
		this.presentBtn = new MyButton(pcfg.getButtons().element("present"));
		this.stockBtn = new MyButton(pcfg.getButtons().element("stock"));
		this.stockCheckBtn = new MyButton(pcfg.getButtons().element("stockcheck"));
		this.exceptionBtn = new MyButton(pcfg.getButtons().element("exception"));
		this.add(this.categoryManageBtn);
		this.add(this.commodityManageBtn);
		this.add(this.presentBtn);
		this.add(this.stockBtn);
		this.add(this.stockCheckBtn);
		this.add(this.exceptionBtn);
	}

	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == categoryManageBtn){
			
			}
		}
		
	}
	
}
