/**
 * 添加/修改客户信息面板
 * @author Vboar
 * @date 2014/11/27
 */

package ui.customerui;

import javax.swing.JDialog;
import javax.swing.JFrame;

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
	
	
	
}
