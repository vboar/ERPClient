package ui.presentui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.presentui.ShowPanel;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import config.ERPConfig;
import config.PanelConfig;

/**
 * 赠送单面板
 * @author JanelDQ
 * @date 2014/11/27
 */
@SuppressWarnings("serial")
public class PresentPanel extends JPanel {

	private MyButton addPresent;
	
	private MyButton show;
	
	private CreatePresentPanel createPanel;
	
	private ShowPanel listpanel;
	
	private Image bg;
	
	private JFrame frame;
	
	private PanelConfig pcfg;
	
	private boolean islist = true;
	
	public PresentPanel(JFrame frame){
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
		this.listpanel = new ShowPanel();
		this.listpanel.setVisible(true);
		this.add(this.listpanel);
		this.addPresent = new MyButton(pcfg.getButtons().element("createpresent"));
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
				if(!islist){
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
		this.islist = false;
		createPanel = new CreatePresentPanel(frame,this);
		add(createPanel);
		repaint();
	}

	public void showList() {
		this.islist = true;
		remove(createPanel);
		listpanel = new ShowPanel();
		add(listpanel);
		repaint();
	}
	
	public ShowPanel getListpanel() {
		return listpanel;
	}
	
}
