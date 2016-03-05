/**
 * 现金费用单PO类
 * @author JaneLDQ
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

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
	private String operatorId;
	
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
	private int documentStatus;
	
	/**
	 * 是否为红冲单据
	 */
	private boolean isWriteOff;
	
	/**
	 * 是否可红冲
	 */
	public boolean canWriteOff;
	
	/**
	 * 单据类型
	 */
	private int documentType;

	/**
	 * 构造方法
	 * @param ID
	 * @param time
	 * @param operatorId
	 * @param bankAccount
	 * @param clauseList
	 * @param total
	 * @param documentStatus
	 * @param isWriteOff
	 * @param documentType
	 */
	public CashPO(String ID,String time,String operatorId,String bankAccount,ArrayList<ClauseLineItemPO> clauseList,
			double total, int documentStatus, boolean isWriteOff,boolean canWriteOff, int documentType){
		this.id = ID;
		this.time = time;
		this.operatorId = operatorId;
		this.bankAccount = bankAccount;
		this.clauseList = clauseList;
		this.total = total;
		this.documentStatus = documentStatus;
		this.isWriteOff = isWriteOff;
		this.canWriteOff = canWriteOff;
		this.documentType = documentType;
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

	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public ArrayList<ClauseLineItemPO> getClauseList() {
		return clauseList;
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

	public boolean isCanWriteOff() {
		return canWriteOff;
	}

}
