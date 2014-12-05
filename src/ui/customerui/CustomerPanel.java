/**
 * 客户管理面板
 * @author Vboar
 * @date 2014/11/27
 */

package ui.customerui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import vo.CustomerVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.customerblservice.CustomerBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class CustomerPanel extends JPanel implements FuzzySearch {
	
	private MyButton addBtn;
	
	private MyButton deleteBtn;
	
	private MyButton updateBtn;
	
	private MyButton findBtn;
	
	private MyButton showBtn;
	
	private MyLabel customerList;
	
	private MySpecialTextField findBox;
	
	private CustomerTablePane customerTable;
	
	private CustomerInfoDialog customerInfoDialog;
	
	private Image bg;
	
	private JFrame frame;
	
	private PanelConfig pcfg;
	
	private CustomerBLService customerController;

	public CustomerPanel(JFrame frame) {
		this.frame = frame;
		customerController = ControllerFactoryImpl.getInstance().getCustomerController();
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
	
	/**
	 * 初始化面板元素
	 * @param cfg
	 */
	private void initComponent(PanelConfig cfg) {
		this.initButtons(cfg.getButtons());
		this.initLabels(cfg.getLabels());
		this.initFindComboBox(cfg.getTextFields());
		this.initTable(cfg.getTablepane());
	}
	
	private void initTable(Element tablepane) {
		this.customerTable = new CustomerTablePane(new TableConfig(tablepane), customerController);
		this.add(this.customerTable);
	}
	
	private void initLabels(Element labels) {
		this.customerList = new MyLabel(labels.element("customerlist"));
		this.add(new MyLabel(labels.element("title")));
		this.add(this.customerList);
	}
	
	private void initButtons(Element buttons) {
		this.initAddBtn(buttons.element("add"));
		this.initDeleteBtn(buttons.element("delete"));
		this.initUpdateBtn(buttons.element("update"));
		this.initFindBtn(buttons.element("find"));
		this.initShowBtn(buttons.element("show"));
		this.add(addBtn);
		this.add(deleteBtn);
		this.add(updateBtn);
		this.add(findBtn);
		this.add(showBtn);
	}
	
	private void initFindComboBox(Element textfields) {
		this.findBox = new MySpecialTextField(textfields.element("findinput"),this);
		this.add(findBox);
	}
	
	private void initAddBtn(Element add) {
		this.addBtn = new MyButton(add);
		this.addBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showAddDialog();
			}
		});
	}
	
	private void initDeleteBtn(Element delete) {
		this.deleteBtn = new MyButton(delete);
		this.deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(customerTable.isSelected()) {
					int result = MyOptionPane.showConfirmDialog(null, "确认删除该客户信息？","删除客户",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION) {
						ResultMessage delResult = deleteCustomer();
						if(delResult == ResultMessage.SUCCESS) {
							MyOptionPane.showMessageDialog(null, "删除成功！");
						} else {
							MyOptionPane.showMessageDialog(null, "删除失败！","提示信息", 
									MyOptionPane.ERROR_MESSAGE, null);
						}
					}
				} else {
					MyOptionPane.showMessageDialog(null, "请选择要删除的客户信息！");
				}
			}
			
		});
	}
	
	private void initUpdateBtn(Element update) {
		this.updateBtn = new MyButton(update);
		this.updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(customerTable.isSelected()) {
					showUpdateDialog();
				} else {
					MyOptionPane.showMessageDialog(null, "请选择要修改的客户信息！");
				}
			}
			
		});
	}
	
	private void initFindBtn(Element find) {
		this.findBtn = new MyButton(find);
		this.findBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				findCustomer(findBox.getText());
			}
			
		});
	}
	
	private void initShowBtn(Element show) {
		this.showBtn = new MyButton(show);
		this.showBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				customerTable.showFindTable(customerController.show());
			}
			
		});
	}
	
	private void showAddDialog(){
		this.customerInfoDialog = new CustomerInfoDialog(ERPConfig.getCUSTOMERINFO_DIALOG_CONFIG(),
				frame, this, true);
		this.customerInfoDialog.setVisible(true);
	}
	
	private void showUpdateDialog() {
		CustomerVO vo = this.customerTable.getSelectedVO();
		this.customerInfoDialog = new CustomerInfoDialog(ERPConfig.getCUSTOMERINFO_DIALOG_CONFIG(),
				frame, this, false, vo);
		this.customerInfoDialog.setVisible(true);
	}
	
	public ResultMessage addCustomer(CustomerVO vo) {
		ResultMessage result = this.customerController.add(vo);
		if(result == ResultMessage.SUCCESS) {
			this.customerTable.addRow(vo);
		}
		return result;
	}
	
	public ResultMessage deleteCustomer() {
		CustomerVO vo = this.customerTable.getSelectedVO();
		ResultMessage result = this.customerController.delete(vo);
		if(result == ResultMessage.SUCCESS) {
			this.customerTable.deleteRow();
		}
		return result;
	}
	
	public ResultMessage updateCustomer(CustomerVO vo) {
		ResultMessage result = this.customerController.update(vo);
		if(result == ResultMessage.SUCCESS) {
			this.customerTable.updateRow(vo);
		}
		return result;
	}
	
	public void findCustomer(String keyWord) {
		this.customerTable.showFindTable(customerController.fuzzyFind(keyWord));
	}

	public CustomerTablePane getCustomerTable() {
		return customerTable;
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public ArrayList<String> getFuzzyResult(String keyWord) {
		ArrayList<CustomerVO> result = this.customerController.fuzzyFind(keyWord);
		ArrayList<String> strs = new ArrayList<String>();
		for(int i = 0; i < result.size(); ++i){
			CustomerVO vo = result.get(i);
			strs.add(vo.id);
		}
		return strs;
	}
	
}
