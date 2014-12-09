package ui.saleui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class AutoCreatePanel extends JPanel{

	private SaleListPane tablepane;
	
	private MyButton create;
	
	private MyButton cancel;
	
	private PanelConfig cfg;
	
	private JFrame frame;
	
	public AutoCreatePanel(JFrame frame){
    	this.frame = frame;
    	this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基础属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
		this.repaint();
	}

	private void initComponent() {
		// 销售单列表
		this.tablepane = new SaleListPane(new TableConfig(this.cfg.getTablepane()),false);
		this.add(tablepane);
		this.create = new MyButton(cfg.getButtons().element("create"));
		this.add(this.create);
		this.cancel = new MyButton(cfg.getButtons().element("cancel"));
		this.add(cancel);
	}
	
}
