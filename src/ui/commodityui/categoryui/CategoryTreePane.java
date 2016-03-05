/**
 * 商品分类树形面板
 * @author JaneLDQ
 * @date 2014/11/27
 */
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
import javax.swing.tree.TreeNode;
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

	/**
	 * 构造函数
	 * @param ele 配置对象
	 * @param frame 主窗口
	 */
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

	/**
	 * 初始化分类树
	 * @param rootname
	 */
	private void initTree(String rootname) {
		// 新建树
		this.root = new DefaultMutableTreeNode(rootname);
		this.dtm = new DefaultTreeModel(root);
		this.tree = new JTree(dtm);
		this.tree.setAutoscrolls(true);
		// 设置选择模式为单选
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		// 设置单击展开节点
		this.tree.setToggleClickCount(1);
		this.tree.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				// 获得当前选中路径
				TreePath path = tree.getPathForLocation(e.getX(), e.getY());
				if (path == null) 	return;
				tree.setSelectionPath(path);
				// 根节点只允许添加分类
				if(((DefaultMutableTreeNode)tree.getLastSelectedPathComponent()).isRoot()){
					popmenu.unableDelUpdItem();
				}else
					popmenu.setAllItenEnable(true);
				// 右键弹出菜单
				if (e.getButton() == 3) {
					popmenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		this.createTree(this.controller.show());
	}

	/**
	 * 删除分类信息
	 */
	public void delete(){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		int result = MyOptionPane.showConfirmDialog(frame, "确认删除？",
				"确认提示", MyOptionPane.YES_NO_OPTION, MyOptionPane.QUESTION_MESSAGE);
		if (result == MyOptionPane.YES_OPTION) {
			if (controller.delete((CategoryVO) node.getUserObject()) == ResultMessage.SUCCESS) {
				MyOptionPane.showMessageDialog(frame, "删除成功！");
				this.dtm.removeNodeFromParent(node);
				this.tree.updateUI();
			}else{
				MyOptionPane.showMessageDialog(frame, "该分类下存在子分类或商品，不可删除！");
			}
		}
		this.categoryInfo.dispose();
	}
	
	/**
	 * 更新分类信息
	 * @param string 更新后的分类名称
	 */
	public void updateCatagory(String string){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();	
		CategoryVO vo = (CategoryVO)node.getUserObject();
		String oldname = vo.name;
		vo.name = string;
		if(this.controller.update(vo)==ResultMessage.SUCCESS){
			//vo.name = string;
			MyOptionPane.showMessageDialog(frame, "修改成功！");
		}else{
			vo.name = oldname;
			MyOptionPane.showMessageDialog(frame, "修改失败！");
		}
		this.tree.updateUI();
		this.categoryInfo.dispose();
	}

	/**
	 * 添加分类
	 * @param name 新增分类名称
	 */
	public void addCategory(String name){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();	
		CategoryVO father;
		// 如果为根节点，设置父类为空
		if(node.isRoot())	father = null;	
		else	father = (CategoryVO)node.getUserObject();
		String fatherId = this.controller.createId(father==null?null:father.id);
		CategoryVO newChild = new CategoryVO( fatherId,name,0,father);
		// 调用控制器添加商品获得返回信息
		ResultMessage result = this.controller.add(newChild);
		switch(result){
		case SUCCESS:
			MyOptionPane.showMessageDialog(frame, "添加成功！");
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newChild);
			node.add(newNode);
			tree.scrollPathToVisible(new TreePath(newNode.getPath()));
			this.tree.updateUI();
			break;
		case EXIST:
			MyOptionPane.showMessageDialog(frame, "分类名称已存在！");
			break;
		case HAS_COMMODITY:
			MyOptionPane.showMessageDialog(frame, "分类下已有商品，无法添加子分类！");
			break;
		default:
			MyOptionPane.showMessageDialog(frame, "添加失败！");
			break;
		}
		// 关闭对话框
		this.categoryInfo.dispose();
	}
	
	/**
	 * 显示添加分类对话框
	 */
	public void showAddDialog() {
		this.categoryInfo = new CategoryInfoDialog(ERPConfig.getCATEGORYINFO_DIALOG_CONFIG(),frame,this);
		this.categoryInfo.setVisible(true);
	}
	
	/**
	 * 显示修改分类对话框
	 */
	public void showUpdDialog() {
		CategoryVO vo = (CategoryVO)((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject();
		this.categoryInfo = new CategoryInfoDialog(ERPConfig.getCATEGORYINFO_DIALOG_CONFIG(),frame,this,vo);
		this.categoryInfo.setVisible(true);
	}

	/**
	 * 展开节点（公共方法）
	 */
	public void expandNode(){
		this.expandAllNode(tree, new TreePath(root), true);
	}
	
	/**
	 * 展开所有节点（私有方法）
	 * @param tree 
	 * @param parent 
	 * @param expand
	 */
	private void expandAllNode(JTree tree, TreePath parent, boolean expand) {
	    TreeNode node = (TreeNode) parent.getLastPathComponent();
	    if (node.getChildCount() >= 0) {
	        for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
	            TreeNode n = (TreeNode) e.nextElement();
	            TreePath path = parent.pathByAddingChild(n);
	            expandAllNode(tree, path, expand);
	        }
	    }
	    if (expand) {
	        tree.expandPath(parent);
	    } else {
	        tree.collapsePath(parent);
	    }
	}
	
	/**
	 * 创建树
	 * @param list
	 */
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

	/**
	 * 插入一个结点
	 * @param node
	 * @param vo
	 */
	@SuppressWarnings("rawtypes")
	private void insertNode(DefaultMutableTreeNode node, CategoryVO vo) {
		// 子女为0，则直接添加
		if (node.getChildCount() == 0) {
			node.add(new DefaultMutableTreeNode(vo));
			this.tree.updateUI();
			return;
		} else {
			// 否则，遍历子女
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				DefaultMutableTreeNode nextNode = (DefaultMutableTreeNode) e.nextElement();
				String childId = ((CategoryVO) nextNode.getUserObject()).id;
				if ((childId.compareTo(vo.id) < 0)
						&& (childId.length() < vo.id.length())) {
					if (childId.compareTo(vo.id.substring(0, childId.length())) < 0) {
						continue;
					} else
						// 下一级分类
						insertNode(nextNode, vo);
				} else {
					// 同级分类
					((DefaultMutableTreeNode) nextNode.getParent()).add(new DefaultMutableTreeNode(vo));
					this.tree.updateUI();
					return;
				}
			}
		}
	}

	/**
	 * 检查节点是否已存在
	 * @param node
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private boolean checkNodes(DefaultMutableTreeNode node, CategoryVO vo) {
		// 如果结点内为ERP或者结点中的id与新增VO的id相同则已存在
		if ((!node.getUserObject().equals("ERP"))
				&& (((CategoryVO) node.getUserObject()).id).equals(vo.id)) {
			return true;
		}
		// 否则，子女数不为零则遍历子女
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
