package ui.util;

import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class EditDialog extends JDialog{
	
	protected JFrame frame;
	
	public EditDialog(JFrame frame){
		super(frame,true);
		this.frame = frame;
		((JComponent) this.getContentPane()).setOpaque(true);
	}
	
	protected void processWindowEvent(WindowEvent e) {
		boolean flag = false;
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			int result = MyOptionPane.showConfirmDialog(frame, "是否放弃当前编辑？",
					"确认提示", MyOptionPane.YES_NO_OPTION,
					MyOptionPane.QUESTION_MESSAGE);
			if (result == MyOptionPane.YES_OPTION) {
				flag = true;
				dispose();
			}
		}
		if (flag) {
			// 点击的了YES,那么交给上面去处理关闭的处理
			super.processWindowEvent(e);
		}
	}
	
}
