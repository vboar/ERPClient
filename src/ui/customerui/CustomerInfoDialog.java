/**
 * 添加/修改客户信息面板
 * @author Vboar
 * @date 2014/11/27
 */

package ui.customerui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import vo.CustomerVO;
import config.InfoDialogConfig;

@SuppressWarnings("serial")
public class CustomerInfoDialog extends JDialog {
	
	private MyTextField nameTxt;
	
	private MyTextField phoneNumberTxt;
	
	private MyTextField addressTxt;

	private MyTextField postalCodeTxt;
	
	private MyTextField emailTxt;
	
	private MyTextField creditLimitTxt;
	
	private MyTextField salesmanTxt;
	
	private MyComboBox categoryBox;
	
	private MyComboBox levelBox;
	
	private MyButton commit;
	
	private MyButton cancel;

	private InfoDialogConfig cfg;
	
	public CustomerPanel panel;
	
	private boolean isAdd;
	
	public CustomerInfoDialog(InfoDialogConfig cfg, JFrame frame, CustomerPanel panel, boolean isAdd) {
		super(frame, true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.cfg = cfg;
		this.setTitle(cfg.getTitle());
		this.panel = panel;
		this.isAdd = isAdd;
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
		this.initComponent();
	}
	
	public CustomerInfoDialog(InfoDialogConfig cfg, JFrame frame, CustomerPanel panel, 
			boolean isAdd, CustomerVO vo) {
		this(cfg, frame, panel, isAdd);
		this.addressTxt.setText(vo.address);
		this.creditLimitTxt.setText(Double.toString(vo.creditLimit));
		this.emailTxt.setText(vo.email);
		this.nameTxt.setText(vo.name);
		this.phoneNumberTxt.setText(vo.phoneNumber);
		this.postalCodeTxt.setText(vo.postalCode);
		this.salesmanTxt.setText(vo.salesman);
		this.categoryBox.setSelectedIndex(vo.category);
		this.levelBox.setSelectedIndex(vo.level);
	}
	
	private void initComponent() {
		this.initLabels(this.cfg.getLabels());
		this.initButtons(this.cfg.getButtons());
		this.initTextFields(this.cfg.getTextFields());
		this.initComboBoxes(this.cfg.getComboboxes());
	}
	
	private void initLabels(Element ele) {
		this.add(new MyLabel(ele.element("category")));
		this.add(new MyLabel(ele.element("level")));
		this.add(new  MyLabel(ele.element("name")));
		this.add(new MyLabel(ele.element("phonenumber")));
		this.add(new MyLabel(ele.element("address")));
		this.add(new MyLabel(ele.element("postalcode")));
		this.add(new MyLabel(ele.element("email")));
		this.add(new MyLabel(ele.element("creditlimit")));
		this.add(new MyLabel(ele.element("salesman")));
	}
	
	private void initButtons(Element ele){
		
		this.commit = new MyButton(ele.element("commit"));
		this.commit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int category = categoryBox.getSelectedIndex();
				int level = levelBox.getSelectedIndex();
				String name = nameTxt.getText();
				String phoneNumber = phoneNumberTxt.getText();
				String address = addressTxt.getText();
				String postalCode = postalCodeTxt.getText();
				String email = emailTxt.getText();
				Double creditLimit = Double.parseDouble(creditLimitTxt.getText());
				String salesman = salesmanTxt.getText();
				CustomerVO vo = new CustomerVO(null, category, level, name, phoneNumber, address,
						postalCode, email, creditLimit, 0, 0, salesman, true);
				
				int result = MyOptionPane.showConfirmDialog(null, "确认提交？", "确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result == MyOptionPane.YES_OPTION){
					if(isAdd) {
						if(panel.addCustomer(vo) == ResultMessage.SUCCESS) {
							MyOptionPane.showMessageDialog(null, "添加成功！");
							dispose();
						} else {
							MyOptionPane.showMessageDialog(null, "填写信息错误，添加失败！");
						}
					} else {
						if(panel.updateCustomer(vo) == ResultMessage.SUCCESS) {
							MyOptionPane.showMessageDialog(null, "修改成功！");
							dispose();
						} else {
							MyOptionPane.showMessageDialog(null, "填写信息错误，修改失败！");
						}
					}
					
				}
			}
			
		});
		this.add(commit);
		
		this.cancel = new MyButton(ele.element("cancel"));
		this.cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int result = MyOptionPane.showConfirmDialog(null, "确认取消？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					CustomerInfoDialog.this.dispose();
				}
				
			}
			
		});
		this.add(cancel);
	}
	
	private void initTextFields(Element ele) {
		this.addressTxt = new MyTextField(ele.element("address"));
		this.creditLimitTxt = new MyTextField(ele.element("creditlimit"));
		this.emailTxt = new MyTextField(ele.element("email"));
		this.nameTxt = new MyTextField(ele.element("name"));
		this.phoneNumberTxt = new MyTextField(ele.element("phonenumber"));
		this.postalCodeTxt = new MyTextField(ele.element("postalcode"));
		this.salesmanTxt = new MyTextField(ele.element("salesman"));
		this.add(addressTxt);
		this.add(creditLimitTxt);
		this.add(emailTxt);
		this.add(nameTxt);
		this.add(phoneNumberTxt);
		this.add(postalCodeTxt);
		this.add(salesmanTxt);
	}
	
	private void initComboBoxes(Element ele) {
		
		this.categoryBox = new MyComboBox(ele.element("category"));
		this.categoryBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(categoryBox.getSelectedIndex() == 0) {
					levelBox.setEnabled(false);
				} else {
					levelBox.setEnabled(true);
				}
			}
			
		});
		
		this.levelBox = new MyComboBox(ele.element("level"));
		
		if(categoryBox.getSelectedIndex() == 0) {
			levelBox.setEnabled(false);
		}
		
		this.add(categoryBox);
		this.add(levelBox);
	}
}
