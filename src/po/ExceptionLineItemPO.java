/**
 * 报溢报损单中商品PO类
 * @author Vboar
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;

public class ExceptionLineItemPO implements Serializable {

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
	 * 系统库存数量
	 */
	private int systemNumber;
	
	/**
	 * 实际库存数量
	 */
	private int actualNumber;
	
	/**
	 * 构造方法
	 * @param id
	 * @param name
	 * @param model
	 * @param systemNumber
	 * @param actualNumber
	 */
	public ExceptionLineItemPO(String id, String name, String model, 
			int systemNumber, int actualNumber) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.systemNumber = systemNumber;
		this.actualNumber = actualNumber;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}

	public int getSystemNumber() {
		return systemNumber;
	}

	public int getActualNumber() {
		return actualNumber;
	}
	
}
