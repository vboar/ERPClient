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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyLabel;
import businesslogicservice.loginblservice.LoginBLService;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class LoginUserInfoPanel extends JPanel {

	private MyLabel username;
	
	private MyLabel userId;
	
	private MyLabel usertype;
	
	private MyButton mailbox;
	
	private PanelConfig pcfg;
	
	private MyButton changePassword;
	
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
		this.mailbox.setToolTipText("收件箱");
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
		
		this.changePassword = new MyButton(button.element("password"));
		this.changePassword.addMouseListener(new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent e) {
				new UpdatePasswordDialog(frame);
			}
		});
		this.add(changePassword);
	}

	private void initLabels(Element label) {
		this.username = new MyLabel(lc.getUserName(), label.element("username"));
		this.usertype = new MyLabel(lc.getUserType().toFriendString(),label.element("usertype"));
		this.userId = new MyLabel(lc.getUserId(),label.element("id"));
		this.userId.setFont(new Font("微软雅黑",Font.PLAIN,12));
		this.usertype.setFont(new Font("微软雅黑",Font.PLAIN,12));
		this.username.setFont(new Font("微软雅黑",Font.PLAIN,12));
		this.userId.setForeground(new Color(40,52,84));
		this.usertype.setForeground(new Color(40,52,84));
		this.username.setForeground(new Color(40,52,84));
		this.add(this.username);
		this.add(this.usertype);
		this.add(this.userId);
	}
}
