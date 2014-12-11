/**
 * 登录面板类
 * @author JaneLDQ
 * @date 2014/11/23
 */
package ui.loginui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogic.utilitybl.Utility;
import businesslogicservice.loginblservice.LoginBLService;
import config.ERPConfig;
import config.PanelConfig;
import ui.homeui.HomeUI;
import ui.util.*;
import util.ResultMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel{
	
	private JComboBox<String> usertype;
	
	private MyButton loginBtn;
	
	private MyButton quitBtn;
	
	private MyButton setBtn;

	private MyLabel typeLab;
	
	private MyLabel idLab;
	
	private MyLabel passwordLab;
	
	private MyTextField idTxt;
	
	private JPasswordField passwordTxt;
	
	private Image bg;
	
	private LoginUI frame;
	
	private PanelConfig pcfg;
	
	private LoginBLService loginController;
	
	public LoginPanel(LoginUI frame){
		this.loginController = ControllerFactoryImpl.getInstance().getLoginController();
		//获得面板配置
		this.pcfg = ERPConfig.getLOGINFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.frame = frame;
		// 设置布局管理器为自由布局
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.bg = pcfg.getBg();
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
		this.initComboBox(pcfg);
		this.initText(pcfg);
		this.initLabel(pcfg);
		this.initButtons(pcfg);
	}
	
	private void initButtons(PanelConfig cfg){
		// 初始登录按钮
		this.initLoginBtn(cfg);
		// 初始退出按钮
		this.initQuitBtn(cfg);
		// 初始设置按钮
		this.initSetBtn(cfg);
		this.add(this.loginBtn);
		this.add(this.quitBtn);
		this.add(this.setBtn);
	}
	
	private void initLabel(PanelConfig cfg){
		//获得标签配置
		this.typeLab = new MyLabel(cfg.getLabels().element("type"));
		this.idLab = new MyLabel(cfg.getLabels().element("id"));
		this.passwordLab = new MyLabel(cfg.getLabels().element("password"));
		this.add(this.typeLab);
		this.add(this.idLab);
		this.add(this.passwordLab);
		
	}
	
	private void initText(PanelConfig cfg){
		//获得输入框配置
		this.idTxt = new MyTextField(cfg.getTextFields().element("id"));
		this.passwordTxt = new JPasswordField();
		this.passwordTxt.setSize(Integer.parseInt(cfg.getTextFields().element("password").attributeValue("w")),
				Integer.parseInt(cfg.getTextFields().element("password").attributeValue("h")));
		this.passwordTxt.setLocation(Integer.parseInt(cfg.getTextFields().element("password").attributeValue("x")),
				Integer.parseInt(cfg.getTextFields().element("password").attributeValue("y")));
		this.idTxt.setText("00003");
		this.passwordTxt.setText("123456");
		this.add(this.idTxt);
		this.add(this.passwordTxt);
		
	}
	
	@SuppressWarnings("unchecked")
	private void initComboBox(PanelConfig cfg){
		// 获得复选框配置
		this.usertype = new MyComboBox(cfg.getComboboxes().element("usertype"));
		this.usertype.setSelectedIndex(3);
		this.add(this.usertype);
	}
	
	private void initLoginBtn(PanelConfig cfg){
		// 初始化登录按钮
		this.loginBtn = new MyButton(cfg.getButtons().element("login"));
		this.loginBtn.addActionListener(new ActionListener(){
			// 事件处理
			@Override
			public void actionPerformed(ActionEvent e) {
				checkLogin();
			}
		});
	}
	
	private void initQuitBtn(PanelConfig cfg){
		// 初始化退出按钮
		this.quitBtn = new MyButton(cfg.getButtons().element("quit"));
		this.quitBtn.addActionListener(new ActionListener(){
			// 事件处理
			@Override
			public void actionPerformed(ActionEvent e){
				int result = MyOptionPane.showConfirmDialog(null, "确认退出？","系统提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
	}

	private void initSetBtn(PanelConfig cfg){
		this.setBtn = new MyButton(cfg.getButtons().element("setting"));
	}
	
	private void checkLogin(){
		// 对输入进行基本的合法非法判断
		String id = idTxt.getText();
		String passwd = String.valueOf(passwordTxt.getPassword());
		ResultMessage idResult = Utility.checkInputValid(id,4,14,false);
		ResultMessage passwdResult = Utility.checkInputValid(passwd,5,14,false);
		if(idResult != ResultMessage.SUCCESS) {
			MyOptionPane.showMessageDialog(null, "输入的用户名" + idResult.toFriendlyString() + "!");
			return;
		} else if(passwdResult != ResultMessage.SUCCESS) {
			MyOptionPane.showMessageDialog(null, "输入的密码" + passwdResult.toFriendlyString() + "!");
			return;
		}
		// 登录验证
		ResultMessage loginresult = loginController.login(usertype.getSelectedIndex(),id,passwd);
		if(loginresult == ResultMessage.SUCCESS){
			new HomeUI(loginController);
			frame.dispose();
		}else{
			if(loginresult==ResultMessage.WRONG_ID){
				MyOptionPane.showMessageDialog(null, "用户名错误！");
				idTxt.setText("");
				passwordTxt.setText("");
			} else if(loginresult==ResultMessage.WRONG_PASSWD) {
				MyOptionPane.showMessageDialog(null, "密码错误！");
				passwordTxt.setText("");
			} else {
				MyOptionPane.showMessageDialog(null, "服务器未开启！");
			}
		}
	}
}
