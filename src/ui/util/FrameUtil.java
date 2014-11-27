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

	private static Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
	
	private static Dimension SCREEN_SIZE = TOOLKIT.getScreenSize();

	public static void setFrameCenter(JFrame frame,int windowUp){

		Dimension windowSize = frame.getSize();
		frame.setLocation((SCREEN_SIZE.width-windowSize.width)>>1, ((SCREEN_SIZE.height-windowSize.height)>>1)-windowUp);
	}
}
