/**
 * 期初建账PO类
 * @author Vboar
 * @date 2014/10/26
 */
package po;

import java.io.Serializable;

public class InitialPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 编号
	 */
	private String id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 无参构造
	 */
	public InitialPO() {}
	
	public InitialPO(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
