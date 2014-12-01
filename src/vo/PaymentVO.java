/**
 * 收付款单据VO类
 * @author JanelDQ
 * @date 2014/10/25
 */
package vo;

import util.DocumentStatus;
import util.DocumentType;

import java.util.ArrayList;

public class PaymentVO implements DocumentVO {
	/**
	 * 单据编号
	 */
	public String id;
	
	/**
	 * 创建时间
	 */
	public String time;
	
	/**
	 * 客户编号
	 */
	public String customerId;
	
	/**
	 * 客户名称
	 */
	public String customerName;
	
	/**
	 * 操作员
	 */
	public String operatorId;
	
	/**
	 * 转账列表
	 */
	public ArrayList<TransferLineItemVO> transferList;
	
	/**
	 * 总额汇总
	 */
	public double total;
	
	/**
	 * 审批状态
	 */
	public DocumentStatus approvalState;
	
	/**
	 * 是否为红冲单据
	 */
	public boolean isWriteOff;
	
	/**
	 * 单据类型
	 */
	public DocumentType documentType;

	/**
	 * 构造方法
	 * @param id
	 * @param customerId
	 * @param customerName
	 * @param operatorId
	 * @param transferList
	 * @param total
	 * @param approvalState
	 * @param documentType
	 */
	public PaymentVO(String id,String time, String customerId, String customerName,
			String operatorId, ArrayList<TransferLineItemVO> transferList,
			double total, DocumentStatus approvalState,boolean isWriteOff,
			DocumentType documentType) {
		super();
		this.id = id;
		this.time = time;
		this.customerId = customerId;
		this.customerName = customerName;
		this.operatorId = operatorId;
		this.transferList = transferList;
		this.total = total;
		this.approvalState = approvalState;
		this.isWriteOff = isWriteOff;
		this.documentType = documentType;
	}
	
	public PaymentVO(String id, double total){
		this.id = id;
		this.total = total;
	}
	
	public PaymentVO(){
		
	}
}
