/**
 * 财务人员主界面
 * @author Vboar
 * @date 2014/11/30
 */

package ui.homeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyMainPanel;

@SuppressWarnings("serial")
public class CounterPanel extends MyMainPanel {

	private MyButton accountManageBtn;
	
	private MyButton paymentBtn;
	
	private MyButton conditionBtn;
	
	private MyButton logBtn;
	
	private MyButton initialBtn;
	
	public CounterPanel(HomeUI frame) {
		super(frame);
	}

	public void initComponent() {
		
		MyLabel title = new MyLabel(this.pcfg.getLabels().element("title"));
		this.add(title);
		
		this.accountManageBtn = new MyButton(pcfg.getButtons().element("account"));
		this.accountManageBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
	}

	@Override
	public void showMesssage() {
		
	}
	
}
