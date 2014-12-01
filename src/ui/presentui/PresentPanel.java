package ui.presentui;

<<<<<<< HEAD
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
=======
>>>>>>> f6ec2aaba589eddf7abc806fc3e4194651baeaa6
import config.ERPConfig;
import config.PanelConfig;
import ui.util.MyButton;
import ui.util.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PresentPanel extends JPanel {

	private MyButton addPresent;
	
	private CreatePresentPanel createPanel;
	
	private Image bg;
	
	private JFrame frame;
	
	private PanelConfig pcfg;
	
	public PresentPanel(JFrame frame){
<<<<<<< HEAD
		this.frame = frame;
=======
>>>>>>> f6ec2aaba589eddf7abc806fc3e4194651baeaa6
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
		this.addPresent = new MyButton(pcfg.getButtons().element("createpresent"));
		this.add(new MyLabel(pcfg.getLabels().element("title")));
		this.createPanel = new CreatePresentPanel(this.frame);
		this.add(this.addPresent);
		this.addPresent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(createPanel);
				repaint();
			}
		});
	}
}
