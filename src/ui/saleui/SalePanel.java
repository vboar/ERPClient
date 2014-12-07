package ui.saleui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.presentui.CreatePresentPanel;
import ui.presentui.ShowPanel;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class SalePanel extends JPanel {

	private MyButton createSale;
	
	private MyButton show;
	
	private Image bg;
	
	private JFrame frame;
	
	private PanelConfig pcfg;
	
	private SalePanel showpanel;
	
    public SalePanel(JFrame frame) {
		this.frame = frame;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.bg = pcfg.getBg();
		// 设置面板基本属性
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.repaint();
    }
    
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	/**
	 * 初始化组件
	 */
	private void initComponent() {
		this.add(new MyLabel(pcfg.getLabels().element("title")));
		this.showpanel = new ShowPanel();
		this.showpanel.setVisible(true);
		this.add(this.showpanel);
		this.createSale = new MyButton(pcfg.getButtons().element("createsale"));
		this.add(this.addPresent);
		this.addPresent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreate();
			}
		});
		this.show = new MyButton(pcfg.getButtons().element("show"));
		this.show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listpanel.udpateData();
				if(createPanel!=null){
					int result = MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑？","确认提示",
							MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						showList();
					}
				}
			}
		});
		this.add(this.show);
	}

	public void showCreate() {
		remove(listpanel);
		createPanel = new CreatePresentPanel(frame,this);
		add(createPanel);
		repaint();
	}

	public void showList() {
		remove(createPanel);
		listpanel = new ShowPanel();
		add(listpanel);
		repaint();
	}
	
	public ShowPanel getListpanel() {
		return listpanel;
	}

}
