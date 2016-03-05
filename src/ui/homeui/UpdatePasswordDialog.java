package ui.homeui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPasswordField;

import ui.util.MyLabel;
import ui.util.MyOptionPane;
import util.ResultMessage;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.userblservice.UserBLService;

@SuppressWarnings("serial")
public class UpdatePasswordDialog extends JDialog{
	
	private JPasswordField old;
	
	private JPasswordField newword;
	
	private JButton commit;
	
	private JButton cancel;
	
	private JFrame frame;
	
	public UpdatePasswordDialog(JFrame frame){
		super(frame,true);
		this.frame = frame;
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setBounds(0,0,400,200);
		this.setTitle("修改密码");
		this.setLayout(null);
        this.setResizable(false);
        this.setLocation(frame.getX()+300, frame.getY()+200);
        this.initComponent();
        this.setVisible(true);
	}

	private void initComponent(){
		this.add(new MyLabel("原密码",80,30,20,20));
		this.add(new MyLabel("新密码",80,30,20,60));
		this.old = new JPasswordField();
		this.old.setBounds(100, 20, 200, 30);
		this.newword = new JPasswordField();
		this.newword.setBounds(100,60,200,30);
		this.commit = new JButton("提交");
		this.commit.setBounds(100, 110, 80, 30);
		this.commit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updatePassword();
			}
		});
		this.cancel = new JButton("取消");
		this.cancel.setBounds(200, 110, 80, 30);
		this.cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame,"确认取消操作？", "系统提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					dispose();
				}
			}
		});
		this.add(old);
		this.add(newword);
		this.add(commit);
		this.add(cancel);
	}

	protected void updatePassword() {
		UserBLService user = ControllerFactoryImpl.getInstance().getUserController();
		String oldword = String.valueOf(this.old.getPassword());
		String newword = String.valueOf(this.newword.getPassword());
		if(oldword.isEmpty()||newword.isEmpty()){
			MyOptionPane.showMessageDialog(frame, "请输入完整！");
			return;
		}
		ResultMessage result = user.updatePassword(oldword, newword);
		if(result==ResultMessage.SUCCESS){
			MyOptionPane.showMessageDialog(frame, "修改成功！");
			this.dispose();
		}else{
			MyOptionPane.showMessageDialog(frame, "修改失败！");
			this.dispose();
		}
	}
}
