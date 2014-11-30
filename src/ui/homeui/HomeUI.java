/**
 * 主界面
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.homeui;

import javax.swing.JFrame;

import ui.util.FrameUtil;
import ui.util.MyMainPanel;
import businesslogic.loginbl.LoginController;
import config.ERPConfig;
import config.FrameConfig;

@SuppressWarnings("serial")
public class HomeUI extends JFrame {
	
	private MyMainPanel stockKeeperPanel;
	
	private MyMainPanel salesmanPanel;
	
	private MyMainPanel counterPanel;
	
	private MyMainPanel managerPanel;
	
	private MyMainPanel adminPanel;
	
	
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
		this.add(new TimePanel());
		this.getContentPane().add(new LoginUserInfoPanel(lc));
		this.addMainPanel(lc);
		// 显示
		this.setVisible(true);
	}
	
	private void addMainPanel(LoginController lc){
		switch(lc.getUserType()) {
		case ADMINISTRATOR:
			adminPanel = new AdminPanel(this);
			this.add(adminPanel);
			break;
		case SALESMAN:
			salesmanPanel = new SalesmanPanel(this);
			this.add(salesmanPanel);
			break;
		case STOCKKEEPER:
			stockKeeperPanel = new StockKeeperPanel(this);
			this.add(stockKeeperPanel);
			break;
		case COUNTER:
			counterPanel = new CounterPanel(this);
			this.add(counterPanel);
			break;
		case MANAGER:
			managerPanel = new ManagerPanel(this);
			this.add(managerPanel);
		default:
			
		}
	}
	
}
