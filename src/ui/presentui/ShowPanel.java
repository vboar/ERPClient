package ui.presentui;

import javax.swing.JPanel;

import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class ShowPanel extends JPanel {

	private PresentListTablePane tablepane;
	
	private PanelConfig pcfg;

	public ShowPanel(){
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.setVisible(false);
	}

	private void initComponent() {
		this.tablepane = new PresentListTablePane(new TableConfig(pcfg.getTablepane()));
		this.add(this.tablepane);
	}
	
	public void udpateData(){
		this.tablepane.updateData();
	}
	
	
}
