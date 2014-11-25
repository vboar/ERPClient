/**
 * 赠品单中赠品列表PO类
 * @author Vboar
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;

public class PresentLineItemPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 赠品编号
	 */
	private String id;
	
	/**
	 * 赠品名称
	 */
	private String name;
	
	/**
	 * 赠品型号
	 */
	private String model;
	
	/**
	 * 赠品数量
	 */
	private int number;
	
	public PresentLineItemPO(String id, String name, String model, int number) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.number = number;
	}
	
	public String getId() {
		return id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}
	
}
