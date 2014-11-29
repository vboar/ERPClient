package ui.commodityui.commodityui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.dom4j.Element;
import org.jdesktop.swingx.JXTreeTable;

import ui.util.BasicOperation;
import ui.util.MyPopMenu;
import vo.CategoryCommodityVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.commodityblservice.CommodityBLService;

@SuppressWarnings("serial")
public class CommodityTreePane extends JPanel implements BasicOperation{
	
	private MyPopMenu popmenu;

	private JScrollPane jsp;

	private CommodityTreeTableModel treeTableModel;;
	
	private JXTreeTable treeTable;
	
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
		this.jsp = new JScrollPane();
		this.jsp.getViewport().add(this.treeTable);
		this.jsp.setBounds(0, 0, this.getWidth(), this.getHeight());	
	}

	@Override
	public void delete() {
		MyTreeNode node = (MyTreeNode)this.treeTable.getModel().getValueAt(this.treeTable.getSelectedRow(), 0);
		System.out.println(node.getId());
	}

	@Override
	public void showUpdDialog() {
		
	}

	@Override
	public void showAddDialog() {
		
	}
}
