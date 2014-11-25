/**
 * 报警单中商品PO类
 * @author Vboar
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;

public class WarningLineItemPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品编号
	 */
	private String id;
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 商品型号
	 */
	private String model;
	
	/**
	 * 库存数量
	 */
	private int stockNumber;
	
	/**
	 * 警戒数量
	 */
	private int warningNumber;

	public WarningLineItemPO(String id, String name, String model, int stockNumber, int warningNumber) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.stockNumber = stockNumber;
		this.warningNumber = warningNumber;
	}
	
	public String getId() {
		return id;
	}

	public int getStockNumber() {
		return stockNumber;
	}

	public int getWarningNumber() {
		return warningNumber;
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}
	
	
}
