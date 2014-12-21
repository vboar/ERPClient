/**
 * 用户管理面板
 * @author JaneLDQ
 * @date 2014/11/23
 */
package ui.userui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dom4j.Element;

import ui.util.FuzzySearch;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MySpecialTextField;
import util.ResultMessage;
import vo.UserVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.userblservice.UserBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class UserPanel extends JPanel implements FuzzySearch{

	private MyButton addbtn;
	
	private MyButton deletebtn;
	
	private MyButton updatebtn;
	
	private MyButton findbtn;
	
	private MyButton showAll;

	private MyLabel userlist;
	
	private Image bg;
	
	private UserTablePane userTable;
	
	private UserInfoDialog userInfoDiaglog;
	
	private JFrame frame;
	
	private MySpecialTextField userfindbox;
	
	private PanelConfig cfg;
	
	private UserBLService userController;
		
	/**
	 * 构造函数
	 * @param frame
	 */
	public UserPanel(JFrame frame){
		this.frame = frame;
		this.userController = ControllerFactoryImpl.getInstance().getUserController();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基础属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		this.bg = cfg.getBg();
		// 初始化组件
		this.initComponent();
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(),cfg.getH(),null);
	}
	
	/**
	 * 初始化组件
	 * @param cfg
	 */
	private void initComponent() {
		this.initButtons(cfg.getButtons());
		this.initLabels(cfg.getLabels());
		this.initFindComboBox(cfg.getTextFields());
		// 初始化用户列表
		this.userTable = new UserTablePane(new TableConfig(cfg.getTablepane()), userController);
		this.add(this.userTable);
	}

	/**
	 * 初始化Combobox
	 * @param box
	 */
	private void initFindComboBox(Element box) {
		this.userfindbox = new MySpecialTextField(box.element("findinput"),this);
		this.add(this.userfindbox);
		this.userfindbox.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					findUser(userfindbox.getText());
				}
			}
		});
	}

	/**
	 * 初始化标签
	 * @param labels
	 */
	private void initLabels(Element labels) {
		this.userlist = new MyLabel(labels.element("userlist"));
		this.add(new MyLabel(labels.element("title")));
		this.add(this.userlist);
	}

	/**
	 * 初始化按钮
	 * @param buttons
	 */
	private void initButtons(Element buttons) {
		//添加按钮
		this.addbtn = new MyButton(buttons.element("add"));
		this.addbtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					showUserDialog();			
			}		
		});
		this.add(this.addbtn);
		// 修改按钮
		this.updatebtn = new MyButton(buttons.element("update"));
		this.updatebtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userTable.isSelected()){
					showUpdateDialog();
				}else{
					MyOptionPane.showMessageDialog(null, "请选择要修改的用户信息！");
				}
			}
		});
		this.add(this.updatebtn);
		// 查找按钮
		this.findbtn = new MyButton(buttons.element("find"));
		this.findbtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				findUser(userfindbox.getText());
			}
		});
		this.add(this.findbtn);
		// 显示所有按钮
		this.showAll = new MyButton(buttons.element("showall"));
		this.showAll.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				userTable.showFindTable(userController.show());
				userTable.updateUI();
			}
		});
		this.add(this.showAll);
		// 删除按钮
		this.initDeleteBtn(buttons.element("delete"));
	}
	
	private void initDeleteBtn(Element delete){
		this.deletebtn = new MyButton(delete);
		this.deletebtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userTable.isSelected()){
					int result = MyOptionPane.showConfirmDialog(frame, "确认删除该用户信息？","删除用户",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						ResultMessage delresult = deleteUser();
						if(delresult == ResultMessage.SUCCESS){
							MyOptionPane.showMessageDialog(frame, "删除成功！");
						}else{
							MyOptionPane.showMessageDialog(frame, "删除失败！","提示信息", 
									MyOptionPane.ERROR_MESSAGE, null);
						}
					}
				}else{
					MyOptionPane.showMessageDialog(frame, "请选择要删除的用户信息！");
				}
			}
		});
		this.add(this.deletebtn);
	}

	/**
	 * 显示添加用户信息对话框
	 */
	private void showUserDialog(){
		this.userInfoDiaglog = new UserInfoDialog(ERPConfig.getUSERINFO_DIALOG_CONFIG(),
				this.frame,this,true);
		this.userInfoDiaglog.setVisible(true);
	}
	
	/**
	 * 显示修改用户信息对话框
	 */
	private void showUpdateDialog() {
		UserVO vo = this.userTable.getSelectedVO();
		this.userInfoDiaglog = new UserInfoDialog(ERPConfig.getUSERINFO_DIALOG_CONFIG(),
				this.frame,this,false,vo);
		this.userInfoDiaglog.setVisible(true);
	}

	/**
	 * 添加用户
	 * @param vo
	 * @return
	 */
	public ResultMessage addUser(UserVO vo){
		ResultMessage result = this.userController.add(vo);
		if(result==ResultMessage.SUCCESS){
			this.userTable.addRow(vo);
		}
		return result;
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	public ResultMessage deleteUser(){
		UserVO vo = this.userTable.getSelectedVO();
		ResultMessage result = this.userController.delete(vo);
		if(result == ResultMessage.SUCCESS){
			this.userTable.deleteRow();
		}
		return result;
	}
	
	/**
	 * 修改用户
	 * @param vo
	 * @return
	 */
	public ResultMessage updateUser(UserVO vo){
		ResultMessage result = this.userController.update(vo);
		if(result == ResultMessage.SUCCESS){
			this.userTable.updateRow(vo);
		}
		return result;
	}
	
	/**
	 * 查找用户
	 * @param keyword
	 */
	public void findUser(String keyword){
		ArrayList<UserVO> list = userController.fuzzyFind(keyword);
		this.userTable.showFindTable(list);
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<UserVO> result = this.userController.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for(int i=0; i<result.size(); ++i){
			UserVO vo = result.get(i);
			strs.add(vo.id);
		}
		return strs;
	}

	public JFrame getHomeframe() {
		return frame;
	}
	
	public UserTablePane getUserTable(){
		return userTable;
	}
}
