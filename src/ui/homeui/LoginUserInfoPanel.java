/**
 * 当前操作员信息面板
 * @author JaneLDQ
 * @date 2014/11/23
 */
package ui.homeui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyLabel;
import businesslogic.loginbl.LoginController;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class LoginUserInfoPanel extends JPanel {

	private MyLabel username;
	
	private MyLabel userId;
	
	private MyLabel img;
	
	private MyButton mailbox;
	
	private PanelConfig pcfg;
	
	private LoginController lc;
	
	private Image bg;
	
	public LoginUserInfoPanel(LoginController lc){
		this.lc = lc;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setLayout(null);
		this.setSize(pcfg.getWidth(), pcfg.getHeight());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.bg = pcfg.getBg();
		// 初始化组件
		this.initComponent(pcfg);
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getWidth(), pcfg.getHeight(), null);
	}
	
	private void initComponent(PanelConfig cfg) {
		this.initLabels(cfg.getLabels());
		this.initButtons(cfg.getButtons());
	}

	private void initButtons(Element button) {
		this.mailbox = new MyButton(button.element("mailbox"));
		this.add(this.mailbox);
	}

	private void initLabels(Element label) {
		this.username = new MyLabel("操作员："+lc.getUserName(), label.element("username"));
		this.userId = new MyLabel("ID："+lc.getUserId(),label.element("id"));
		this.img = new MyLabel(label.element("img"));
		this.username.setForeground(Color.WHITE);
		this.userId.setForeground(Color.WHITE);
		this.userId.setFont(new Font("黑体",Font.PLAIN,16));
		this.add(this.username);
		this.add(this.userId);
		this.add(this.img);
	}
}
