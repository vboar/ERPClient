/**
 * 财务人员主界面
 * @author Vboar
 * @date 2014/11/30
 */

package ui.homeui;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogic.loginbl.Login;
import ui.accountui.AccountPanel;
import ui.conditionui.ConditionPanel;
import ui.initialui.InitialPanel;
import ui.logui.LogPanel;
import ui.messageui.MessagePanel;
import ui.paymentui.PaymentPanel;
import ui.util.MyButton;
import ui.util.MyMainPanel;
import ui.util.MyOptionPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public CounterPanel(JFrame frame) {
		super(frame);
	}

	@Override
	public void initComponent() {
		
		super.initComponent();

		this.accountManageBtn = new MyButton(pcfg.getButtons().element("account"));
		this.accountManageBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showAccount();
			}
			
		});
		this.add(accountManageBtn);
		// 非最高权限不可用
		if(ControllerFactoryImpl.getInstance().getUserController().
				getById(Login.currentUserId).permission == 0) {
			accountManageBtn.setEnabled(false);
		}
		
		this.paymentBtn = new MyButton(pcfg.getButtons().element("payment"));
		this.paymentBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showPayment();
			}
			
		});
		this.add(paymentBtn);

		conditionBtn = new MyButton(pcfg.getButtons().element("condition"));
		add(conditionBtn);
		conditionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				MyOptionPane.showMessageDialog(null, "界面正在开发中...");
//				showCondition();
			}
		});

		logBtn = new MyButton(pcfg.getButtons().element("log"));
		add(logBtn);
		logBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				MyOptionPane.showMessageDialog(null, "界面正在开发中...");
//				showLog();
			}
		});

		initialBtn = new MyButton(pcfg.getButtons().element("initial"));
		add(initialBtn);
		initialBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				MyOptionPane.showMessageDialog(null, "界面正在开发中...");
//				showInitial();
			}
		});
		
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

	public void showCondition() {
		removeAllPanel();
		conditionPanel = new ConditionPanel(frame);
		add(conditionPanel);
		repaint();
	}
	
	public void showLog() {
		removeAllPanel();
		logPanel = new LogPanel(frame);
		add(logPanel);
		repaint();
	}
	
	public void showInitial() {
		removeAllPanel();
		initialPanel = new InitialPanel(frame);
		add(initialPanel);
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
		if(accountPanel != null) remove(accountPanel); accountPanel = null;
		if(paymentPanel != null) remove(paymentPanel); paymentPanel = null;
		if(conditionPanel != null) remove(conditionPanel); conditionPanel = null;
		if(logPanel != null) remove(logPanel); logPanel = null;
		if(initialPanel != null) remove(initialPanel); initialPanel = null;
		if(messagePanel != null) remove(messagePanel); messagePanel = null;
	}
	
}
