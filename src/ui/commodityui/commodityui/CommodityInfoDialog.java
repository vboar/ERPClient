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
import config.InfoDialogConfig;

@SuppressWarnings("serial")
public class CommodityInfoDialog extends JDialog {
	
	private boolean isAdd;
	
	private MyLabel nameTip;
	
	private MyTextField nameTxt;
	
	private MyTextField	modelTxt;
	
	private MyTextField purchasePriceTxt;
	
	private MyTextField salePriceTxt;
	
	private MyTextField warningNumTxt;
	
	private MyButton commit;
	
	private MyButton cancel;
	
	private CommodityTreePane tree;
	
	private InfoDialogConfig cfg;

	public CommodityInfoDialog(InfoDialogConfig cfg, JFrame frame, CommodityTreePane tree, boolean isAdd) {
		super(frame, true);
		((JComponent) this.getContentPane()).setOpaque(true);
		this.setTitle(cfg.getTitle());
		this.isAdd = isAdd;
		this.tree = tree;
		this.cfg = cfg;
		this.setSize(this.cfg.getW(), this.cfg.getH());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(frame.getX() + this.cfg.getX(), frame.getY()+ this.cfg.getY());
		this.initComponent();
	}
	
	public CommodityInfoDialog(InfoDialogConfig cfg, JFrame frame, CommodityTreePane tree, boolean isAdd,
			CommodityVO vo){
		this(cfg, frame,tree,isAdd);
		this.nameTxt.setText(vo.name);
		this.modelTxt.setText(vo.model);
		this.purchasePriceTxt.setText(Double.toString(vo.purchasePrice));
		this.salePriceTxt.setText(Double.toString(vo.salePrice));
		this.warningNumTxt.setText(Integer.toString(vo.warningNumber));
	}

	private void initComponent() {
		this.initButtons(this.cfg.getButtons());
		this.initLabels(cfg.getLabels());
		this.initButtons(cfg.getTextFields());
	}
	
	public void initLabels(Element ele){
		this.nameTip = new MyLabel(ele.element("nametip"));
		this.add(new MyLabel(ele.element("name")));
		this.add(new MyLabel(ele.element("model")));
		this.add(new MyLabel(ele.element("purchaseprice")));
		this.add(new MyLabel(ele.element("saleprice")));
		this.add(new MyLabel(ele.element("warningnum")));
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
			int result = MyOptionPane.showConfirmDialog(null, "确认添加？",
					"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
			if(result==MyOptionPane.YES_OPTION){
				if(isAdd){
					tree.addCommodity(nameTxt.getText(),modelTxt.getText(),
							Double.parseDouble(purchasePriceTxt.getText()),
							Double.parseDouble(salePriceTxt.getText()),
							Integer.parseInt(warningNumTxt.getText()));
				}else{
					tree.updateCommodity(nameTxt.getText(),modelTxt.getText(),
							Double.parseDouble(purchasePriceTxt.getText()),
							Double.parseDouble(salePriceTxt.getText()),
							Integer.parseInt(warningNumTxt.getText()));
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
					CommodityInfoDialog.this.dispose();
				}
			}		
		});
		this.add(this.commit);
		this.add(this.cancel);
	}


}
