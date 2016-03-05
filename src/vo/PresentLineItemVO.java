/**
 * 赠品单中赠品列表VO类
 * @author Vboar
 * @date 2014/10/25
 */
package vo;

public class PresentLineItemVO {

	/**
	 * 赠品编号
	 */
	public String id;
	
	/**
	 * 赠品名称
	 */
	public String name;
	
	/**
	 * 赠品型号
	 */
	public String model;
	
	/**
	 * 赠品数量
	 */
	public int number;
	
	/**
	 * 构造方法
	 * @param id
	 * @param name
	 * @param model
	 * @param number
	 */
	public PresentLineItemVO(String id, String name, String model, int number) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.number = number;
	}
	
	@Override
	public String toString(){
		return name + "-" + model + "-" + number;
	}
}
