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
	
	private JFrame homeframe;
	
	private MySpecialTextField userfindbox;
	
	private PanelConfig pcfg;
	
	private UserBLService userController;
		
	public UserPanel(JFrame frame){
		this.homeframe = frame;
		this.userController = ControllerFactoryImpl.getInstance().getUserController();
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.bg = pcfg.getBg();
		this.initComponent(pcfg);
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, pcfg.getW(),pcfg.getH(),null);
	}
	
	private void initComponent(PanelConfig cfg) {
		this.initButtons(cfg.getButtons());
		this.initLabels(cfg.getLabels());
		this.initFindComboBox(cfg.getTextFields());
		this.initTable(cfg.getTablepane());
	}

	private void initTable(Element tablepane) {
		this.userTable = new UserTablePane(new TableConfig(tablepane), userController);
		this.add(this.userTable);

	}

	private void initFindComboBox(Element textfields) {
		this.userfindbox = new MySpecialTextField(textfields.element("findinput"),this);
		this.add(this.userfindbox);
		this.userfindbox.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					System.out.println("enter");
					findUser(userfindbox.getText());
				}
			}
		});
	}

	private void initLabels(Element labels) {
		this.userlist = new MyLabel(labels.element("userlist"));
		this.add(new MyLabel(labels.element("title")));
		this.add(this.userlist);
	}

	private void initButtons(Element buttons) {
		this.initAddBtn(buttons.element("add"));
		this.initDeleteBtn(buttons.element("delete"));
		this.initUpdateBtn(buttons.element("update"));
		this.initFindBtn(buttons.element("find"));
		this.initShowAllBtn(buttons.element("showall"));
	}
	
	private void initShowAllBtn(Element ele) {
		this.showAll = new MyButton(ele);
		this.showAll.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				userTable.showFindTable(userController.show());
				userTable.updateUI();
			}
		});
		this.add(this.showAll);
	}

	private void initFindBtn(Element find){
		this.findbtn = new MyButton(find);
		this.findbtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				findUser(userfindbox.getText());
			}
		});
		this.add(this.findbtn);
	}
	
	private void initAddBtn(Element add){
		this.addbtn = new MyButton(add);
		this.addbtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					showUserDialog();			
			}		
		});
		this.add(this.addbtn);
	}
	
	private void initDeleteBtn(Element delete){
		this.deletebtn = new MyButton(delete);
		this.deletebtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userTable.isSelected()){
					int result = MyOptionPane.showConfirmDialog(null, "确认删除该用户信息？","删除用户",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						ResultMessage delresult = deleteUser();
						if(delresult == ResultMessage.SUCCESS){
							MyOptionPane.showMessageDialog(null, "删除成功！");
						}else{
							MyOptionPane.showMessageDialog(null, "删除失败！","提示信息", 
									MyOptionPane.ERROR_MESSAGE, null);
						}
					}
				}else{
					MyOptionPane.showMessageDialog(null, "请选择要删除的用户信息！");
				}
			}
		});
		this.add(this.deletebtn);
	}
	
	private void initUpdateBtn(Element modify){
		this.updatebtn = new MyButton(modify);
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
	}
	
	private void showUserDialog(){
		this.userInfoDiaglog = new UserInfoDialog(ERPConfig.getUSERINFO_DIALOG_CONFIG(),
				this.homeframe,this,true);
		this.userInfoDiaglog.setVisible(true);
	}
	

	private void showUpdateDialog() {
		UserVO vo = this.userTable.getSelectedVO();
		this.userInfoDiaglog = new UserInfoDialog(ERPConfig.getUSERINFO_DIALOG_CONFIG(),
				this.homeframe,this,false,vo);
		this.userInfoDiaglog.setVisible(true);
	}

	public UserTablePane getUserTable(){
		return userTable;
	}
	
	public ResultMessage addUser(UserVO vo){
		ResultMessage result = this.userController.add(vo);
		if(result==ResultMessage.SUCCESS){
			this.userTable.addRow(vo);
		}
		return result;
	}
	
	public ResultMessage deleteUser(){
		UserVO vo = this.userTable.getSelectedVO();
		ResultMessage result = this.userController.delete(vo);
		if(result == ResultMessage.SUCCESS){
			this.userTable.deleteRow();
		}
		return result;
	}
	
	public ResultMessage updateUser(UserVO vo){
		ResultMessage result = this.userController.update(vo);
		if(result == ResultMessage.SUCCESS){
			this.userTable.updateRow(vo);
		}
		return result;
	}
	
	public void findUser(String keyword){
		System.out.println("keyword:"+keyword);
		ArrayList<UserVO> list = userController.fuzzyFind(keyword);
		System.out.println(list.size());
		this.userTable.showFindTable(list);
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyword) {
		ArrayList<UserVO> result = this.userController.fuzzyFind(keyword);
		ArrayList<String> strs = new ArrayList<String>();
		for(int i=0; i<result.size(); ++i){
			UserVO vo = result.get(i);
			System.out.println("get:"+vo.id);
			strs.add(vo.id);
		}
		return strs;
	}

	public JFrame getHomeframe() {
		return homeframe;
	}
	
}
