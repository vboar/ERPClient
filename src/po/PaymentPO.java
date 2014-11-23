/**
 * 收付款单PO类
 * @author JaneLDQ
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

public class PaymentPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 单据编号
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
	 * 操作员
	 */
	private String operatorId;
	
	/**
	 * 转账列表
	 */
	private ArrayList<TransferLineItemPO> transferList;
	
	/**
	 * 总额汇总
	 */
	private double total;
	
	/**
	 * 审批状态
	 */
	private int documentStatus;
	
	/**
	 * 是否为红冲单据
	 */
	private boolean isWriteOff;
	
	/**
	 * 单据类型
	 */
	private int documentType;
	
	/**
	 * 构造方法
	 * @param id
	 * @param customerId
	 * @param customerName
	 * @param operator
	 * @param transferList
	 * @param total
	 * @param approvalState
	 * @param isWriteOff
	 * @param documentType
	 */
	public PaymentPO(String id,String time,String customerId,String operatorId,
			ArrayList<TransferLineItemPO> transferList,double total,
			int documentStatus,boolean isWriteOff,int documentType){
		this.id = id;
		this.time = time;
		this.customerId = customerId;
		this.operatorId = operatorId;
		this.transferList = transferList;
		this.total = total;
		this.documentStatus = documentStatus;
		this.isWriteOff = isWriteOff;
		this.documentType = documentType;
	}

	public String getId() {
		return id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public ArrayList<TransferLineItemPO> getTransferList() {
		return transferList;
	}

	public double getTotal() {
		return total;
	}

	public boolean isWriteOff() {
		return isWriteOff;
	}

	public String getTime() {
		return time;
	}

	public int getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(int documentStatus) {
		this.documentStatus = documentStatus;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public int getDocumentType() {
		return documentType;
	}
	
}
