/**
 * 当前操作员信息面板
 * @author JaneLDQ
 * @date 2014/11/23
 */
package ui.homeui;

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
	
	public LoginUserInfoPanel(LoginController lc){
		this.lc = lc;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setLayout(null);
		this.setSize(pcfg.getWidth(), pcfg.getHeight());
		this.setLocation(pcfg.getX(), pcfg.getY());
		// 初始化组件
		this.initComponent(pcfg);
		this.repaint();
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
		this.username = new MyLabel(lc.getUserName(), label.element("username"));
		this.userId = new MyLabel(lc.getUserId(),label.element("id"));
		this.img = new MyLabel(label.element("img"));
		this.add(this.username);
		this.add(this.userId);
		this.add(this.img);
	}
}
