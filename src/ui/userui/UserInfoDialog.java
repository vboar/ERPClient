/**
 * 添加/修改用户信息面板
 * @author JaneLDQ
 * @date 2014/11/26
 */
package ui.userui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import config.DialogConfig;

@SuppressWarnings("serial")
public class UserInfoDialog extends JDialog {
	
	private MyLabel idTip;
	
	private MyLabel nameTip;
	
	private MyLabel passwordTip;

	private MyTextField idTxt;
	
	private MyTextField nameTxt;
	
	private MyTextField passwordTxt;
	
	private MyComboBox typebox;
	
	private MyComboBox permissionbox;
	
	private MyButton commit;
	
	private MyButton cancel;
	
	private DialogConfig cfg;
	
	private UserPanel panel;
	
	private JFrame frame;
	
	private boolean isAdd;
	
	/**
	 * 构造函数
	 * @param cfg 对话框配置对象
	 * @param frame 主窗口
	 * @param panel 用户管理面板
	 * @param isAdd true为添加，false为修改
	 */
	public UserInfoDialog(DialogConfig cfg, JFrame frame, UserPanel panel,boolean isAdd){
		super(frame,true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle(cfg.getTitle());
		this.isAdd = isAdd;
		this.panel = panel;
		this.frame = frame;
		this.cfg = cfg;
		// 设置基本属性
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
		// 初始化组件
		this.initComponent();
	}

	/**
	 * 修改用户信息时的构造函数
	 * @param userinfo_DIALOG_CONFIG
	 * @param homeframe
	 * @param userPanel
	 * @param isAdd
	 * @param vo 待修改用户信息VO
	 */
	public UserInfoDialog(DialogConfig userinfo_DIALOG_CONFIG,
			JFrame homeframe, UserPanel userPanel, Boolean isAdd,UserVO vo) {
		this(userinfo_DIALOG_CONFIG,homeframe,userPanel,isAdd);
		this.idTxt.setText(vo.id);
		this.idTxt.setEnabled(false);
		this.nameTxt.setText(vo.name);
		this.passwordTxt.setText(vo.password);
		if(vo.type.ordinal()<=1){
			this.typebox.setSelectedIndex(vo.type.ordinal());
		}else{
			this.typebox.setSelectedIndex(0);
		}
		this.permissionbox.setSelectedIndex(vo.permission);
	}
 
	/**
	 * 初始化组件
	 */
	private void initComponent(){
		this.initLabels(this.cfg.getLabels());
		this.initTextFields(this.cfg.getTextFields());
		this.initComboBoxes(this.cfg.getComboboxes());
		this.initButtons(this.cfg.getButtons());
	}
	
	/**
	 * 初始化标签
	 * @param ele
	 */
	private void initLabels(Element ele){
		this.idTip = new MyLabel(ele.element("idtip"));
		this.idTip.setVisible(false);
		this.nameTip = new MyLabel(ele.element("nametip"));
		this.nameTip.setVisible(false);
		this.passwordTip = new MyLabel(ele.element("passwordtip"));
		this.passwordTip.setVisible(false);
		this.add(new MyLabel(ele.element("id")));
		this.add(new MyLabel(ele.element("name")));
		this.add(new MyLabel(ele.element("password")));
		this.add(new MyLabel(ele.element("type")));
		this.add(new MyLabel(ele.element("permission")));
		this.add(this.idTip);
		this.add(this.nameTip);
		this.add(this.passwordTip);
	}
	
	/**
	 * 初始化按钮
	 * @param ele
	 */
	private void initButtons(Element ele){
		this.commit = new MyButton(ele.element("commit"));
		this.commit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				UserVO vo = new UserVO(idTxt.getText(),passwordTxt.getText(),
						UserType.check(typebox.getSelectedItem().toString()),
						permissionbox.getSelectedIndex(),nameTxt.getText());
					int result = MyOptionPane.showConfirmDialog(frame, "确认提交？", "确认提示",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						if(isAdd){
							ResultMessage addResult = panel.addUser(vo);
							switch(addResult){
								case SUCCESS:
									MyOptionPane.showMessageDialog(frame, "添加成功！");
									UserInfoDialog.this.dispose(); return;
								case EXIST:
									MyOptionPane.showMessageDialog(frame, "该用户ID已存在！");return;
								default:
									MyOptionPane.showMessageDialog(frame, "填写信息不符合要求，请重新填写！");
							}	
						}else{
							if(panel.updateUser(vo)==ResultMessage.SUCCESS){
								MyOptionPane.showMessageDialog(frame, "修改成功！");
								UserInfoDialog.this.dispose();
							}else	MyOptionPane.showMessageDialog(frame, "填写信息不符合要求，请重新填写！");	
						}
					}	
			}			
		});
		this.cancel = new MyButton(ele.element("cancel"));
		this.cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "确认取消操作？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					UserInfoDialog.this.dispose();
				}
			}	
		});
		this.add(this.commit);
		this.add(this.cancel);
	}
	
	/**
	 * 初始化输入框
	 * @param ele
	 */
	private void initTextFields(Element ele){
		this.idTxt = new MyTextField(ele.element("id"));
		this.idTxt.addMouseListener(new  MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				idTip.setVisible(true);
				nameTip.setVisible(false);
				passwordTip.setVisible(false);
			}
		});
		this.nameTxt = new MyTextField(ele.element("name"));
		this.nameTxt.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				idTip.setVisible(false);
				nameTip.setVisible(true);
				passwordTip.setVisible(false);
			}});
		this.passwordTxt = new MyTextField(ele.element("password"));
		this.passwordTxt.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				idTip.setVisible(false);
				nameTip.setVisible(false);
				passwordTip.setVisible(true);
			}
		});
		this.add(this.idTxt);
		this.add(this.nameTxt);
		this.add(this.passwordTxt);
	}
	
	/**
	 * 初始化选择框
	 * @param ele
	 */
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
