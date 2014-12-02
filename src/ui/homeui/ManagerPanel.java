/**
 * 总经理主界面
 * @author Vboar
 * @date 2014/11/30
 */

package ui.homeui;

import ui.approvalui.ApprovalPanel;
import ui.conditionui.ConditionPanel;
import ui.logui.LogPanel;
import ui.messageui.MessagePanel;
import ui.promotionui.PromotionPanel;
import ui.util.MyButton;
import ui.util.MyMainPanel;
import ui.util.MyOptionPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ManagerPanel extends MyMainPanel {

	private MyButton approvalBtn;

	private MyButton promotionBtn;

	private MyButton conditionBtn;

	private MyButton logBtn;

	private ApprovalPanel approvalPanel;

	private PromotionPanel promotionPanel;

	private ConditionPanel conditionPanel;

	private LogPanel logPanel;

	private MessagePanel messagePanel;

	public ManagerPanel(JFrame frame) {
		super(frame);
	}

	@Override
	public void initComponent() {

		super.initComponent();

		approvalBtn = new MyButton(pcfg.getButtons().element("approval"));
		add(approvalBtn);
		approvalBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				MyOptionPane.showMessageDialog(null, "界面正在开发中...");
//				showApproval();
			}
		});

		promotionBtn = new MyButton(pcfg.getButtons().element("promotion"));
		add(promotionBtn);
		promotionBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showPromotion();
			}
		});

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

	}

	public void showApproval() {
		removeAllPanel();
		approvalPanel = new ApprovalPanel(frame);
		add(approvalPanel);
		repaint();
	}

	public void showPromotion() {
		removeAllPanel();
		promotionPanel = new PromotionPanel(frame);
		this.add(promotionPanel);
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

	@Override
	public void showMesssage() {
		removeAllPanel();
		messagePanel = new MessagePanel(frame);
		add(messagePanel);
		repaint();
	}

	private void removeAllPanel() {
		if(approvalPanel != null) remove(approvalPanel); approvalPanel = null;
		if(promotionPanel != null) remove(promotionPanel); promotionPanel = null;
		if(conditionPanel != null) remove(conditionPanel); conditionPanel = null;
		if(logPanel != null) remove(logPanel); logPanel = null;
		if(messagePanel != null) remove(messagePanel); messagePanel = null;
	}

}
