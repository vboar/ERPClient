/**
 * 现金费用单VO类
 * @author JanelDQ
 * @date 2014/10/25
 */
package vo;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

public class CashVO implements DocumentVO {
	
	/**
	 * 单据编号
	 */
	public String id;
	
	/**
	 * 操作员
	 */
	public String operator;
	
	/**
	 * 银行账户
	 */
	public String bankAccount;
	
	/**
	 * 条目清单
	 */
	public ArrayList<ClauseLineItemVO> clauseList;
	
	/**
	 * 总额
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
	 * 能否红冲
	 */
	public boolean canWriteOff;
	
	/**
	 * 单据类型
	 */
	public DocumentType documentType;
	
	/**
	 * 时间
	 */
	public String time;

	/**
	 * 构造方法
	 * @param id
	 * @param operator
	 * @param bankAccount
	 * @param time
	 * @param clauseList
	 * @param total
	 * @param approvalState
	 * @param isWriteOff
	 * @param canWriteOff
	 * @param documentType
	 */
	public CashVO(String id, String operator, String bankAccount,String time,
			ArrayList<ClauseLineItemVO> clauseList, double total,
			DocumentStatus approvalState,boolean isWriteOff, 
			boolean canWriteOff,DocumentType documentType) {
		super();
		this.id = id;
		this.operator = operator;
		this.bankAccount = bankAccount;
		this.clauseList = clauseList;
		this.total = total;
		this.approvalState = approvalState;
		this.documentType=documentType;
		this.isWriteOff=isWriteOff;
		this.canWriteOff=canWriteOff;
		this.time=time;
	}


	@Override
	public String getId() {
		return id;
	}

	@Override
	public DocumentStatus getStatus() {
		return approvalState;
	}

	@Override
	public DocumentType getType() {
		return documentType;
	}

	@Override
	public boolean isWriteoff() {
		return isWriteOff;
	}

	@Override
	public String getTime() {
		return time;
	}

	@Override
	public void setStatus(DocumentStatus status) {
		this.approvalState = status;
	}

	/**
	 * 将条目列表转换成String
	 * @return
	 */
	public String listToStr(){
		String str="";
		for(int i=0; i<this.clauseList.size()-1;++i){
			str = str + clauseList.get(i).toString() +",";
		}
		str = str + clauseList.get(clauseList.size()-1).toString();
		return str;
	}
}
