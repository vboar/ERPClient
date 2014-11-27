package ui.userui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import config.UserInfoDialogConfig;

@SuppressWarnings("serial")
public class UserInfoDialog extends JDialog{

	private MyLabel idLab;
	
	private MyLabel nameLab;
	
	private MyLabel passwordLab;
	
	private MyLabel typeLab;
	
	private MyLabel permissionLab;

	private MyTextField idTxt;
	
	private MyTextField nameTxt;
	
	private MyTextField passwordTxt;
	
	private MyComboBox typebox;
	
	private MyComboBox permissionbox;
	
	private MyButton commit;
	
	private MyButton cancel;
	
	private UserInfoDialogConfig cfg;
	
	public UserPanel panel;
	
	private Boolean isAdd;
	
	public UserInfoDialog(UserInfoDialogConfig cfg, JFrame frame, UserPanel panel,Boolean isAdd){
		super(frame,true);
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
	
	@SuppressWarnings("deprecation")
	public UserInfoDialog(UserInfoDialogConfig userinfo_DIALOG_CONFIG,
			JFrame homeframe, UserPanel userPanel, Boolean isAdd,UserVO vo) {
		this(userinfo_DIALOG_CONFIG,homeframe,userPanel,isAdd);
		this.idTxt.setText(vo.id);
		this.idTxt.enable(false);
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
		this.add(this.idLab);
		this.add(this.nameLab);
		this.add(this.passwordLab);
		this.add(this.typeLab);
		this.add(this.permissionLab);
	}
	
	private void initButtons(Element ele){
		this.commit = new MyButton(ele.element("commit"));
		this.commit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
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
								panel.setHasADialog(false);
							}else	MyOptionPane.showMessageDialog(null, "填写信息错误，添加失败！");	
						}else{
							if(panel.updateUser(vo)==ResultMessage.SUCCESS){
								MyOptionPane.showMessageDialog(null, "修改成功！");
								panel.setHasADialog(false);
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
				int result = MyOptionPane.showConfirmDialog(null, "确认取消添加该用户信息？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					UserInfoDialog.this.dispose();
					panel.setHasADialog(false);
				}
			}	
		});
		this.add(this.commit);
		this.add(this.cancel);
	}
	
	private void initTextFields(Element ele){
		this.idTxt = new MyTextField(ele.element("id"));
		this.nameTxt = new MyTextField(ele.element("name"));
		this.passwordTxt = new MyTextField(ele.element("password"));
		this.add(this.idTxt);
		this.add(this.nameTxt);
		this.add(this.passwordTxt);
	}
	
	private void initComboBoxes(Element ele){
		this.typebox = new MyComboBox(ele.element("type"));
		this.permissionbox = new MyComboBox(ele.element("permission"));
		this.add(this.typebox);
		this.add(this.permissionbox);
	}
}
