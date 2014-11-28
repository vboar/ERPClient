/**
 * 添加/修改用户信息面板
 * @author JaneLDQ
 * @date 2014/11/26
 */
package ui.userui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MyTextField;
import util.ResultMessage;
import util.UserType;
import vo.UserVO;
import config.InfoDialogConfig;

@SuppressWarnings("serial")
public class UserInfoDialog extends JDialog {

	private MyLabel idLab;
	
	private MyLabel idTip;
	
	private MyLabel nameLab;
	
	private MyLabel nameTip;
	
	private MyLabel passwordLab;
	
	private MyLabel passwordTip;
	
	private MyLabel typeLab;
	
	private MyLabel permissionLab;

	private MyTextField idTxt;
	
	private MyTextField nameTxt;
	
	private MyTextField passwordTxt;
	
	private MyComboBox typebox;
	
	private MyComboBox permissionbox;
	
	private MyButton commit;
	
	private MyButton cancel;
	
	private InfoDialogConfig cfg;
	
	public UserPanel panel;
	
	private boolean isAdd;
	
	public UserInfoDialog(InfoDialogConfig cfg, JFrame frame, UserPanel panel,Boolean isAdd){
		super(frame,true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle("用户信息");
		this.isAdd = isAdd;
		this.panel = panel;
		this.cfg = cfg;
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
		this.initComponent();
		this.initButtons(this.cfg.getButtons());
	}

	public UserInfoDialog(InfoDialogConfig userinfo_DIALOG_CONFIG,
			JFrame homeframe, UserPanel userPanel, Boolean isAdd,UserVO vo) {
		this(userinfo_DIALOG_CONFIG,homeframe,userPanel,isAdd);
		this.idTxt.setText(vo.id);
		this.idTxt.setEnabled(false);
		this.nameTxt.setText(vo.name);
		this.passwordTxt.setText(vo.password);
		this.typebox.setSelectedIndex(vo.type.ordinal());
		this.permissionbox.setSelectedIndex(vo.permission);
	}

	private void initComponent(){
		this.initLabels(this.cfg.getLabels());
		this.initTextFields(this.cfg.getTextFields());
		this.initComboBoxes(this.cfg.getComboboxes());

	}
	
	private void initLabels(Element ele){
		this.idLab = new MyLabel(ele.element("id"));
		this.nameLab = new MyLabel(ele.element("name"));
		this.passwordLab = new MyLabel(ele.element("password"));
		this.typeLab = new MyLabel(ele.element("type"));
		this.permissionLab = new MyLabel(ele.element("permission"));
		this.idTip = new MyLabel(ele.element("idtip"));
		this.idTip.setVisible(false);
		this.nameTip = new MyLabel(ele.element("nametip"));
		this.nameTip.setVisible(false);
		this.passwordTip = new MyLabel(ele.element("passwordtip"));
		this.passwordTip.setVisible(false);
		this.add(this.idLab);
		this.add(this.nameLab);
		this.add(this.passwordLab);
		this.add(this.typeLab);
		this.add(this.permissionLab);
		this.add(this.idTip);
		this.add(this.nameTip);
		this.add(this.passwordTip);
	}
	
	private void initButtons(Element ele){
		this.commit = new MyButton(ele.element("commit"));
		this.commit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(UserType.check(typebox.getSelectedItem().toString()));
				UserVO vo = new UserVO(idTxt.getText(),passwordTxt.getText(),
						UserType.check(typebox.getSelectedItem().toString()),
						permissionbox.getSelectedIndex(),nameTxt.getText());
					int result = MyOptionPane.showConfirmDialog(null, "确认提交？", "确认提示",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						if(isAdd){
							if(panel.addUser(vo)==ResultMessage.SUCCESS){
								MyOptionPane.showMessageDialog(null, "添加成功！");
								UserInfoDialog.this.dispose();
							}else	MyOptionPane.showMessageDialog(null, "填写信息错误，添加失败！");	
						}else{
							if(panel.updateUser(vo)==ResultMessage.SUCCESS){
								MyOptionPane.showMessageDialog(null, "修改成功！");
								UserInfoDialog.this.dispose();
							}else	MyOptionPane.showMessageDialog(null, "填写信息错误，请重新填写！");	
						}
					}
				
			}			
		});
		this.cancel = new MyButton(ele.element("cancel"));
		this.cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(null, "确认取消操作？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					UserInfoDialog.this.dispose();
				}
			}	
		});
		this.add(this.commit);
		this.add(this.cancel);
	}
	
	private void initTextFields(Element ele){
		this.idTxt = new MyTextField(ele.element("id"));
		this.idTxt.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
				idTip.setVisible(true);
				nameTip.setVisible(false);
				passwordTip.setVisible(false);
			}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		this.nameTxt = new MyTextField(ele.element("name"));
		this.nameTxt.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
				idTip.setVisible(false);
				nameTip.setVisible(true);
				passwordTip.setVisible(false);
			}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		this.passwordTxt = new MyTextField(ele.element("password"));
		this.passwordTxt.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
				idTip.setVisible(false);
				nameTip.setVisible(false);
				passwordTip.setVisible(true);
			}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		this.add(this.idTxt);
		this.add(this.nameTxt);
		this.add(this.passwordTxt);
	}
	
	private void initComboBoxes(Element ele){
		this.typebox = new MyComboBox(ele.element("type"));
		this.typebox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(typebox.getSelectedItem().toString().equals(UserType.ADMINISTRATOR.toFriendString())){
					permissionbox.setEnabled(false);
				}else{
					permissionbox.setEnabled(true);
				}
			}		
		});
		this.permissionbox = new MyComboBox(ele.element("permission"));
		if(typebox.getSelectedItem().toString().equals(UserType.ADMINISTRATOR.toFriendString())){
			this.permissionbox.setEnabled(false);
		}
		this.add(this.typebox);
		this.add(this.permissionbox);
	}
}
