/**
 * 收付款单PO类
 * @author JaneLDQ
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

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
	 * 客户名称
	 */
	private String customerName;
	
	/**
	 * 操作员
	 */
	private String operator;
	
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
	private DocumentStatus approvalState;
	
	/**
	 * 是否为红冲单据
	 */
	private boolean isWriteOff;
	
	/**
	 * 单据类型
	 */
	private DocumentType documentType;
	
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
	public PaymentPO(String id,String time,String customerId,String customerName,
			String operator,ArrayList<TransferLineItemPO> transferList,double total,
			DocumentStatus approvalState,boolean isWriteOff,DocumentType documentType){
		this.id = id;
		this.time = time;
		this.customerId = customerId;
		this.customerName = customerName;
		this.operator = operator;
		this.transferList = transferList;
		this.total = total;
		this.approvalState = approvalState;
		this.isWriteOff = isWriteOff;
		this.documentType = documentType;
	}

	public DocumentStatus getApprovalState() {
		return approvalState;
	}

	public void setApprovalState(DocumentStatus approvalState) {
		this.approvalState = approvalState;
	}

	public String getId() {
		return id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getOperator() {
		return operator;
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

	public DocumentType getDocumentType() {
		return documentType;
	}

	public String getTime() {
		return time;
	}
	
}
