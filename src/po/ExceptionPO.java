/**
 * 报溢报损单PO类
 * @author Vboar
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

public class ExceptionPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 报溢报损单编号
	 */
	private String id;
	
	/**
	 * 创建时间
	 */
	private String time;
	
	/**
	 * 商品列表
	 */
	private ArrayList<ExceptionLineItemPO> list;
	
	/**
	 * 审批状态
	 */
	private DocumentStatus status;
	
	/**
	 * 单据类型
	 */
	private DocumentType type;
	
	/**
	 * 是否为红冲单据
	 */
	private boolean isWriteoff;
	
	/**
	 * 无参构造
	 */
	public ExceptionPO() {}
	
	/**
	 * 构造方法
	 * @param id
	 * @param time
	 * @param list
	 * @param status
	 * @param type
	 * @param isWriteoff
	 */
	public ExceptionPO(String id, String time, ArrayList<ExceptionLineItemPO> list,
			DocumentStatus status, DocumentType type, boolean isWriteoff) {
		this.id = id;
		this.time = time;
		this.list = list;
		this.status = status;
		this.type = type;
		this.isWriteoff = isWriteoff;
	}

	public DocumentStatus getStatus() {
		return status;
	}

	public void setStatus(DocumentStatus status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

	public ArrayList<ExceptionLineItemPO> getList() {
		return list;
	}

	public DocumentType getType() {
		return type;
	}

	public boolean isWriteoff() {
		return isWriteoff;
	}

}
