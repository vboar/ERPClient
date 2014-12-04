package ui.exceptionui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.exceptionblservice.ExceptionBLService;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import vo.ExceptionLineItemVO;
import config.ERPConfig;
import config.PanelConfig;

@SuppressWarnings("serial")
public class CreateLossPanel extends JPanel{

	private MyButton addBtn;
	
	private MyButton deleteBtn;
	
	private MyButton commitBtn;
	
	private MyButton cancelBtn;
	
	private MyLabel documentId;
	
	private ExceptionTablePane tablepane;
	
	private PanelConfig pcfg;
	
	private Image bg;
	
	private ArrayList<ExceptionLineItemVO> commoditylist;
	
	private ExceptionPanel panel;
	
	private JFrame frame;
	
	private ExceptionBLService controller;
	
	public CreateLossPanel(JFrame frame,ExceptionPanel panel){
		this.frame = frame;
		this.panel = panel;
		this.commoditylist = new ArrayList<ExceptionLineItemVO>();
		this.controller = ControllerFactoryImpl.getInstance().getLossController();
		this.pcfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		//this.bg = this.pcfg.getBg();
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		this.setLayout(null);
		this.initComponent();
		this.setVisible(true);
	}
	
//	@Override
//	public void paintComponent(Graphics g){
//		g.drawImage(bg, 0, 0, pcfg.getW(), pcfg.getH(), null);
//	}
//	
	private void initComponent() {
		this.documentId = new MyLabel(pcfg.getLabels().element("documentid"));
		this.documentId.setText("00001");
		this.add(this.documentId);
		// 添加商品按钮
		this.addBtn = new MyButton(pcfg.getButtons().element("add"));
		this.addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddExceptionLineItemDialog(CreateLossPanel.this,frame);
			}
			
		});
		this.add(this.addBtn);
		// 删除赠品按钮
		this.deleteBtn = new MyButton(pcfg.getButtons().element("delete"));
		this.deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tablepane.isSelected()){
					int result = MyOptionPane.showConfirmDialog(null, "确认删除该商品？","删除商品",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						delCommodity();
					}
				}else{
					MyOptionPane.showMessageDialog(null, "请选择要删除的商品！");
				}
			}
			
		});
		this.add(this.deleteBtn);
		// 提交按钮
		this.commitBtn = new MyButton(pcfg.getButtons().element("commit"));
		this.commitBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkCompleted()){
					int result = MyOptionPane.showConfirmDialog(null, "确认创建？","确认提示",
							MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
							createLoss();
					}
				}else{
					MyOptionPane.showMessageDialog(null, "请填入完整信息！");
				}
			}
		});
		// 取消按钮
		this.cancelBtn = new MyButton(pcfg.getButtons().element("cancel"));
		this.add(this.commitBtn);
		this.add(this.cancelBtn);
	}

	protected void createLoss() {
		
	}

	private boolean checkCompleted() {
		if(this.commoditylist.size()>0){
			return true;
		}
		return false;
	}

	public void delCommodity(){
		this.commoditylist.remove(this.tablepane.getTable().getSelectedRow());
		this.tablepane.deleteRow();
	}
	
	public void addCommodity(ExceptionLineItemVO vo){
		this.commoditylist.add(vo);
		this.tablepane.addRow(vo);
	}
}
