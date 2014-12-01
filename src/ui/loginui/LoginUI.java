/**
 * 登录界面
 * @author JaneLDQ
 * @date 2014/11/23
 */
package ui.loginui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import ui.util.FrameUtil;
import config.ERPConfig;
import config.FrameConfig;

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
		
//		WebLookAndFeel.globalControlFont = new Font("微软雅黑", Font.PLAIN, 12);
//		WebLookAndFeel.globalTooltipFont = new Font("微软雅黑", Font.PLAIN, 12);
//		WebLookAndFeel.globalAlertFont = new Font("微软雅黑", Font.PLAIN, 12);
//		WebLookAndFeel.globalMenuFont = new Font("微软雅黑", Font.PLAIN, 12);
//		WebLookAndFeel.globalAcceleratorFont = new Font("微软雅黑", Font.PLAIN, 12);
//		WebLookAndFeel.globalTitleFont = new Font("微软雅黑", Font.PLAIN, 12);
//		WebLookAndFeel.globalTextFont = new Font("微软雅黑", Font.PLAIN, 12);
//		WebLookAndFeel.install ();
		
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
//			UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
//			UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
//			UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
			System.setProperty("sun.java2d.noddraw", "true");
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new LoginUI();
	}
}
