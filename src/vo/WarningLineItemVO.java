/**
 * 报警单中商品PO类
 * @author Vboar
 * @date 2014/10/25
 */
package vo;

public class WarningLineItemVO {

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
	 * 库存数量
	 */
	public int stockNumber;
	
	/**
	 * 警戒数量
	 */
	public int warningNumber;

	/**
	 * 构造方法
	 * @param id
	 * @param name
	 * @param model
	 * @param stockNumber
	 * @param warningNumber
	 */
	public WarningLineItemVO(String id, String name, String model, int stockNumber, int warningNumber) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.stockNumber = stockNumber;
		this.warningNumber = warningNumber;
	}
	
}
