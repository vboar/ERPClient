package ui.saleui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class ShowPanel extends JPanel{

	private MyButton show;
	
	private PanelConfig cfg;
	
	private SaleListPane table;
	
	private JFrame frame;
	
	public ShowPanel(JFrame frame){
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.frame = frame;
        this.setSize(cfg.getW(), cfg.getH());
        this.setLocation(cfg.getX(), cfg.getY());
        this.setLayout(null);
        this.initComponent();
        this.repaint();
        this.setVisible(true);
	}

	private void initComponent() {
		this.show = new MyButton(cfg.getButtons().element("show"));
		this.add(this.show);
		this.table = new SaleListPane(new TableConfig(cfg.getTablepane()));
		this.add(this.table);
	}
	
	
}
