/**
 * 现金费用单条目清单VO类
 * @author JanelDQ
 * @date 2014/10/25
 */
package vo;

public class ClauseLineItemVO {
	/**
	 * 条目名称
	 */
	public String name;
	
	/**
	 * 金额
	 */
	public double account;
	
	/**
	 * 备注
	 */
	public String remark;

	/**
	 * 构造方法
	 * @param name
	 * @param account
	 * @param remark
	 */
	public ClauseLineItemVO(String name, double account, String remark) {
		this.name = name;
		this.account = account;
		this.remark = remark;
	}
	
	@Override
	public String toString(){
		return name+"-"+account+"-"+remark;
	}
	

}
