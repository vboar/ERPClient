package ui.commodityui.commodityui;

import java.util.ArrayList;

import vo.CategoryVO;
import vo.CommodityVO;

public class MyTreeNode {
	
	private String id;

	private CommodityVO commodityvo;
	
	private CategoryVO categoryvo;
	
	private ArrayList<MyTreeNode> children = new ArrayList<MyTreeNode>();
	
	public MyTreeNode(String id, CategoryVO catevo, CommodityVO comvo){
		this.id = id;
		this.commodityvo = comvo;
		this.categoryvo = catevo;
	}
	
	public CommodityVO getCommodityvo() {
		return commodityvo;
	}

	public void setCommodityvo(CommodityVO commodityvo) {
		this.commodityvo = commodityvo;
	}

	public CategoryVO getCategoryvo() {
		return categoryvo;
	}

	public void setCategoryvo(CategoryVO categoryvo) {
		this.categoryvo = categoryvo;
	}

	public ArrayList<MyTreeNode> getChildren() {
		return children;
	}
	
	public int getChildrenCount(){
		return children.size();
	}

	public void setChildren(ArrayList<MyTreeNode> children) {
		this.children = children;
	}
	
	public String getId() {
		return id;
	}

	public boolean isCategory(){
		if((commodityvo==null)&&(categoryvo!=null)){
			return true;
		}else{
			return false;
		}
	}
	
}
