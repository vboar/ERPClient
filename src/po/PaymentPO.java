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
	 * 客户姓名
	 */
	private String customerName;
	
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
	private int approvalStatus;
	
	/**
	 * 是否为红冲单据
	 */
	private boolean isWriteOff;
	
	/**
	 * 是否可红冲
	 */
	private boolean canWriteOff;
	
	/**
	 * 单据类型
	 */
	private int documentType;

	/**
	 * 构造方法
	 * @param id
	 * @param time
	 * @param customerId
	 * @param customerName
	 * @param operatorId
	 * @param transferList
	 * @param total
	 * @param approvalStatus
	 * @param isWriteOff
	 * @param documentType
	 */
	public PaymentPO(String id,String time,String customerId,String customerName,
			String operatorId,ArrayList<TransferLineItemPO> transferList,double total,
			int approvalStatus,boolean isWriteOff,boolean canWriteOff,int documentType){
		this.id = id;
		this.time = time;
		this.customerId = customerId;
		this.setCustomerName(customerName);
		this.operatorId = operatorId;
		this.transferList = transferList;
		this.total = total;
		this.approvalStatus = approvalStatus;
		this.isWriteOff = isWriteOff;
		this.canWriteOff = canWriteOff;
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

	public int getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus=approvalStatus;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public int getDocumentType() {
		return documentType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public boolean isCanWriteOff() {
		return canWriteOff;
	}
	
}
