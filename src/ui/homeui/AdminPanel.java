/**
 * 系统管理员主界面
 * @author Vboar
 * @date 2014/11/30
 */

package ui.homeui;

import ui.messageui.MessagePanel;
import ui.userui.UserPanel;
import ui.util.MyButton;
import ui.util.MyMainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AdminPanel extends MyMainPanel {

	private MyButton userManageBtn;
	
	private UserPanel userPanel;

	private MessagePanel messagePanel;
	
	public AdminPanel(HomeUI frame) {
		super(frame);
	}
	
	@Override
	public void initComponent(){
		super.initComponent();
		this.userManageBtn = new MyButton(this.pcfg.getButtons().element("usermanage"));
		this.userManageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userPanel = new UserPanel(frame);
				add(userPanel);
				repaint();
			}
		});
		this.add(this.userManageBtn);
	}

	public void showUser() {
		removeAllPanel();
		userPanel = new UserPanel(frame);
		add(userPanel);
		repaint();
	}

	@Override
	public void showMesssage() {
		removeAllPanel();
		messagePanel = new MessagePanel(frame);
		add(messagePanel);
		repaint();
	}


	private void removeAllPanel() {
		if(userPanel != null) remove(userPanel); userPanel = null;
		if(messagePanel != null) remove(messagePanel); messagePanel = null;
	}

}
