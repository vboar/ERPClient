/**
 * 转账列表
 * @author JaneLDQ
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;

public class TransferLineItemPO implements Serializable{

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 银行账号
	 */
	private String bankAccount;
	
	/**
	 * 转账金额
	 */
	private double account;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 构造方法
	 * @param bankAccount
	 * @param account
	 * @param remark
	 */
	public TransferLineItemPO(String bankAccount, double account,String remark){
		this.bankAccount = bankAccount;
		this.account = account;
		this.remark = remark;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public double getAccount() {
		return account;
	}

	public String getRemark() {
		return remark;
	}
	

}
