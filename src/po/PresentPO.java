/**
 * 赠品单PO类
 * @author Vboar
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

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
	private String clientId;
	
	/**
	 * 客户姓名
	 */
	private String clientName;
	
	/**
	 * 赠品列表
	 */
	private ArrayList<PresentLineItemPO> list;
	
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
	 * 构造方法
	 * @param id
	 * @param time
	 * @param clientId
	 * @param clientName
	 * @param list
	 * @param status
	 * @param type
	 * @param isWriteoff
	 */
	public PresentPO(String id, String time, String clientId,
			String clientName, ArrayList<PresentLineItemPO> list,
			DocumentStatus status, DocumentType type, boolean isWriteoff) {
		this.id = id;
		this.time = time;
		this.clientId = clientId;
		this.clientName = clientName;
		this.list = list;
		this.status = status;
		this.type = type;
		this.isWriteoff = isWriteoff;
	}

	public ArrayList<PresentLineItemPO> getList() {
		return list;
	}

	public void setList(ArrayList<PresentLineItemPO> list) {
		this.list = list;
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

	public DocumentType getType() {
		return type;
	}

	public boolean isWriteoff() {
		return isWriteoff;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientName() {
		return clientName;
	}

}
