package ui.promotionui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class CustomerPanel extends JPanel{
	
	private MyButton add;
	
	private MyButton unable;
	
	private MyButton update;
	
	private PanelConfig cfg;
	
	private TotalGiftTablePane tablepane;
	
	private JFrame frame;
	
	public CustomerPanel(JFrame frame){
		this.frame = frame;
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.setVisible(false);
	}

	private void initComponent() {
		this.add = new MyButton(cfg.getButtons().element("add"));
		this.add.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomerGiftInfoDialog(frame,true);
			}
		});
		this.unable = new MyButton(cfg.getButtons().element("unable"));
		this.update = new MyButton(cfg.getButtons().element("update"));
		this.add(new MyLabel(cfg.getLabels().element("list")));
		this.tablepane = new TotalGiftTablePane(new TableConfig(cfg.getTablepane()));
		this.add(this.add);
		this.add(this.unable);
		this.add(this.update);
		this.add(this.tablepane);
	}
}
