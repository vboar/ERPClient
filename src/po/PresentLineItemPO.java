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
	 * 赠品数量
	 */
	private int number;
	
	/**
	 * 构造方法
	 * @param id
	 * @param number
	 */
	public PresentLineItemPO(String id, int number) {
		this.id = id;
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
	
}
