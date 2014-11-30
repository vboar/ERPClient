package ui.commodityui.commodityui;

import java.util.ArrayList;

import vo.CategoryVO;
import vo.CommodityVO;

public class MyTreeNode {
	
	private String id;

	private CommodityVO commodityvo;
	
	private CategoryVO categoryvo;
	
	private MyTreeNode parent;
	
	private ArrayList<MyTreeNode> children = new ArrayList<MyTreeNode>();
	
	public MyTreeNode(String id, CategoryVO catevo, CommodityVO comvo, MyTreeNode parent){
		this.id = id;
		this.commodityvo = comvo;
		this.categoryvo = catevo;
		this.parent = parent;
	}
	
	public MyTreeNode findChild(String key){
		if(this.isCategory()){
			for(int i=0; i<children.size(); ++i){
				if(!children.get(i).isCategory()){
					MyTreeNode node = children.get(i);
					if((node.getCommodityvo().name+
							node.getCommodityvo().model).equals(key)){
						return node;
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		if(this.categoryvo!=null){
			return categoryvo.name;
		}else if(this.commodityvo!=null){
			return commodityvo.name;
		}
		return "ERP";
	}
	
	public void removeChild(MyTreeNode child){
		int index = this.getIndexOfChild(child);
		this.children.remove(index);
	}
	
	public void addChild(MyTreeNode child){
		this.children.add(child);
	}
	
	public int getIndexOfChild(MyTreeNode child){
		for (int i = 0; i < this.getChildren().size(); i++) {
			if (this.getChildren().get(i) == child) {
				return i;
			}
		}
		return 0;
	}
	
	public boolean isCategory(){
		if((commodityvo==null)&&(categoryvo!=null)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isLastCategory(){
		if(isCategory()){
			if(this.children.size()>0){
				for(int i=0; i<this.children.size();++i){
					if(this.children.get(i).isCategory()){
						return false;
					}
				}return true;
			}
			else return true;
		}
		return false;
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

	public MyTreeNode getParent() {
		return parent;
	}

}
