package ui.accountui;

import config.DialogConfig;
import org.dom4j.Element;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MyTextField;
import util.ResultMessage;
import vo.AccountVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AccountInfoDialog extends JDialog {

	private MyButton commit;
	
	private MyButton cancel;
	
	private MyLabel accountLabel;
	
	private MyLabel nameLabel;
	
	private MyTextField accountTxt;
	
	private MyTextField nameTxt;
	
	private DialogConfig cfg;
	
	private AccountPanel panel;
	
	private boolean isAdd;
	
	public AccountInfoDialog(DialogConfig cfg, JFrame frame, AccountPanel panel, boolean isAdd) {
		super(frame, true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.cfg = cfg;
		this.setTitle("账户信息");
		this.panel = panel;
		this.isAdd = isAdd;
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
		this.initComponent();
	}
	
	public AccountInfoDialog(DialogConfig cfg, JFrame frame, AccountPanel panel, boolean isAdd, 
			AccountVO vo) {
		this(cfg, frame, panel, isAdd);
		this.accountTxt.setText(vo.account);
		// 禁止改动
		this.accountTxt.setEnabled(false);
		this.nameTxt.setText(vo.name);
	}
	
	private void initComponent() {
		this.initLabels(this.cfg.getLabels());
		this.initButtons(this.cfg.getButtons());
		this.initTextFields(this.cfg.getTextFields());
	}
	
	private void initTextFields(Element ele) {
		this.accountTxt = new MyTextField(ele.element("account"));
		this.nameTxt = new MyTextField(ele.element("name"));
		this.add(accountTxt);
		this.add(nameTxt);
	}

	private void initButtons(Element ele) {
		this.commit = new MyButton(ele.element("commit"));
		this.add(commit);
		this.commit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String account = accountTxt.getText();
				String name = nameTxt.getText();
				AccountVO vo = new AccountVO(name, account, 0);
				int result = MyOptionPane.showConfirmDialog(null, "确认提交？", "确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result == MyOptionPane.YES_OPTION){
					if(isAdd) {
						if(panel.addAccount(vo) == ResultMessage.SUCCESS) {
							MyOptionPane.showMessageDialog(null, "添加成功！");
							dispose();
						} else {
							MyOptionPane.showMessageDialog(null, "填写信息错误，添加失败！");
						}
					} else {
						if(panel.updateAccount(vo) == ResultMessage.SUCCESS) {
							MyOptionPane.showMessageDialog(null, "修改成功！");
							dispose();
						} else {
							MyOptionPane.showMessageDialog(null, "填写信息错误，修改失败！");
						}
					}
					
				}
			}
			
		});
		
		this.cancel = new MyButton(ele.element("cancel"));
		this.cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int result = MyOptionPane.showConfirmDialog(null, "确认取消？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					dispose();
				}
				
			}
			
		});
		this.add(cancel);
	}

	private void initLabels(Element ele) {
		this.accountLabel = new MyLabel(ele.element("account"));
		this.nameLabel = new MyLabel(ele.element("name"));
		this.add(accountLabel);
		this.add(nameLabel);
	}
	
}
