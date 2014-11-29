package ui.commodityui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.dom4j.Element;

import ui.util.MyOptionPane;
import util.ResultMessage;
import vo.CategoryVO;
import businesslogic.commoditybl.CategoryController;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import config.ERPConfig;
import config.PanelConfig;


@SuppressWarnings("serial")
public class CategoryTreePane extends JPanel {

	private JTree tree;

	private DefaultMutableTreeNode root;

	private CategoryPopMenu popmenu;

	private JScrollPane jsp;

	private CategoryInfoDialog categoryInfo;
	
	private JFrame frame;
	
	private CategoryController controller;
	
	@SuppressWarnings("unused")
	private PanelConfig pcfg;

	public CategoryTreePane(Element ele, JFrame frame) {
		this.frame =frame;
		this.setBounds(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("width")),
				Integer.parseInt(ele.attributeValue("height")));
		this.controller = ControllerFactoryImpl.getInstance()
				.getCategoryController();
		this.initTree(ele.attributeValue("rootname"));
		this.jsp = new JScrollPane();
		this.jsp.getViewport().add(this.tree);
		this.setLayout(null);
		this.jsp.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.popmenu = new CategoryPopMenu(this);
		this.add(popmenu);
		this.add(this.jsp);
		this.setVisible(true);

	}

	private void initTree(String rootname) {
		this.root = new DefaultMutableTreeNode(rootname);
		this.tree = new JTree(this.root);
		this.tree.setAutoscrolls(true);
		this.tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.tree.setToggleClickCount(1);
		this.createTree(this.controller.show());
		this.tree.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				TreePath path = tree.getPathForLocation(e.getX(), e.getY());
				if (path == null) 	return;
				tree.setSelectionPath(path);
				if(((DefaultMutableTreeNode)tree.getLastSelectedPathComponent()).isRoot()){
					popmenu.enableItem(false);
				}else	
					popmenu.enableItem(true);
				if (e.getButton() == 3) {
					popmenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	public void deleteCategory(){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		int result = MyOptionPane.showConfirmDialog(null, "确认删除？",
				"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
		if (result == MyOptionPane.YES_OPTION) {
			if (controller.delete((CategoryVO) node.getUserObject()) == ResultMessage.SUCCESS) {
				MyOptionPane.showMessageDialog(null, "删除成功！");
			}else{
				MyOptionPane.showMessageDialog(null, "该分类下存在子分类或商品，不可删除！");
			}
		}
	}
	
	public void updateCatagory(){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		 if(!node.isRoot()){
			this.showUpdateCategoryDialog();
			 if (controller.update((CategoryVO) node.getUserObject()) == ResultMessage.SUCCESS) {
				 MyOptionPane.showMessageDialog(null, "修改成功！");
			 }
		 }
	}

	public void showAddCategoryDialog() {
		this.categoryInfo = new CategoryInfoDialog(ERPConfig.getCATEGORYINFO_DIALOG_CONFIG(),frame,this);
		this.categoryInfo.setVisible(true);
	}
	
	public void showUpdateCategoryDialog() {
		CategoryVO vo = (CategoryVO)((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject();
		this.categoryInfo = new CategoryInfoDialog(ERPConfig.getCATEGORYINFO_DIALOG_CONFIG(),frame,this,vo);
		this.categoryInfo.setVisible(true);
	}

	private void createTree(ArrayList<CategoryVO> list) {
		boolean isExist = false;
		for (int i = 0; i < list.size(); ++i) {
			CategoryVO vo = list.get(i);
			isExist = checkNodes(this.root, vo);
			if (!isExist) {
				this.insertNode(this.root, vo);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void insertNode(DefaultMutableTreeNode node, CategoryVO vo) {
		System.out.println(node.getChildCount());
		if (node.getChildCount() == 0) {
			node.add(new DefaultMutableTreeNode(vo));
			this.tree.updateUI();
			return;
		} else {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				DefaultMutableTreeNode nextNode = (DefaultMutableTreeNode) e.nextElement();
				String childId = ((CategoryVO) nextNode.getUserObject()).id;
				if ((childId.compareTo(vo.id) < 0)
						&& (childId.length() < vo.id.length())) {
					if (childId.compareTo(vo.id.substring(0, childId.length())) < 0) {
						continue;
					} else
						insertNode(nextNode, vo);
				} else {
					((DefaultMutableTreeNode) nextNode.getParent()).add(new DefaultMutableTreeNode(vo));
					this.tree.updateUI();
					return;
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private boolean checkNodes(DefaultMutableTreeNode node, CategoryVO vo) {
		if ((!node.getUserObject().equals("ERP"))
				&& (((CategoryVO) node.getUserObject()).id).equals(vo.id)) {
			return true;
		}
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				DefaultMutableTreeNode n = (DefaultMutableTreeNode) e
						.nextElement();
				checkNodes(n, vo);
			}
		}
		return false;
	}

}
