/**
 * 收付款单据VO类
 * @author JanelDQ
 * @date 2014/10/25
 */
package vo;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

public class PaymentVO implements DocumentVO {
	/**
	 * 单据编号
	 */
	public String id;
	
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
	public String operator;
	
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
	 * 单据类型
	 */
	public DocumentType documentType;

	/**
	 * 构造方法
	 * @param id
	 * @param customerId
	 * @param customerName
	 * @param operator
	 * @param transferList
	 * @param total
	 * @param approvalState
	 * @param documentType
	 */
	public PaymentVO(String id, String customerId, String customerName,
			String operator, ArrayList<TransferLineItemVO> transferList,
			double total, DocumentStatus approvalState,
			DocumentType documentType) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.customerName = customerName;
		this.operator = operator;
		this.transferList = transferList;
		this.total = total;
		this.approvalState = approvalState;
		this.documentType = documentType;
	}
	
}
