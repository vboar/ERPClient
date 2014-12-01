/**
 * 销售人员主界面
 * @author Vboar
 * @date 2014/11/30
 */

package ui.homeui;

import ui.customerui.CustomerPanel;
import ui.util.MyButton;
import ui.util.MyMainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class SalesmanPanel extends MyMainPanel {

	private MyButton customerManageBtn;

	private CustomerPanel customerPanel;

	public SalesmanPanel(HomeUI frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initComponent(){
		super.initComponent();
		this.customerManageBtn = new MyButton(this.pcfg.getButtons().element("customermanage"));
		this.customerManageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				customerPanel = new CustomerPanel(frame);
				add(customerPanel);
				repaint();
			}
		});
		this.add(this.customerManageBtn);
	}

	@Override
	public void showMesssage() {
		// TODO Auto-generated method stub
		
	}

}
