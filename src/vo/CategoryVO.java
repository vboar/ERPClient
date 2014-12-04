/**
 * 商品分类VO类
 * @author Vboar
 * @date 2014/10/25 
 */
package vo;

public class CategoryVO {

	/**
	 * 商品分类编号
	 */
	public String id;
	
	/**
	 * 名称
	 */
	public String name;
	
	/**
	 * 子分类和商品数量
	 */
	public int number;
	
	/**
	 * 父分类
	 */
	public CategoryVO father;
	
	/**
	 * 构造方法
	 * @param name
	 * @param number
	 */
	public CategoryVO(String id, String name, int number,CategoryVO father) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.father=father;
	}
		
	@Override
	public String toString(){
		return this.name;
	}
	
}
