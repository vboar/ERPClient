/**
 * 当前操作员信息面板
 * @author JaneLDQ
 * @date 2014/11/23
 */
package ui.homeui;

import businesslogicservice.loginblservice.LoginBLService;
import config.ERPConfig;
import config.PanelConfig;
import org.dom4j.Element;
import ui.util.MyButton;
import ui.util.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class LoginUserInfoPanel extends JPanel {

	private MyLabel username;
	
	private MyLabel userId;
	
	private MyLabel img;
	
	private MyButton mailbox;
	
	private PanelConfig pcfg;
	
	private LoginBLService lc;
	
	private Image bg;

	private HomeUI frame;
	
	public LoginUserInfoPanel(LoginBLService lc, HomeUI frame){
		this.frame = frame;
		this.lc = lc;
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.bg = pcfg.getBg();
		// 初始化组件
		this.initComponent(pcfg);
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
	}
	
	private void initComponent(PanelConfig cfg) {
		this.initLabels(cfg.getLabels());
		this.initButtons(cfg.getButtons());
	}

	private void initButtons(Element button) {
		this.mailbox = new MyButton(button.element("mailbox"));
		this.mailbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(this.mailbox);
		mailbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(lc.getUserType()) {
					case ADMINISTRATOR:
						frame.getAdminPanel().showMesssage();
						break;
					case SALESMAN:
						frame.getSalesmanPanel().showMesssage();
						break;
					case STOCKKEEPER:
						frame.getStockKeeperPanel().showMesssage();
						break;
					case COUNTER:
						frame.getCounterPanel().showMesssage();
						break;
					case MANAGER:
						frame.getManagerPanel().showMesssage();
					default:
				}
			}
		});
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
