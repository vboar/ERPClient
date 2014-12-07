/**
 * 商品树表面板
 * @author JaneLDQ
 * @date 2014/11/30
 */
package ui.commodityui.commodityui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.tree.TreePath;

import org.dom4j.Element;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;

import ui.util.BasicOperation;
import ui.util.FrameUtil;
import ui.util.MyOptionPane;
import ui.util.MyPopMenu;
import util.ResultMessage;
import vo.CategoryCommodityVO;
import vo.CommodityVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.commodityblservice.CommodityBLService;
import config.ERPConfig;

@SuppressWarnings("serial")
public class CommodityTreePane extends JPanel implements BasicOperation{
	
	/**
	 * 右键菜单
	 */
	private MyPopMenu popmenu;

	/**
	 * 滚动条面板
	 */
	private JScrollPane jsp;

	/**
	 * 商品树表模型
	 */
	private CommodityTreeTableModel treeTableModel;;
	
	/**
	 * 商品树表
	 */
	private JXTreeTable treeTable;
	
	/**
	 * 添加商品对话框
	 */
	private CommodityInfoDialog infodialog;
	
	/**
	 * 主窗口
	 */
	private JFrame frame;
	
	/**
	 * 商品管理控制器
	 */
	private CommodityBLService controller;
	
	/**
	 * 商品信息VO列表
	 */
	ArrayList<CategoryCommodityVO> list;
	
	/**
	 * 树表是否初始属性
	 */
	private boolean isInit = true;
	
	/**
	 * 构造函数
	 * @param ele 配置对象
	 * @param frame 主窗口
	 */
	public CommodityTreePane(Element ele, JFrame frame) {
		// 获得商品管理控制器
		this.controller = ControllerFactoryImpl.getInstance().getCommodityController();
		// 获得主窗口引用
		this.frame =frame;
		// 设置面板属性
		this.setLayout(null);
		this.setBounds(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")),
				Integer.parseInt(ele.attributeValue("width")),
				Integer.parseInt(ele.attributeValue("height")));
		// 初始树表
		this.initTreeTable();
		// 初始弹出菜单
		this.popmenu = new MyPopMenu(this);
		// 添加组件
		this.add(popmenu);
		this.add(this.jsp);
		// 设置面板可见
		this.setVisible(true);

	}

