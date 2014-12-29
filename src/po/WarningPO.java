/**
 * 报警单PO类
 * @author Vboar
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

public class WarningPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 报警单编号
	 */
	private String id;
	
	/**
	 * 创建时间
	 */
	private String time;
	
	/**
	 * 商品列表
	 */
	private ArrayList<WarningLineItemPO> list;
	
	/**
	 * 单据类型
	 */
	private int documentType;

	/**
	 * 构造方法
	 * @param id
	 * @param time
	 * @param list
	 * @param documentType
	 */
	public WarningPO(String id, String time, ArrayList<WarningLineItemPO> list, int documentType) {
		this.id = id;
		this.time = time;
		this.list = list;
		this.documentType = documentType;
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

	public ArrayList<WarningLineItemPO> getList() {
		return list;
	}

	public int getDocumentType() {
		return documentType;
	}
	
}
