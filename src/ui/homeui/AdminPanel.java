/**
 * 系统管理员主界面
 * @author Vboar
 * @date 2014/11/30
 */

package ui.homeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.userui.UserPanel;
import ui.util.MyButton;
import ui.util.MyMainPanel;

@SuppressWarnings("serial")
public class AdminPanel extends MyMainPanel {

	private MyButton userManageBtn;
	
	private UserPanel userpanel;
	
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
				userpanel = new UserPanel(frame);
				add(userpanel);
				repaint();
			}
		});
		this.add(this.userManageBtn);
	}

	@Override
	public void showMesssage() {
		// TODO Auto-generated method stub

	}

}
