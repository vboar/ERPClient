/**
 * 报溢报损单中商品VO类
 * @author Vboar
 * @date 2014/10/25
 */
package vo;

public class ExceptionLineItemVO {

	/**
	 * 商品编号
	 */
	public String id;
	
	/**
	 * 商品名称
	 */
	public String name;
	
	/**
	 * 商品型号
	 */
	public String model;
	
	/**
	 * 系统库存数量
	 */
	public int systemNumber;
	
	/**
	 * 实际库存数量
	 */
	public int actualNumber;
	
	/**
	 * 构造方法
	 * @param id
	 * @param name
	 * @param model
	 * @param systemNumber
	 * @param actualNumber
	 */
	public ExceptionLineItemVO(String id, String name, String model, 
			int systemNumber, int actualNumber) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.systemNumber = systemNumber;
		this.actualNumber = actualNumber;
	}
	
	public String toString(){
		return  name+"-"+model+"-System"+systemNumber+"-Actual"+actualNumber;
	}
	
}
