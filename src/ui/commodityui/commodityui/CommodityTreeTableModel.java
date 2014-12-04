package ui.commodityui.commodityui;

import java.util.ArrayList;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

import vo.CategoryCommodityVO;

public class CommodityTreeTableModel extends DefaultTreeTableModel {

	private MyTreeNode root;
	
	private ArrayList<CategoryCommodityVO> list;

	private boolean isfound = false;
	
	private static int COLUMN_COUNT = 8;

	public CommodityTreeTableModel(ArrayList<CategoryCommodityVO> list) {
		this.list = list;
		this.createTree();
	}

	public MyTreeNode findNode(MyTreeNode node, String key){
		MyTreeNode findnode = null;
		if ((node.getCommodityvo()!= null) && (node.getCommodityvo().name
				+"-"+node.getCommodityvo().model).equals(key)) {
			this.isfound = true;
			findnode = node;
		}
		if (node.getChildCount() >= 0) {
		
			ArrayList<MyTreeNode> list = node.getChildren();
			for (MyTreeNode treeNode : list) {
				if(!this.isfound){
					findnode = findNode(treeNode,key);
				}
			}
		}
		return findnode;
	}
	
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "名称";
		case 1:
			return "型号";
		case 2:
			return "库存数量";
		case 3:
			return "进价";
		case 4:
			return "零售价";
		case 5:
			return "最近进价";
		case 6:
			return "最近零售价";
		case 7:
			return "警戒数量";
		default:
			return "unknown";
		}
	}

	@Override
	public Object getValueAt(Object node, int column) {
		MyTreeNode treenode = (MyTreeNode) node;
		if (treenode.isCategory()||treenode.isRoot()) {
			switch (column) {
			case 0:
				return treenode;
			default:
				return null;
			}
		} else {
			switch (column) {
			case 0:
				return treenode;
			case 1:
				return treenode.getCommodityvo().model;
			case 2:
				return treenode.getCommodityvo().number;
			case 3:
				return treenode.getCommodityvo().purchasePrice;
			case 4:
				return treenode.getCommodityvo().salePrice;
			case 5:
				return treenode.getCommodityvo().recentPurchasePrice;
			case 6:
				return treenode.getCommodityvo().recentSalePrice;
			case 7:
				return treenode.getCommodityvo().warningNumber;
			default:
				return null;
			}
		}
	}

	@Override
	public Object getChild(Object node, int index) {
		MyTreeNode treenode = (MyTreeNode) node;
		return treenode.getChildren().get(index);
	}

	@Override
	public int getChildCount(Object parent) {
		MyTreeNode treenode = (MyTreeNode) parent;
		return treenode.getChildren().size();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		MyTreeNode treenode = (MyTreeNode) parent;
		return treenode.getIndex((MyTreeNode)child);
	}

	public boolean isLeaf(Object node) {
		MyTreeNode treenode = (MyTreeNode) node;
		if (treenode.getChildren().size() > 0) {
			return false;
		}
		return true;
	}

	public TreeTableNode getRoot() {
		return root;
	}

	@Override
	public boolean isCellEditable(Object node, int column) {
		return false;
	}
	
	private void createTree() {
		boolean isExist = false;
		this.root = new MyTreeNode("ERP", null, null,null);
		if (list != null) {
			for (int i = 0; i < list.size(); ++i) {
				CategoryCommodityVO vo = list.get(i);
				isExist = this.checkNodes(root, vo);
				if (!isExist) {
					this.insertNode(this.root, vo);
				}
			}
		}
	}

	private void insertNode(MyTreeNode node, CategoryCommodityVO vo) {
		if (node.getChildCount() == 0) {
			node.getChildren().add(
					new MyTreeNode(vo.id, vo.Categoryvo, vo.commodityvo,node));
			return;
		} else {
			ArrayList<MyTreeNode> list = node.getChildren();
			for (MyTreeNode nextNode : list) {
				String childId = nextNode.getId();
				if ((childId.compareTo(vo.id) < 0)
						&& (childId.length() < vo.id.length())) {
					if (childId.compareTo(vo.id.substring(0, childId.length())) < 0) {
						continue;
					} else
						this.insertNode(nextNode, vo);
				} else {
					node.getChildren()
							.add(new MyTreeNode(vo.id, vo.Categoryvo,
									vo.commodityvo,node));
					return;
				}
			}
		}
	}

	private boolean checkNodes(MyTreeNode node, CategoryCommodityVO vo) {
		if ((node.getId() != null) && (node.getId().equals(vo.id))) {
			return true;
		}
		if (node.getChildCount() >= 0) {
			ArrayList<MyTreeNode> list = node.getChildren();
			for (MyTreeNode treeNode : list) {
				checkNodes(treeNode, vo);
			}
		}
		return false;
	}

	public void setIsfound(boolean isfound) {
		this.isfound = isfound;
	}
	
	
}
