package ui.saleui;

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
import vo.SaleVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.saleblservice.SaleBLService;
import config.ERPConfig;
import config.PanelConfig;
import config.TableConfig;

@SuppressWarnings("serial")
public class AutoCreateSaleReturnPanel extends JPanel{

	private SaleListPane tablepane;
	
	private MyLabel tip;
	
	private MyTextField findTxt;
	
	private MyButton create;
	
	private MyButton cancel;
	
	private MyButton find;

	private MyButton show;
	
	private PanelConfig cfg;
	
	private SaleReturnPanel panel;
	
	private JFrame frame;
	
	private SaleBLService controller;
	
	public AutoCreateSaleReturnPanel(JFrame frame, SaleReturnPanel panel){
    	this.frame = frame;
    	this.panel = panel;
    	this.controller = ControllerFactoryImpl.getInstance().getSaleReturnController();
    	this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		// 设置面板基础属性
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		this.setVisible(true);
		this.repaint();
	}

	private void initComponent() {
		// 销售单列表
		this.tablepane = new SaleListPane(new TableConfig(this.cfg.getTablepane()),false,true);
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
					int result = MyOptionPane.showConfirmDialog(frame, "确认根据此销售单创建退货单？",
							"确认提示",MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						// TODO 销售退货单 只修改ID和类型
						SaleVO vo = tablepane.getSelectedVO();
						vo.id = controller.createId();
						vo.receiptType = DocumentType.SALERETURN;
						vo.approvalState = DocumentStatus.NONCHECKED;
						// 交给bl操作
						ResultMessage addresult = controller.add(vo);
						if(addresult==ResultMessage.SUCCESS){
							MyOptionPane.showMessageDialog(frame, "创建退货单成功！");
						}else{
							MyOptionPane.showMessageDialog(frame, "创建退货单失败");
						}
						panel.showShow();
					}
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
				//TODO 销售单精确查找
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
