package ui.presentui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class PresentPanel extends JPanel {

	private MyButton addPresent;
	
	private MyButton show;
	
	private CreatePresentPanel createPanel;
	
	private ShowPanel listpanel;
	
	private Image bg;
	
	private JFrame frame;
	
	private PanelConfig pcfg;
	
	public PresentPanel(JFrame frame){
		this.frame = frame;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.bg = pcfg.getBg();
		this.initComponent();
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
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
				listpanel.setVisible(false);
				createPanel = new CreatePresentPanel(frame, PresentPanel.this);
				add(createPanel);
				repaint();
			}
		});
		this.show = new MyButton(pcfg.getButtons().element("show"));
		this.show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listpanel.udpateData();
				if(createPanel!=null){
					int result = MyOptionPane.showConfirmDialog(null, "是否放弃当前编辑？","确认提示",
							MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						remove(createPanel);
						listpanel.setVisible(true);
					}
				}
			}
		});
		this.add(this.show);
	}

	public ShowPanel getListpanel() {
		return listpanel;
	}
	
	
}
