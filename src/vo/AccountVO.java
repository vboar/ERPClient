/**
 * 账户VO类
 * @author JanelDQ
 * @date 2014/10/25
 */
package vo;

public class AccountVO {

	/**
	 * 账户名称
	 */
	public String name;
	
	/**
	 * 银行卡号
	 */
	public String account;
	
	/**
	 * 账户余额
	 */
	public double balance;

	/**
	 * 构造方法
	 * @param name
	 * @param account
	 * @param balance
	 */
	public AccountVO(String name, String account, double balance) {
		this.name = name;
		this.account = account;
		this.balance = balance;
	}

	/**
	 * 无参构造方法
	 */
	public AccountVO(){}
	
}
