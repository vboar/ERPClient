/**
 * 添加/修改商品信息对话框
 * @author JaneLDQ
 * @date 2014/11/30
 */
package ui.commodityui.commodityui;

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
import vo.CommodityVO;
import config.DialogConfig;

@SuppressWarnings("serial")
public class CommodityInfoDialog extends JDialog {
	
	private MyLabel nameTip;
	
	private MyTextField nameTxt;
	
	private MyTextField	modelTxt;
	
	private MyTextField purchasePriceTxt;
	
	private MyTextField salePriceTxt;
	
	private MyTextField warningNumTxt;
	
	private MyButton commit;
	
	private MyButton cancel;
	
	private CommodityTreePane tree;
	
	private DialogConfig cfg;
	
	private JFrame frame;
		
	private boolean isAdd;

	/**
	 * 添加商品信息构造函数
	 * @param cfg 配置对象
	 * @param frame 主窗口
	 * @param tree 商品树状面板
	 * @param isAdd 是否为添加商品
	 */
	public CommodityInfoDialog(DialogConfig cfg, JFrame frame, CommodityTreePane tree, boolean isAdd) {
		super(frame, true);
		this.frame = frame;
		this.isAdd = isAdd;
		this.tree = tree;
		this.cfg = cfg;
		
		// 设置标题、尺寸、坐标
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle(cfg.getTitle());
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX() + this.cfg.getX(), frame.getY()+ this.cfg.getY());
		
		// 初始化组件
		this.initComponent();
	}
	
	/**
	 * 修改商品信息构造函数
	 * @param cfg
	 * @param frame
	 * @param tree
	 * @param isAdd
	 * @param vo 待修改商品信息VO
	 */
	public CommodityInfoDialog(DialogConfig cfg, JFrame frame, CommodityTreePane tree, boolean isAdd,
			CommodityVO vo){
		this(cfg, frame,tree,isAdd);
		// 显示待修改商品原信息
		this.nameTxt.setText(vo.name);
		this.modelTxt.setText(vo.model);
		this.purchasePriceTxt.setText(Double.toString(vo.purchasePrice));
		this.salePriceTxt.setText(Double.toString(vo.salePrice));
		this.warningNumTxt.setText(Integer.toString(vo.warningNumber));
	}

	/**
	 * 初始化组件
	 */
	private void initComponent() {
		this.initButtons(this.cfg.getButtons());
		this.initLabels(cfg.getLabels());
		this.initTextfields(cfg.getTextFields());
	}
	
	/**
	 * 初始化标签
	 * @param ele 标签配置对象
	 */
	private void initLabels(Element ele){
		this.nameTip = new MyLabel(ele.element("nametip"));
		this.add(new MyLabel(ele.element("name")));
		this.add(new MyLabel(ele.element("model")));
		this.add(new MyLabel(ele.element("purchaseprice")));
		this.add(new MyLabel(ele.element("saleprice")));
		this.add(new MyLabel(ele.element("warningnum")));
		this.add(this.nameTip);
	}
	
	/**
	 * 初始化输入框
	 * @param ele 输入框配置对象
	 */
	private void initTextfields(Element ele){
		this.nameTxt = new MyTextField(ele.element("name"));
		this.modelTxt = new MyTextField(ele.element("model"));
		this.purchasePriceTxt = new MyTextField(ele.element("purchaseprice"));
		this.salePriceTxt = new MyTextField(ele.element("saleprice"));
		this.warningNumTxt = new MyTextField(ele.element("warningnum"));
		this.add(this.nameTxt);
		this.add(this.modelTxt );
		this.add(this.purchasePriceTxt);
		this.add(this.salePriceTxt);
		this.add(this.warningNumTxt);
	}
	
	/**
	 * 初始化按钮
	 * @param ele 按钮配置对象
	 */
	private void initButtons(Element ele){
		// 初始化提交按钮
		this.commit = new MyButton(ele.element("commit"));
		this.commit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					double purchasePrice = Double.parseDouble(purchasePriceTxt.getText());
					double salePrice = Double.parseDouble(salePriceTxt.getText());
					int warningNum = Integer.parseInt(warningNumTxt.getText());
					int result = MyOptionPane.showConfirmDialog(frame, "确认操作？",
							"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
					if(result==MyOptionPane.YES_OPTION){
						if(isAdd){
							tree.addCommodity(nameTxt.getText(),modelTxt.getText(),
									purchasePrice, salePrice,warningNum);
						}else{
							tree.updateCommodity(nameTxt.getText(),modelTxt.getText(),
									purchasePrice, salePrice,warningNum);
						}
					}
				}catch(NumberFormatException ex){
					MyOptionPane.showMessageDialog(frame, "请按正确格式输入数据！",
							"错误提示",MyOptionPane.ERROR_MESSAGE);
				}
			}		
		});
		this.add(this.commit);
		
		// 初始化取消按钮
		this.cancel = new MyButton(ele.element("cancel"));
		this.cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "确认取消？",
						"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
				if(result==MyOptionPane.YES_OPTION){
					CommodityInfoDialog.this.dispose();
				}
			}		
		});
		this.add(this.cancel);
	}

}
