/**
 * Class:现金费单条目清单类
 * 
 * @author JaneLDQ
 * @date 2014/10/25
 * 
 */
package po;

import java.io.Serializable;

public class ClauseLineItemPO implements Serializable{

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 条目名称
	 */
	private String name;
	
	/**
	 * 金额
	 */
	private double account;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 构造方法
	 * @param name
	 * @param account
	 * @param remark
	 */
	public ClauseLineItemPO(String name, double account, String remark){
		this.name = name;
		this.account = account;
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public double getAccount() {
		return account;
	}

	public String getRemark() {
		return remark;
	}
	
}
