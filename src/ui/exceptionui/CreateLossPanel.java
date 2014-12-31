package ui.exceptionui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyOptionPane;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.ExceptionLineItemVO;
import vo.ExceptionVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.exceptionblservice.ExceptionBLService;
import config.ERPConfig;
import config.TableConfig;

/**
 * 创建报损单面板
 * @author JanelDQ
 * @date 2014/12/5
 */
@SuppressWarnings("serial")
public class CreateLossPanel extends ExceptionDocumentPanel implements AddExceptionLineItem{
		
	private ExceptionPanel panel;
	
	private ArrayList<ExceptionLineItemVO> commoditylist;
	
	private ExceptionBLService controller;
	
	/**
	 * 构造函数
	 * @param frame 主窗口
	 * @param panel 库存异常处理面板
	 */
	public CreateLossPanel(JFrame frame,ExceptionPanel panel){
		super(frame);
		this.panel = panel;
		this.commoditylist = new ArrayList<ExceptionLineItemVO>();
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.controller = ControllerFactoryImpl.getInstance().getLossController();
		this.bg = this.cfg.getBg();	
		
		// 设置大小、坐标、布局
		this.setSize(cfg.getW(), cfg.getH());
		this.setLocation(cfg.getX(), cfg.getY());
		this.setLayout(null);
		
		//初始化组件
		this.initComponent();
		this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(bg, 0, 0, cfg.getW(), cfg.getH(), null);
	}
	
	/**
	 * 初始化组件
	 */
	protected void initComponent(){
		// 初始化按钮
		this.initButtons();
		
		// 初始化标签
		this.documentId = new MyLabel(cfg.getLabels().element("documentid"));
		this.documentId.setText(this.controller.createId());
		this.add(this.documentId);
		
		//初始化商品表格面板
		this.tablepane = new ExceptionTablePane(new TableConfig(this.cfg.getTablepane()));
		this.add(this.tablepane);
	}
	
	private void initButtons() {
		// 添加商品按钮
		this.addBtn = new MyButton(cfg.getButtons().element("add"));
		this.addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddExceptionLineItemDialog(CreateLossPanel.this,frame);
			}
			
		});
		this.add(this.addBtn);
		// 删除赠品按钮
		this.deleteBtn = new MyButton(cfg.getButtons().element("delete"));
		this.deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tablepane.isSelected()){
					int result = MyOptionPane.showConfirmDialog(frame, "确认删除该商品？","删除商品",
							MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
						delCommodity();
					}
				}else{
					MyOptionPane.showMessageDialog(frame, "请选择要删除的商品！");
				}
			}
			
		});
		this.add(this.deleteBtn);
		// 提交按钮
		this.commitBtn = new MyButton(cfg.getButtons().element("commit"));
		this.commitBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkCompleted()){
					int result = MyOptionPane.showConfirmDialog(frame, "确认创建？","确认提示",
							MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
					if(result == MyOptionPane.YES_OPTION){
							createException();
					}
				}else{
					MyOptionPane.showMessageDialog(frame, "请填入完整信息！");
				}
			}
		});
		// 取消按钮
		this.cancelBtn = new MyButton(cfg.getButtons().element("cancel"));
		this.cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = MyOptionPane.showConfirmDialog(frame, "确认取消当前编辑？","确认提示",
						MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
				if(result == MyOptionPane.YES_OPTION){
					panel.showExceptionList(true);
				}
			}
		});
		this.add(this.commitBtn);
		this.add(this.cancelBtn);
	}

	/**
	 * 创建报损单
	 */
	protected void createException() {
		ExceptionVO vo = new ExceptionVO(this.documentId.getText(),null,this.commoditylist,
				DocumentStatus.NONCHECKED,DocumentType.LOSS,false,true);
		ResultMessage result = 	this.controller.create(vo);
		if(result==ResultMessage.SUCCESS){
			MyOptionPane.showMessageDialog(frame, "单据提交成功！");
			this.setVisible(false);
			panel.showExceptionList(true);
		}else{
			MyOptionPane.showMessageDialog(frame, "单据提交失败！");
		}
	}

	/**
	 * 检查单据是否填写完整
	 * @return
	 */
	private boolean checkCompleted() {
		if(this.commoditylist.size()>0){
			return true;
		}
		return false;
	}

	/**
	 * 删除一条商品信息
	 */
	public void delCommodity(){
		this.commoditylist.remove(this.tablepane.getTable().getSelectedRow());
		this.tablepane.deleteRow();
	}
	
	/**
	 * 添加一条商品信息
	 */
	@Override
	public void addLineItem(ExceptionLineItemVO itemVO) {
		this.commoditylist.add(itemVO);
		this.tablepane.addRow(itemVO);
	}
}
