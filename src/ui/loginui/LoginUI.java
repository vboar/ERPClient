/**
 * 登录界面
 * @author JaneLDQ
 * @date 2014/11/23
 */
package ui.loginui;

import config.ERPConfig;
import config.FrameConfig;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import ui.util.FrameUtil;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class LoginUI extends JFrame {

	public LoginUI() {
		// 获得窗口配置
		FrameConfig fcfg = ERPConfig.getLOGINFRAME_CONFIG();
		// 设置大小
		this.setSize(fcfg.getWidth(), fcfg.getHeight());
		// 设置不可更改大小
		this.setResizable(false);
		// 默认关闭退出
		this.setDefaultCloseOperation(3);
		// 居中
		FrameUtil.setFrameCenter(this, fcfg.getWindowUp());
		this.getContentPane().add(new LoginPanel(this));
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		
		Font font = new Font("微软雅黑", Font.PLAIN, 13);
		@SuppressWarnings("rawtypes")
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
		    Object value = UIManager.get(key);
		    if (value instanceof javax.swing.plaf.FontUIResource) {
		    	UIManager.put(key, font);
		    }
		}
		
		try {
			System.setProperty("sun.java2d.noddraw", "true");
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible" ,false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new LoginUI();
	}
}
