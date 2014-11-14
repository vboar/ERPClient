/**
 * 现金费用单PO类
 * @author JaneLDQ
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

public class CashPO implements Serializable {

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
	 * 操作员
	 */
	private String operator;
	
	/**
	 * 银行账户
	 */
	private String bankAccount;
	
	/**
	 * 条目清单
	 */
	private ArrayList<ClauseLineItemPO> clauseList;
	
	/**
	 * 总额
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
	 * @param ID
	 * @param operator
	 * @param bankAccount
	 * @param clauseList
	 * @param approvalState
	 * @param isWriteOff
	 * @param documentType
	 */
	public CashPO(String ID,String time,String operator,String bankAccount,ArrayList<ClauseLineItemPO> clauseList,
			DocumentStatus approvalState, boolean isWriteOff,DocumentType documentType){
		this.id = ID;
		this.time = time;
		this.operator = operator;
		this.bankAccount = bankAccount;
		this.clauseList = clauseList;
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

	public String getOperator() {
		return operator;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public ArrayList<ClauseLineItemPO> getClauseList() {
		return clauseList;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public boolean isWriteOff() {
		return isWriteOff;
	}

	public String getId() {
		return id;
	}

	public double getTotal() {
		return total;
	}

	public String getTime() {
		return time;
	}

}
