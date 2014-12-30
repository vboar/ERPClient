package ui.purchaseui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import ui.util.MyTextField;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.PurchaseVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.purchaseblservice.PurchaseBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class AutoCreatePurchaseReturnPanel extends JPanel {

	private PurchaseListPane tablepane;
	
	private MyLabel tip;
	
	private MyTextField findTxt;
	
	private MyButton create;
	
	private MyButton cancel;
	
	private MyButton find;

	private MyButton show;
	
	private PanelConfig cfg;
	
	private PurchaseReturnPanel panel;
	
	private JFrame frame;
	
	private PurchaseBLService controller;
	
	public AutoCreatePurchaseReturnPanel(JFrame frame,
			PurchaseReturnPanel panel) {
		this.frame = frame;
		this.panel = panel;
		this.controller = ControllerFactoryImpl.getInstance().getPurchaseReturnController();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置基本属性
		this.setBounds(cfg.getX(), cfg.getY(), cfg.getW(), cfg.getH());
		this.setLayout(null);
		this.initComponent();
		this.setVisible(true);
		this.repaint();
	}

	private void initComponent() {
		// 销售单列表
		this.tablepane = new PurchaseListPane(new TableConfig(this.cfg.getTablepane()),false,true);
		this.add(tablepane);
		// 提示标签
		this.tip = new MyLabel(cfg.getLabels().element("tip"));
		this.add(tip);
		this.create = new MyButton(cfg.getButtons().element("create"));
		this.create.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tablepane.isSelected()){
					MyOptionPane.showMessageDialog(frame, "请先选择一张销售单！");
				}else{
					int result = MyOptionPane.showConfirmDialog(frame, "确认创建此进货单创建退货单？",
							"确认提示",MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						// TODO 进货退货单创建 修改ID和类型
						PurchaseVO vo = tablepane.getSelectedVO();
						vo.id = controller.createId();
						vo.receiptType = DocumentType.PURCHASERETURN;
						vo.documentStatus = DocumentStatus.NONCHECKED;
						ResultMessage addresult = controller.add(vo);
						if(addresult==ResultMessage.SUCCESS){
							MyOptionPane.showMessageDialog(frame, "创建退货单成功！");
						}else{
							MyOptionPane.showMessageDialog(frame, "创建退货单失败");
						}
					}
					panel.showShow();
				}
			}
		});
		this.add(this.create);
		this.cancel = new MyButton(cfg.getButtons().element("cancel"));
		this.cancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.showShow();
			}
		});
		this.add(cancel);
		this.show = new MyButton(cfg.getButtons().element("show"));
		this.show.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tablepane.updateData();
			}
		});
		this.add(this.show);
		this.find = new MyButton(cfg.getButtons().element("find"));
		this.find.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				tablepane.showFindOne(controller.getById(findTxt.getText()));
			}
		});
		this.add(find);
		this.findTxt = new MyTextField(cfg.getTextFields().element("find"));
		this.findTxt.setText("请输入单据编号");
		this.findTxt.setForeground(new Color(120,120,120));
		this.findTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getSource()==findTxt){
					findTxt.setText("");
					findTxt.setForeground(new Color(40,40,40));
				}
			}		
		});
		this.add(findTxt);
	}

}
