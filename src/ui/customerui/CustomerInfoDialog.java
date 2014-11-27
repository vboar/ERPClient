/**
 * 添加/修改客户信息面板
 * @author Vboar
 * @date 2014/11/27
 */

package ui.customerui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyComboBox;
import ui.util.MyLabel;
import ui.util.MyTextField;
import vo.CustomerVO;
import config.InfoDialogConfig;

@SuppressWarnings("serial")
public class CustomerInfoDialog extends JDialog {

	private MyLabel categoryLabel;
	
	private MyLabel levelLabel;
	
	private MyLabel nameLabel;
	
	private MyLabel phoneNumberLabel;
	
	private MyLabel addressLabel;
	
	private MyLabel postalCodeLabel;
	
	private MyLabel emailLabel;
	
	private MyLabel creditLimitLabel;
	
	private MyLabel salesmanLabel;
	
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
		this.cfg = cfg;
		this.setTitle("客户信息");
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
		this.categoryLabel = new MyLabel(ele.element("category"));
		this.levelLabel = new MyLabel(ele.element("level"));
		this.nameLabel = new  MyLabel(ele.element("name"));
		this.phoneNumberLabel = new MyLabel(ele.element("phonenumber"));
		this.addressLabel = new MyLabel(ele.element("address"));
		this.postalCodeLabel = new MyLabel(ele.element("postalcode"));
		this.emailLabel = new MyLabel(ele.element("email"));
		this.creditLimitLabel = new MyLabel(ele.element("creditlimit"));
		this.salesmanLabel = new MyLabel(ele.element("salesman"));
		this.add(categoryLabel);
		this.add(levelLabel);
		this.add(nameLabel);
		this.add(phoneNumberLabel);
		this.add(addressLabel);
		this.add(postalCodeLabel);
		this.add(emailLabel);
		this.add(creditLimitLabel);
		this.add(salesmanLabel);
	}
	
	private void initButtons(Element ele){
		
		this.commit = new MyButton(ele.element("commit"));
		this.commit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
//				CustomerVO vo = new CustomerVO(null, );
			}
			
		});
		
		this.cancel = new MyButton(ele.element("cancel"));
		this.cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
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
		
	}
}
