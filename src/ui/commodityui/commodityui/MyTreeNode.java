package ui.commodityui.commodityui;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.jdesktop.swingx.treetable.TreeTableNode;

import vo.CategoryVO;
import vo.CommodityVO;

@SuppressWarnings({ "serial", "unchecked" })
public class MyTreeNode extends DefaultMutableTreeNode implements TreeTableNode{
	
	/**
	 * 结点ID
	 */
	private String id;

	/**
	 * 商品信息VO
	 */
	private CommodityVO commodityvo;
	
	/**
	 * 商品分类VO
	 */
	private CategoryVO categoryvo;
	
	/**
	 * 父节点
	 */
	private MyTreeNode myparent;
	
	/**
	 * 子节点列表
	 */
	private ArrayList<MyTreeNode> mychildren = new ArrayList<MyTreeNode>();
	
	public MyTreeNode(String id, CategoryVO catevo, CommodityVO comvo, MyTreeNode parent){
		this.id = id;
		this.commodityvo = comvo;
		this.categoryvo = catevo;
		this.myparent = parent;
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
	
	@Override
	public int getIndex(TreeNode child){
		for (int i = 0; i < this.getChildren().size(); i++) {
			if (this.getChildren().get(i) == child) {
				return i;
			}
		}
		return 0;
	}
	
	@Override
	public TreeTableNode getChildAt(int i){
		if(this.mychildren.size()>0){
			return this.mychildren.get(i);
		}else{
			return null;
		}
	}
	
	@Override
	public int getChildCount(){
		if(mychildren!=null){
			return mychildren.size();
		}
		return 0;
	}

	@Override
	public MyTreeNode getParent() {
		return myparent;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEditable(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValueAt(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	public void removeChild(MyTreeNode child){
		int index = this.getIndex(child);
		this.mychildren.remove(index);
	}
	
	public void addChild(MyTreeNode child){
		this.mychildren.add(child);
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
			if(this.mychildren.size()>0){
				for(int i=0; i<this.mychildren.size();++i){
					if(this.mychildren.get(i).isCategory()){
						return false;
					}
				}return true;
			}
			else return true;
		}
		return false;
	}

	public void setChildren(ArrayList<MyTreeNode> children) {
		this.mychildren = children;
	}
	
	public String getId() {
		return id;
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
		return mychildren;
	}
	
	public MyTreeNode findChild(String key){
		if(this.isCategory()||this.parent==null){
			for(int i=0; i<mychildren.size(); ++i){
				if(!mychildren.get(i).isCategory()){
					MyTreeNode node = mychildren.get(i);
					if((node.getCommodityvo().name+"-"+
							node.getCommodityvo().model).equals(key)){
						return node;
					}
				}
			}
		}
		return null;
	}



}
