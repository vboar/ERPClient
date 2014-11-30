package ui.commodityui.categoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.dom4j.Element;

import ui.util.BasicOperation;
import ui.util.MyOptionPane;
import ui.util.MyPopMenu;
import util.ResultMessage;
import vo.CategoryVO;
import businesslogic.controllerfactory.ControllerFactoryImpl;
import businesslogicservice.commodityblservice.CategoryBLService;
import config.ERPConfig;
import config.PanelConfig;


@SuppressWarnings("serial")
public class CategoryTreePane extends JPanel implements BasicOperation{

	private JTree tree;

	private DefaultMutableTreeNode root;

	private DefaultTreeModel dtm;
	
	private MyPopMenu popmenu;

	private JScrollPane jsp;

	private CategoryInfoDialog categoryInfo;
	
	private JFrame frame;
	
	private CategoryBLService controller;
	
	@SuppressWarnings("unused")
	private PanelConfig pcfg;

	public CategoryTreePane(Element ele, JFrame frame) {
		this.frame =frame;
		this.setBounds(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("y")),
				Integer.parseInt(ele.attributeValue("width")),
				Integer.parseInt(ele.attributeValue("height")));
		this.controller = ControllerFactoryImpl.getInstance()
				.getCategoryController();
		this.initTree(ele.attributeValue("rootname"));
		this.jsp = new JScrollPane();
		this.jsp.getViewport().add(this.tree);
		this.setLayout(null);
		this.jsp.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.popmenu = new MyPopMenu(this);
		this.add(popmenu);
		this.add(this.jsp);
		this.setVisible(true);

	}

	private void initTree(String rootname) {
		this.root = new DefaultMutableTreeNode(rootname);
		this.dtm = new DefaultTreeModel(root);
		this.tree = new JTree(dtm);
		this.tree.setAutoscrolls(true);
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.tree.setToggleClickCount(1);
		this.tree.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				TreePath path = tree.getPathForLocation(e.getX(), e.getY());
				if (path == null) 	return;
				tree.setSelectionPath(path);
				if(((DefaultMutableTreeNode)tree.getLastSelectedPathComponent()).isRoot()){
					popmenu.unableDelUpdItem();
				}else	
					popmenu.setAllItenEnable(true);
				if (e.getButton() == 3) {
					popmenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		this.createTree(this.controller.show());
	}

	public void delete(){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		int result = MyOptionPane.showConfirmDialog(null, "确认删除？",
				"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
		if (result == MyOptionPane.YES_OPTION) {
			if (controller.delete((CategoryVO) node.getUserObject()) == ResultMessage.SUCCESS) {
				MyOptionPane.showMessageDialog(null, "删除成功！");
				this.dtm.removeNodeFromParent(node);
				this.tree.updateUI();
			}else{
				MyOptionPane.showMessageDialog(null, "该分类下存在子分类或商品，不可删除！");
			}
		}
		this.categoryInfo.dispose();
	}
	
	public void updateCatagory(String string){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();	
		int result = MyOptionPane.showConfirmDialog(null, "确认修改？",
				"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
		if(result==MyOptionPane.YES_OPTION){
			CategoryVO vo = (CategoryVO)node.getUserObject();
			vo.name = string;
			if(this.controller.update(vo)==ResultMessage.SUCCESS){
				MyOptionPane.showMessageDialog(null, "修改成功！");
			}else{
				MyOptionPane.showMessageDialog(null, "修改失败！");
			}
			this.tree.updateUI();
			this.categoryInfo.dispose();
		}
	}

	public void addCategory(String name){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();	
		int result = MyOptionPane.showConfirmDialog(null, "确认添加？",
				"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
		if(result==MyOptionPane.YES_OPTION){
			CategoryVO father;
			if(node.isRoot()) father = null;
			else	father = (CategoryVO)node.getUserObject();
			CategoryVO newChild = new CategoryVO(name,0,father);
			if(this.controller.add(newChild)==ResultMessage.SUCCESS){
				MyOptionPane.showMessageDialog(null, "添加成功！");
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newChild);
				node.add(newNode);
				tree.scrollPathToVisible(new TreePath(newNode.getPath()));
				this.tree.updateUI();
			}else{
				MyOptionPane.showMessageDialog(null, "添加失败！");
			}
			this.categoryInfo.dispose();

		}
	}
	
	public void showAddDialog() {
		this.categoryInfo = new CategoryInfoDialog(ERPConfig.getCATEGORYINFO_DIALOG_CONFIG(),frame,this);
		this.categoryInfo.setVisible(true);
	}
	
	public void showUpdDialog() {
		CategoryVO vo = (CategoryVO)((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject();
		this.categoryInfo = new CategoryInfoDialog(ERPConfig.getCATEGORYINFO_DIALOG_CONFIG(),frame,this,vo);
		this.categoryInfo.setVisible(true);
	}

	private void createTree(ArrayList<CategoryVO> list) {
		boolean isExist = false;
		if(list!=null){
			for (int i = 0; i < list.size(); ++i) {
				CategoryVO vo = list.get(i);
				isExist = checkNodes(this.root, vo);
				if (!isExist) {
					this.insertNode(this.root, vo);
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void insertNode(DefaultMutableTreeNode node, CategoryVO vo) {
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
