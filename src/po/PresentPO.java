/**
 * 赠品单PO类
 * @author Vboar
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

public class PresentPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 赠品单编号
	 */
	private String id;
	
	/**
	 * 创建时间
	 */
	private String time;
	
	/**
	 * 客户编号
	 */
	private String customerId;
	
	/**
	 * 客户姓名
	 */
	private String cusomerName;
	
	/**
	 * 赠品列表
	 */
	private ArrayList<PresentLineItemPO> list;
	
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
	
	public PresentPO(String id, String time, String customerId, ArrayList<PresentLineItemPO> list,
			int documentStatus, int documentType, boolean isWriteoff) {
		this.id = id;
		this.time = time;
		this.customerId = customerId;
		this.list = list;
		this.documentStatus = documentStatus;
		this.documentType = documentType;
		this.isWriteoff = isWriteoff;
	}

	public ArrayList<PresentLineItemPO> getList() {
		return list;
	}

	public void setList(ArrayList<PresentLineItemPO> list) {
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

	public boolean isWriteoff() {
		return isWriteoff;
	}

	public String getCustomerId() {
		return customerId;
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

	public String getCusomerName() {
		return cusomerName;
	}

}
