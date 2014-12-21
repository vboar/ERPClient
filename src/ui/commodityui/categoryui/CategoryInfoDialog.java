package ui.commodityui.categoryui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.dom4j.Element;

import ui.util.EditDialog;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MyTextField;
import vo.CategoryVO;
import config.DialogConfig;

/**
 * 添加分类信息对话框
 * @author JaneLDQ
 * @date 2014/11/27
 */
@SuppressWarnings("serial")
public class CategoryInfoDialog extends EditDialog{
	
	private MyLabel nameTip;
	
	private MyTextField nameTxt;
	
	private MyButton commit;
	
	private MyButton cancel;

	private boolean isAdd;
	
	private CategoryTreePane tree;
	
	private DialogConfig cfg;
	
	private JFrame frame;
	
	/**
	 * 添加分类对话框
	 * @param cfg
	 * @param frame
	 * @param tree
	 */
	public CategoryInfoDialog(DialogConfig cfg,JFrame frame, CategoryTreePane tree){
		super(frame);
		this.frame = frame;
		this.cfg = cfg;
		this.tree = tree;
		this.isAdd = true;
		this.cfg = cfg;
		
		// 设置标题、尺寸、坐标及布局
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle(cfg.getTitle());
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX()+this.cfg.getX(), frame.getY()+this.cfg.getY());
		
		// 初始化组件
		this.initComponent();
	}
	
	/**
	 * 修改分类时所用构造函数
	 * @param categoryinfo_DIALOG_CONFIG 对话框配置对象
	 * @param homeframe 主窗口
	 * @param panel 调用对象的分类树面板
	 * @param vo 选择修改的分类VO
	 */
	public CategoryInfoDialog(DialogConfig categoryinfo_DIALOG_CONFIG,
			JFrame homeframe, CategoryTreePane panel,CategoryVO vo) {
		this(categoryinfo_DIALOG_CONFIG,homeframe,panel);
		this.nameTxt.setText(vo.name);
		this.isAdd = false;
	}
	
	/**
	 * 初始化组件
	 */
	public void initComponent(){
		// 初始化标签
		this.nameTip = new MyLabel(cfg.getLabels().element("nametip"));
		this.nameTip.setVisible(false);
		this.add(new MyLabel(cfg.getLabels().element("name")));
		this.add(this.nameTip);
		
		// 初始化输入框
		this.nameTxt = new MyTextField(cfg.getTextFields().element("name"));
		this.add(this.nameTxt);
		
		// 初始化按钮
		this.initButtons(cfg.getButtons());
	}

	/**
	 * 初始化按钮
	 * @param ele 配置对象
	 */
	public void initButtons(Element ele){
		// 初始化提交按钮
		this.commit = new MyButton(ele.element("commit"));
		this.commit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// 添加分类
				if(isAdd){
					int result = MyOptionPane.showConfirmDialog(frame, "确认添加？",
							"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);						
					if(result==MyOptionPane.YES_OPTION){
						tree.addCategory(nameTxt.getText());
					}
				}
				// 修改分类
				else{
					int result = MyOptionPane.showConfirmDialog(frame, "确认修改？",
							"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);						
					if(result==MyOptionPane.YES_OPTION){
						tree.updateCatagory(nameTxt.getText());
					}
				}
			}		
		});
		this.add(this.commit);
		
		// 初始化取消按钮
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
		this.add(this.cancel);
	}
}