	/**
	 * 初始树表
	 */
	private void initTreeTable() {
		// 获得商品信息
		this.list = this.controller.bigShow();
		// 根据数据创建树表模型
		this.treeTableModel = new CommodityTreeTableModel(list);
		// 创建树表
		this.treeTable = new JXTreeTable(this.treeTableModel);
		this.treeTable.setToggleClickCount(1);
		// 增加树表监听
		this.treeTable.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				TreePath path = treeTable.getPathForLocation(e.getX(), e.getY());
				MyTreeNode node = (MyTreeNode)treeTable.getModel().getValueAt(treeTable.getSelectedRow(), 0);
				if (path == null) return;
				if (node == null) return;
				if (e.getButton() == 3) {
					if(node.getParent()==null){
						popmenu.setVisible(false);
						MyOptionPane.showMessageDialog(null, "请选择商品分类或商品进行操作！");
					}else{
						popmenu.setVisible(true);
						if(node.isCategory()){
							if(node.isLastCategory()){
								popmenu.unableDelUpdItem();
							}else	popmenu.setAllItenEnable(false);;
						}else{
							popmenu.setAllItenEnable(true);
							popmenu.unableAddItem();
						}
						popmenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			}
		});
		this.treeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.treeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.treeTable.setDefaultRenderer(Object.class, new MyTreeTableRenderer());
		this.treeTable.setRootVisible(true);
		// 创建滚动条面板
		this.jsp = new JScrollPane();
		this.jsp.setBounds(0, 0, this.getWidth(), this.getHeight());	
		this.jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// 将树表添加到滚动条面板上
		this.jsp.getViewport().add(this.treeTable);
	}

	/**
	 * 删除商品
	 */
	@Override
	public void delete() {
		MyTreeNode node = (MyTreeNode)treeTable.getModel().getValueAt(treeTable.getSelectedRow(), 0);
		if(!node.isCategory()){
			int result = MyOptionPane.showConfirmDialog(null, "确认删除？","确认提示",
						MyOptionPane.YES_NO_OPTION,MyOptionPane.QUESTION_MESSAGE);
			if(result == MyOptionPane.YES_OPTION){
				if(this.controller.delete(node.getCommodityvo())==ResultMessage.SUCCESS){
					MyOptionPane.showMessageDialog(null, "删除成功！");
					node.getParent().removeChild(node);
					this.treeTable.updateUI();			
				}else{
					MyOptionPane.showMessageDialog(null, "删除失败！");
				}
			}
		}
	}

	/**
	 * 显示修改对话框
	 */
	@Override
	public void showUpdDialog() {
		MyTreeNode node = (MyTreeNode)treeTable.getModel().getValueAt(treeTable.getSelectedRow(), 0);
		CommodityVO vo = node.getCommodityvo();
		this.infodialog = new CommodityInfoDialog(ERPConfig.getCOMMODITYINFO_DIALOG_CONFIG(), 
				frame, this, false, vo);
		this.infodialog.setVisible(true);
	}

	/**
	 * 显示添加对话框
	 */
	@Override
	public void showAddDialog() {
		this.infodialog = new CommodityInfoDialog(ERPConfig.getCOMMODITYINFO_DIALOG_CONFIG(), 
				frame, this, true);
		this.infodialog.setVisible(true);
	}

	/**
	 * 添加商品
	 * @param name 商品名
	 * @param model 型号
	 * @param purchasePrice 默认进价
	 * @param salePrice 默认售价
	 * @param warningNum 警戒数量
	 */
	public void addCommodity(String name, String model, double purchasePrice, 
			double salePrice, int warningNum) {
		MyTreeNode node = (MyTreeNode)treeTable.getModel().getValueAt(treeTable.getSelectedRow(), 0);
		if(node.isCategory()){
			CommodityVO vo=new CommodityVO(controller.createId(node.getId()),
					name,model,0,purchasePrice,salePrice,0.0,0.0,
					warningNum,false,node.getCategoryvo());
			if(this.controller.add(vo)==ResultMessage.SUCCESS){
				node.addChild( new MyTreeNode(null,null,vo,node));
				this.treeTable.updateUI();
				this.infodialog.dispose();
				MyOptionPane.showMessageDialog(null, "添加成功！");
			}else{
				MyOptionPane.showMessageDialog(null, "添加失败！");
				this.treeTable.updateUI();
			}
		}
		
	}
	
	/**
	 * 修改商品
	 * @param name 商品名称
	 * @param model 商品型号
	 * @param purchasePrice 默认进价
	 * @param salePrice 默认售价
	 * @param warningNum 警戒数量
	 */
	public void updateCommodity(String name, String model, double purchasePrice, 
			double salePrice, int warningNum){
		MyTreeNode node = (MyTreeNode)treeTable.getModel().getValueAt(treeTable.getSelectedRow(), 0);
		if(!node.isCategory()){
			CommodityVO vo= node.getCommodityvo();
			vo.name = name;
			vo.model = model;
			vo.purchasePrice = purchasePrice;
			vo.salePrice = salePrice;
			vo.warningNumber = warningNum;
			if(this.controller.update(vo)==ResultMessage.SUCCESS){
				node.setCommodityvo(vo);
				this.infodialog.dispose();
				MyOptionPane.showMessageDialog(null, "修改成功！");
				this.treeTable.updateUI();
			}else{
				MyOptionPane.showMessageDialog(null, "修改失败！");
				this.treeTable.updateUI();
			}
		}
	}

	/**
	 * 查找商品
	 * @param key 关键字
	 */
	public void findCommodity(String key){
		this.treeTableModel.setIsfound(false);
		MyTreeNode node = this.treeTableModel.findNode((MyTreeNode)this.treeTableModel.getRoot(), key);
		if(node!=null){
			TreePath path = new TreePath(node.getPath());
			this.treeTable.scrollPathToVisible(path);
			int row = this.treeTable.getRowForPath(path);
			this.treeTable.setRowSelectionInterval(row,row);
			this.repaint();
		}
	}
	
	/**
	 * 树表渲染器
	 * @author JanelDQ
	 *
	 */
	private class MyTreeTableRenderer extends  DefaultTableRenderer{	
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if(isSelected){
				comp.setBackground(new Color(205,220,240));
				comp.setForeground(new Color(40,40,40));
			}
			if(isInit){
				FrameUtil.setTableColumnWidth(table, CommodityTreePane.this.getWidth(),40);
	            table.getColumnModel().getColumn(0).setMinWidth(200);
				isInit = false;
			}
			return comp;
		}
	}
}
