/**
 * 转账列表行VO类
 * @author JanelDQ
 * @date 2014/10/25
 */
package vo;

public class TransferLineItemVO {

	/**
	 * 账户名称
	 */
	public String name;

	/**
	 * 银行卡号
	 */
	public String bankAccount;
	
	/**
	 * 转账金额
	 */
	public double account;
	
	/**
	 * 备注
	 */
	public String remark;

	/**
	 * 构造方法
	 * @param name
	 * @param bankAccount
	 * @param account
	 * @param remark
	 */
	public TransferLineItemVO(String name, String bankAccount, double account, String remark) {
		super();
		this.name = name;
		this.bankAccount = bankAccount;
		this.account = account;
		this.remark = remark;
	}
	
	@Override
	public String toString(){
		return name+"-"+bankAccount+"-"+account+"-"+remark;
	}
	
}
