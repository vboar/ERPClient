/**
 * 登录面板类
 * @author JaneLDQ
 * @date 2014/11/23
 */
package ui.loginui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import ui.util.MyOptionPane;
import ui.HomeUI;
import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyTextField;
import util.ResultMessage;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogic.loginbl.LoginController;
import config.ERPConfig;
import config.PanelConfig;

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
	
	private LoginUI frame;
	
	private LoginController loginController;
	
	public LoginPanel(LoginUI frame){
		this.loginController = ControllerFactoryImpl.getInstance().getLoginController();
		this.frame = frame;
		// 设置布局管理器为自由布局
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.repaint();
	}

	/**
	 * 初始化组件
	 */
	private void initComponent() {
		//获得面板配置
		PanelConfig panelCfg = ERPConfig.getLOGINFRAME_CONFIG().getPanelsCfg().get(0);
		this.initComboBox(panelCfg);
		this.initText(panelCfg);
		this.initLabel(panelCfg);
		this.initButtons(panelCfg);
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
		this.add(this.idTxt);
		this.add(this.passwordTxt);
		
	}
	
	public void initComboBox(PanelConfig cfg){
		// 获得复选框配置
		this.usertype = new MyComboBox(cfg.getComboboxes().element("usertype"));
		this.add(this.usertype);
	}
	
	public void initLoginBtn(PanelConfig cfg){
		// 初始化登录按钮
		this.loginBtn = new MyButton(cfg.getButtons().element("login"));
		this.loginBtn.addActionListener(new ActionListener(){
			// 事件处理
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultMessage login = loginController.login(idTxt.getText(), String.valueOf(passwordTxt.getPassword()));
				if(login == ResultMessage.SUCCESS){
					new HomeUI();
					frame.dispose();
				}else{
					MyOptionPane.showMessageDialog(null, "用户名或密码错误！");
				}
			}
		});
	}
	
	public void initQuitBtn(PanelConfig cfg){
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

	public void initSetBtn(PanelConfig cfg){
		this.setBtn = new MyButton(cfg.getButtons().element("setting"));
	}
}
