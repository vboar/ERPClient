package ui;

import javax.swing.JFrame;

import ui.login.LoginPanel;
import ui.util.FrameUtil;
import config.ERPConfig;
import config.FrameConfig;

@SuppressWarnings("serial")
public class LoginUI extends JFrame{
	
	public LoginUI(){
		// 获得窗口配置
		FrameConfig fcfg = ERPConfig.getLOGINFRAME_CONFIG();
		// 设置大小
		this.setSize(fcfg.getWidth(), fcfg.getHeight());
		// 设置不可更改大小
		this.setResizable(false);
		// 默认关闭退出
		this.setDefaultCloseOperation(3);
		// 居中
		FrameUtil.setFrameCenter(this,fcfg.getWindowUp());
		// 设置面板
		this.setContentPane(new LoginPanel());
		// 显示
		this.setVisible(true);
	}
		
	public static void main(String[] args) {
		new LoginUI();
	}

}
