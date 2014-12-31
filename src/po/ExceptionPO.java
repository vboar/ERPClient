/**
 * 报溢报损单PO类
 * @author Vboar
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

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
	private int documentStatus;
	
	/**
	 * 单据类型
	 */
	private int documentType;
	
	/**
	 * 是否为红冲单据
	 */
	private boolean isWriteoff;
	
	/**
	 * 是否为红冲单据
	 */
	public boolean canWriteoff;
	
	/**
	 * 无参构造
	 */
	public ExceptionPO() {}

	/**
	 * 构造方法
	 * @param id
	 * @param time
	 * @param list
	 * @param documentStatus
	 * @param documentType
	 * @param isWriteoff
	 */
	public ExceptionPO(String id, String time, ArrayList<ExceptionLineItemPO> list,
			int documentStatus, int documentType, boolean isWriteoff, boolean canWriteoff) {
		this.id = id;
		this.time = time;
		this.list = list;
		this.documentStatus = documentStatus;
		this.documentType = documentType;
		this.isWriteoff = isWriteoff;
		this.canWriteoff = canWriteoff;
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

	public int getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(int documentStatus) {
		this.documentStatus = documentStatus;
	}

	public int getDocumentType() {
		return documentType;
	}

	public boolean isWriteoff() {
		return isWriteoff;
	}

	public boolean isCanWriteoff() {
		return canWriteoff;
	}

}
