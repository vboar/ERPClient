/**
 * 添加分类信息对话框
 * @author JaneLDQ
 * @date 2014/11/27
 */
package ui.commodityui.categoryui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.dom4j.Element;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MyTextField;
import vo.CategoryVO;
import config.InfoDialogConfig;

@SuppressWarnings("serial")
public class CategoryInfoDialog extends JDialog{
	
	private MyLabel nameTip;
	
	private MyTextField nameTxt;
	
	private MyButton commit;
	
	private MyButton cancel;

	private boolean isAdd;
	
	private CategoryTreePane tree;
	
	private InfoDialogConfig cfg;
	
	public CategoryInfoDialog(InfoDialogConfig cfg,JFrame frame, CategoryTreePane tree){
		super(frame,true);
		this.cfg = cfg;
		this.tree = tree;
		this.isAdd = true;
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle(cfg.getTitle());
		this.cfg = cfg;
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());	
		this.initComponent();
	}
	
	public CategoryInfoDialog(InfoDialogConfig categoryinfo_DIALOG_CONFIG,
			JFrame homeframe, CategoryTreePane panel,CategoryVO vo) {
		this(categoryinfo_DIALOG_CONFIG,homeframe,panel);
		this.nameTxt.setText(vo.name);
		this.isAdd = false;
	}
	
	public void initComponent(){
		this.initLabels(cfg.getLabels());
		this.initTextfields(cfg.getTextFields());
		this.initButtons(cfg.getButtons());
	}
	
	public void initLabels(Element ele){
		this.nameTip = new MyLabel(ele.element("nametip"));
		this.nameTip.setVisible(false);
		this.add(new MyLabel(ele.element("name")));
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
				if(isAdd){
					int result = MyOptionPane.showConfirmDialog(null, "确认添加？",
							"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);						
					if(result==MyOptionPane.YES_OPTION){
						tree.addCategory(nameTxt.getText());
					}
				}else{
					int result = MyOptionPane.showConfirmDialog(null, "确认修改？",
							"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);						
					if(result==MyOptionPane.YES_OPTION){
						tree.updateCatagory(nameTxt.getText());
					}
				}
			}		
		});
		this.cancel = new MyButton(ele.element("cancel"));
		this.cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(null, "确认取消？",
						"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					CategoryInfoDialog.this.dispose();
				}
			}		
		});
		this.add(this.commit);
		this.add(this.cancel);
	}
}
