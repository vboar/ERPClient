package ui.saleui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class SalePanel extends JPanel {

	private MyButton createSale;
	
	private MyButton show;
	
	private JFrame frame;
	
	private PanelConfig cfg;
	
	private ShowPanel showpanel;
	
	private CreatePanel createpanel;
	
    public SalePanel(JFrame frame) {
		this.frame = frame;
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基本属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.repaint();
    }
	
	private void initComponent() {
		this.createSale = new MyButton(cfg.getButtons().element("createsale"));
		this.createSale.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreate();
			}
		});
		this.add(this.createSale);
		this.show = new MyButton(cfg.getButtons().element("showAll"));
		this.show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showShow();
			}
		});
		this.add(this.show);
	}
	
	public void showCreate() {
//		if(showpanel!=null) {
//			showpanel.setVisible(false);
//			remove(showpanel);
//		}
//		createpanel = new CreatePanel(frame);
//		add(createpanel);
//		repaint();
		new CreateSaleDialog(frame,this);
	}

	public void showShow() {
		if(createpanel!=null){
			createpanel.setVisible(false);
			remove(createpanel);
		}
		showpanel = new ShowPanel(frame);
		add(showpanel);
		repaint();
	}
}
