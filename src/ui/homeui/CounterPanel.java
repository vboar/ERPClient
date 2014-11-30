/**
 * 财务人员主界面
 * @author Vboar
 * @date 2014/11/30
 */

package ui.homeui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.accountui.AccountPanel;
import ui.conditionui.ConditionPanel;
import ui.initialui.InitialPanel;
import ui.logui.LogPanel;
import ui.messageui.MessagePanel;
import ui.paymentui.PaymentPanel;
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
	
	private AccountPanel accountPanel;
	
	private PaymentPanel paymentPanel;
	
	private ConditionPanel conditionPanel;
	
	private LogPanel logPanel;
	
	private InitialPanel initialPanel;
	
	private MessagePanel messagePanel;
	
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
				showAccount();
			}
			
		});
		this.add(accountManageBtn);
		
		this.paymentBtn = new MyButton(pcfg.getButtons().element("payment"));
		this.paymentBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPayment();
			}
			
		});
		this.add(paymentBtn);
		
	}
	
	public void showAccount() {
		removeAllPanel();
		accountPanel = new AccountPanel(frame);
		this.add(accountPanel);
		repaint();
	}
	
	public void showPayment() {
		removeAllPanel();
		paymentPanel = new PaymentPanel(frame);
		this.add(paymentPanel);
		repaint();
	}
	
	public void showLog() {
		
	}
	
	public void showInitial() {
		
	}

	@Override
	public void showMesssage() {
		
	}
	
	private void removeAllPanel() {
		if(accountPanel != null) remove(accountPanel); accountPanel = null;
		if(paymentPanel != null) remove(paymentPanel); paymentPanel = null;
		if(conditionPanel != null) remove(conditionPanel); conditionPanel = null;
		if(logPanel != null) remove(logPanel); logPanel = null;
		if(initialPanel != null) remove(initialPanel); initialPanel = null;
		if(messagePanel != null) remove(messagePanel); messagePanel = null;
	}
	
}
