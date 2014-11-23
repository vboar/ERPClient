/**
 * 主界面
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.homeui;

import javax.swing.JFrame;

import ui.userui.UserPanel;
import ui.util.FrameUtil;
import util.UserType;
import businesslogic.loginbl.LoginController;
import config.ERPConfig;
import config.FrameConfig;

@SuppressWarnings("serial")
public class HomeUI extends JFrame{
		
	public HomeUI(LoginController lc){
		// 获得窗口配置
		FrameConfig fcfg = ERPConfig.getHOMEFRAME_CONFIG();
		// 设置标题
		this.setTitle(fcfg.getTitle());
		// 设置大小
		this.setSize(fcfg.getWidth(), fcfg.getHeight());
		// 设置不可更改大小
		this.setResizable(false);
		// 默认关闭退出
		this.setDefaultCloseOperation(3);
		// 居中
		FrameUtil.setFrameCenter(this,fcfg.getWindowUp());
		// 设置为自由布局
		this.getContentPane().setLayout(null);
		// 初始化组件
		this.addUserInfoPanel(lc);
		this.addMainPanel(lc);
		// 显示
		this.setVisible(true);
	}
	
	public void addUserInfoPanel(LoginController lc){
		this.getContentPane().add(new LoginUserInfoPanel(lc));
	}
	
	public void addMainPanel(LoginController lc){
		if(lc.getUserType() == UserType.ADMINISTRATOR){
			this.add(new LoginUserInfoPanel(lc));
			this.add(new UserPanel(this));
		}
	}
}
