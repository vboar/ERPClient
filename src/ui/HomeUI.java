/**
 * 主界面
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui;

import javax.swing.JFrame;

import ui.util.FrameUtil;
import config.ERPConfig;
import config.FrameConfig;

@SuppressWarnings("serial")
public class HomeUI extends JFrame{

	public HomeUI(){
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
		// 显示
		this.setVisible(true);
	}

}
