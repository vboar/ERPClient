package ui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import util.ResultMessage;
import config.DialogConfig;
import config.ERPConfig;

@SuppressWarnings("serial")
public class SavePathDialog extends JDialog {

	private MyTextField ownPath;

	private MyTextField defaultPath;

	private MyRadioButton selectPathBtn;

	private MyRadioButton defaultBtn;
	
	private MyJFileChooser filesaver;

	private MyButton choose;
	
	private MyButton commit;

	private MyButton cancel;

	private DialogConfig cfg;
	
	private ExcelSaver saver;

	public SavePathDialog(JFrame frame,ExcelSaver saver) {
		super(frame, true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.cfg = ERPConfig.getSAVER_DIALOG_CONFIG();
		this.saver = saver;
		// 设置对话框基本属性
		this.setTitle(cfg.getTitle());
		this.setBounds(cfg.getX(), cfg.getW(), cfg.getW(), cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX() + this.cfg.getX(), frame.getY()
				+ this.cfg.getY());
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
	}

	private void initComponent() {
		this.filesaver = new MyJFileChooser();	
		// 单选按钮
		ButtonGroup group = new ButtonGroup();
		this.defaultBtn = new MyRadioButton(cfg.getButtons().element("default"));
		this.defaultBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(defaultBtn.isSelected()){
					ownPath.setFocusable(false);
					choose.setEnabled(false);
				}
			}
		});
		this.defaultBtn.setSelected(true);
		this.selectPathBtn = new MyRadioButton(cfg.getButtons().element("own"));
		this.selectPathBtn.setSelected(false);
		this.selectPathBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectPathBtn.isSelected()) {
					ownPath.setFocusable(true);
					choose.setEnabled(true);
				}
			}
		});
		this.add(defaultBtn);
		this.add(selectPathBtn);
		group.add(defaultBtn);
		group.add(selectPathBtn);
		// 选择路径
		this.choose = new MyButton(cfg.getButtons().element("choose"));
		this.choose.setEnabled(false);
		this.choose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = filesaver.showSaveDialog(SavePathDialog.this);
				if(result == JFileChooser.APPROVE_OPTION){
					 //获得该文件    
		            File f = filesaver.getSelectedFile();    
		            String path = f.getPath();
		            if(!(path.endsWith(".xls")||path.endsWith(".xlsx"))){
			            path += ".xls";
		            }
		            ownPath.setText(path);
				}
			}
		});
		// 提交按钮
		this.commit = new MyButton(this.cfg.getButtons().element("commit"));
		this.commit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = "";
				if(defaultBtn.isSelected()){
					path = defaultPath.getText();
				}else{
					if(ownPath.getText().isEmpty()){
						MyOptionPane.showMessageDialog(SavePathDialog.this, "请输入自定义存储路径！");
					}else	path = ownPath.getText();
				}
				if(saver.setSavePath(path)==ResultMessage.SUCCESS){
					MyOptionPane.showMessageDialog(SavePathDialog.this, "保存成功！");
					dispose();
				}else	MyOptionPane.showMessageDialog(SavePathDialog.this, "保存失败！");		
			}
		});
		// 取消按钮
		this.cancel = new MyButton(this.cfg.getButtons().element("cancel"));
		this.cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(SavePathDialog.this, "确认取消？",
						"确认提示", MyOptionPane.YES_NO_OPTION,
						MyOptionPane.QUESTION_MESSAGE);
				if (result == MyOptionPane.YES_OPTION) {
					SavePathDialog.this.dispose();
				}
			}
		});
		this.add(choose);
		this.add(this.commit);
		this.add(this.cancel);
		// 默认路径
		this.defaultPath = new MyTextField(cfg.getTextFields().element("default"));
		this.defaultPath.setEditable(false);
		// TODO 
		this.defaultPath.setText(saver.getDefaultPath()!=null?saver.getDefaultPath():"d:\\erp.xls");
		// 自定义路径
		this.ownPath = new MyTextField(cfg.getTextFields().element("own"));
		this.ownPath.setFocusable(false);
		this.add(defaultPath);
		this.add(ownPath);
	}
	
	@Override
	protected void processWindowEvent(WindowEvent e) {
		boolean flag = false;
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			int result = MyOptionPane.showConfirmDialog(this, "是否取消导出Excel文件？",
					"确认提示", MyOptionPane.YES_NO_OPTION,
					MyOptionPane.QUESTION_MESSAGE);
			if (result == MyOptionPane.YES_OPTION) {
				flag = true;
				dispose();
			}
		}
		if (flag) super.processWindowEvent(e);
	}
}
