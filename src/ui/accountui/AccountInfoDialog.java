package ui.accountui;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyTextField;
import vo.AccountVO;
import config.InfoDialogConfig;

@SuppressWarnings("serial")
public class AccountInfoDialog extends JDialog {

	private MyButton submit;
	
	private MyButton cancel;
	
	private MyLabel accountLabel;
	
	private MyLabel nameLabel;
	
	private MyTextField accountTxt;
	
	private MyTextField nameTxt;
	
	private InfoDialogConfig cfg;
	
	private AccountPanel panel;
	
	private boolean isAdd;
	
	public AccountInfoDialog(InfoDialogConfig cfg, JFrame frame, AccountPanel panel, boolean isAdd) {
		super(frame, true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.cfg = cfg;
		this.setTitle("账户信息");
		this.panel = panel;
		this.isAdd = isAdd;
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
		this.initComponent();
	}
	
	public AccountInfoDialog(InfoDialogConfig cfg, JFrame frame, AccountPanel panel, boolean isAdd, 
			AccountVO vo) {
		this(cfg, frame, panel, isAdd);
		this.accountTxt.setText(vo.account);
		this.nameTxt.setText(vo.name);
	}
	
	private void initComponent() {
		this.initLabels(this.cfg.getLabels());
		this.initButtons(this.cfg.getButtons());
		this.initTextFields(this.cfg.getTextFields());
	}
	
	private void initTextFields(Element textFields) {
		// TODO Auto-generated method stub
		
	}

	private void initButtons(Element buttons) {
		// TODO Auto-generated method stub
		
	}

	private void initLabels(Element ele) {
		this.accountLabel = new MyLabel(ele.element("account"));
		this.nameLabel = new MyLabel(ele.element("name"));
		this.add(accountLabel);
		this.add(nameLabel);
	}
	
}
