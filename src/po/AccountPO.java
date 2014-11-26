/**
 * 公司账户PO类
 * @author JaneLDQ
 * @date 2014/10/25
 */

package po;

import java.io.Serializable;

public class AccountPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 公司账户名称
	 */
	private String name;
	/**
	 * 银行卡号
	 */
	private String account;
	/**
	 * 余额
	 */
	private double balance;

	/**
	 * 构造方法
	 * @param name
	 * @param balance
	 */
	public AccountPO(String name, String account, double balance){
		this.name = name;
		this.account = account;
		this.balance = balance;		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
