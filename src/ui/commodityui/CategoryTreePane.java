package ui.commodityui;

import java.util.Enumeration;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.dom4j.Element;

import vo.UserVO;
import businesslogic.commoditybl.CategoryController;

@SuppressWarnings("serial")
public class CategoryTreePane extends JPanel {

	private JTree categoryTree;

	private JScrollPane jsp;

	private CategoryController controller;
	
	public CategoryTreePane(Element ele, CategoryController controller) {
		this.setBounds(Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("x")),
				Integer.parseInt(ele.attributeValue("width")),
				Integer.parseInt(ele.attributeValue("height")));
		this.controller = controller;
		this.initTree(ele.attributeValue("rootname"));
		
	}
	
	private void initTree(String rootname){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootname);
		this.categoryTree = new JTree(root);
		this.categoryTree.setAutoscrolls(true);
		this.categoryTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.categoryTree.setToggleClickCount(1);
	}
	
	private void showTree(){
		
	}

	public void checkExistNode(UserVO vo){
		
	}
	
	public void visitAllNodes(TreeNode node) {
	       // node is visited exactly once
	      // this.categoryTree.process(node);
	   
	       if (node.getChildCount() >= 0) {
	           for (Enumeration e=node.children(); e.hasMoreElements(); ) {
	               TreeNode n = (TreeNode)e.nextElement();
	               visitAllNodes(n);
	           }
	       }
	   }
	
}
