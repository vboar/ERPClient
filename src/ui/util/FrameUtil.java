/**
 * 界面工具类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package ui.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtil {

	public static void setFrameCenter(JFrame frame,int windowUp){
		Toolkit tkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = tkit.getScreenSize();
		Dimension windowSize = frame.getSize();
		frame.setLocation((screenSize.width-windowSize.width)>>1, ((screenSize.height-windowSize.height)>>1)-windowUp);
	}
	
}
