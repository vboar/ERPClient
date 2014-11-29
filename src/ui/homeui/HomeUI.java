/**
 * 主界面
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.homeui;

import javax.swing.JFrame;

import ui.accountui.AccountPanel;
import ui.commodityui.CategoryPanel;
import ui.customerui.CustomerPanel;
import ui.userui.UserPanel;
import ui.util.FrameUtil;
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
		this.addTimePanel();
		this.addMainPanel(lc);
		// 显示
		this.setVisible(true);
	}
	
	private void addUserInfoPanel(LoginController lc){
		this.getContentPane().add(new LoginUserInfoPanel(lc));
	}
	
	private void addMainPanel(LoginController lc){
		this.add(new LoginUserInfoPanel(lc));
		switch(lc.getUserType()) {
		case ADMINISTRATOR:
			this.add(new UserPanel(this));
			break;
		case SALESMAN:
			this.add(new CustomerPanel(this));
			break;
		case STOCKKEEPER:
			this.add(new CategoryPanel(this));
			break;
		case COUNTER:
			this.add(new AccountPanel(this));
			break;
		default:
			
		}
	}
	
	private void addTimePanel(){
		this.add(new TimePanel());
	}
}
