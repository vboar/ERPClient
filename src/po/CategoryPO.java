/**
 * 商品分类PO类
 * @author oneoneO
 * @date 2014/10/25 
 */
package po;

import java.io.Serializable;

public class CategoryPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品分类编号
	 */
	private String id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 子分类和商品数量
	 */
	private int number;
	
	/**
	 * 构造方法
	 * @param id
	 * @param name
	 * @param number
	 */
	public CategoryPO(String id,String name,int number){
		this.id=id;
		this.name=name;
		this.number=number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
