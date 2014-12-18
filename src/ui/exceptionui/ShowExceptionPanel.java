package ui.exceptionui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import ui.util.DocumentWriteoffAndCopy;
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

@SuppressWarnings("serial")
public class ShowExceptionPanel extends ExceptionDocumentPanel implements 
	AddExceptionLineItem,DocumentWriteoffAndCopy{

	private int type;
	
	private ExceptionVO vo;
	private ArrayList<ExceptionLineItemVO> commoditylist;
	
	private ExceptionBLService controller;
	
	/**
	 * 构造函数
	 * @param frame 主窗口
	 * @param panel 库存异常处理面板
	 */
	public ShowExceptionPanel(JFrame frame, ExceptionVO vo, int type, boolean isloss){
		super(frame);
		this.type = type;
		this.vo = vo;
		this.commoditylist = vo.list;
		this.cfg = ERPConfig.getHOMEFRAME_CONFIG().getConfigMap().get(this.getClass().getName());
		this.controller = isloss ? ControllerFactoryImpl.getInstance().getLossController()
				: ControllerFactoryImpl.getInstance().getOverflowController();
		this.bg = isloss ? this.cfg.getBg() : this.cfg.getBg1();	
		// 设置大小、坐标、布局
		this.setPreferredSize(new Dimension(cfg.getW(), cfg.getH()));
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
		this.documentId.setText(type==2? this.controller.createId() : vo.id);
		this.add(this.documentId);
		
		//初始化商品表格面板
		this.tablepane = new ExceptionTablePane(new TableConfig(this.cfg.getTablepane()));
		for(int i=0; i<this.commoditylist.size();++i){
			tablepane.addRow(commoditylist.get(i));
		}
		this.tablepane.updateUI();
		this.add(this.tablepane);
	}
	
	private void initButtons() {
		if(type!=1){
		// 添加商品按钮
		this.addBtn = new MyButton(cfg.getButtons().element("add"));
		this.addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddExceptionLineItemDialog(ShowExceptionPanel.this,frame);
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
		}
	}

	/**
	 * 检查单据是否填写完整
	 * @return
	 */
	@Override
	public boolean checkCompleted() {
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

	@Override
	public DocumentType getDocumentType() {
		return vo.type;
	}

	@Override
	public String getDocumentID() {
		return this.documentId.getText();
	}

	@Override
	public void createCopyDocument() {
		ExceptionVO vo = new ExceptionVO(this.documentId.getText(),null,this.commoditylist,
				DocumentStatus.NONCHECKED,DocumentType.LOSS,false);
		ResultMessage result = 	this.controller.create(vo);
		if(result==ResultMessage.SUCCESS){
			MyOptionPane.showMessageDialog(frame, "单据提交成功！");
			this.setVisible(false);
		}else{
			MyOptionPane.showMessageDialog(frame, "单据提交失败！");
		}
	}

}
