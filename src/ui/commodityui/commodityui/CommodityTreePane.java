package ui.commodityui.commodityui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.tree.TreePath;

import org.dom4j.Element;
import org.jdesktop.swingx.JXTreeTable;

import config.ERPConfig;
import ui.util.BasicOperation;
import ui.util.MyOptionPane;
import ui.util.MyPopMenu;
import util.ResultMessage;
import vo.CategoryCommodityVO;
import vo.CommodityVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.commodityblservice.CommodityBLService;

@SuppressWarnings("serial")
public class CommodityTreePane extends JPanel implements BasicOperation{
	
	private MyPopMenu popmenu;

	private JScrollPane jsp;

	private CommodityTreeTableModel treeTableModel;;
	
	private JXTreeTable treeTable;
	
	private CommodityInfoDialog infodialog;
	
	private JFrame frame;
	
	private CommodityBLService controller;
	
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

	private void initTreeTable() {
		ArrayList<CategoryCommodityVO> list = this.controller.bigShow();
		this.treeTableModel = new CommodityTreeTableModel(list);
		this.treeTable = new JXTreeTable(this.treeTableModel);
		this.treeTable.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				TreePath path = treeTable.getPathForLocation(e.getX(), e.getY());
				MyTreeNode node = (MyTreeNode)treeTable.getModel().getValueAt(treeTable.getSelectedRow(), 0);
				if (path == null) 	return;
				if (e.getButton() == 3) {
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
		});
		this.jsp = new JScrollPane();
		this.jsp.getViewport().add(this.treeTable);
		this.jsp.setBounds(0, 0, this.getWidth(), this.getHeight());	
	}

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
					MyOptionPane.showConfirmDialog(null, "删除失败！");
				}
			}
		}
	}

	@Override
	public void showUpdDialog() {
		MyTreeNode node = (MyTreeNode)treeTable.getModel().getValueAt(treeTable.getSelectedRow(), 0);
		CommodityVO vo = node.getCommodityvo();
		this.infodialog = new CommodityInfoDialog(ERPConfig.getCOMMODITYINFO_DIALOG_CONFIG(), 
				frame, this, true,vo);
		this.infodialog.setVisible(true);
	}

	@Override
	public void showAddDialog() {
		this.infodialog = new CommodityInfoDialog(ERPConfig.getCOMMODITYINFO_DIALOG_CONFIG(), frame, this, true);
		this.infodialog.setVisible(true);
	}

	public void addCommodity(String name, String model, double purchasePrice, 
			double salePrice, int warningNum) {
		MyTreeNode node = (MyTreeNode)treeTable.getModel().getValueAt(treeTable.getSelectedRow(), 0);
		if(node.isCategory()){
			CommodityVO vo=new CommodityVO(null,name,model,0,purchasePrice,salePrice,0.0,0.0,
					warningNum,false,node.getCategoryvo());
			if(this.controller.add(vo)==ResultMessage.SUCCESS){
				// 新增的节点中id为空
				node.addChild(new MyTreeNode(null,node.getCategoryvo(),vo,node));
			}
		}
		
	}
	
	public void updateCommodity(String name, String model, double purchasePrice, 
			double salePrice, int warningNum){
		
	}
}
