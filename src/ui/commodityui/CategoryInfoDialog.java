package ui.commodityui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyTextField;
import vo.CategoryVO;
import config.InfoDialogConfig;

@SuppressWarnings("serial")
public class CategoryInfoDialog extends JDialog{

	private MyLabel nameLab;
	
	private MyLabel nameTip;
	
	private MyTextField nameTxt;
	
	private MyButton commit;
	
	private MyButton cancel;

	private InfoDialogConfig cfg;
	
	public CategoryInfoDialog(InfoDialogConfig cfg,JFrame frame, CategoryTreePane panel){
		super(frame,true);
		this.cfg = cfg;
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle("用户信息");
		this.cfg = cfg;
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());	
	}
	
	public CategoryInfoDialog(InfoDialogConfig userinfo_DIALOG_CONFIG,
			JFrame homeframe, CategoryTreePane panel,CategoryVO vo) {
		this(userinfo_DIALOG_CONFIG,homeframe,panel);
		this.nameTxt.setText(vo.name);
	}
	
	public void initComponent(Element ele){
		this.initComponent(ele.element("lables"));
		this.initTextfields(ele.element("textfield"));
		this.initButtons(ele.element("buttons"));
	}
	
	public void initLabels(Element ele){
		this.nameLab = new MyLabel(ele.element("name"));
		this.nameTip = new MyLabel(ele.element("nametip"));
		this.add(this.nameLab);
		this.add(this.nameTip);
	}
	
	public void initTextfields(Element ele){
		this.nameTxt = new MyTextField(ele.element("name"));
		this.add(this.nameTxt);
	}
	
	public void initButtons(Element ele){
		this.commit = new MyButton(ele.element("commit"));
		this.commit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}		
		});
		this.cancel = new MyButton(ele.element("cancel"));
		this.add(this.commit);
		this.add(this.cancel);
	}
}
