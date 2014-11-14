/**
 * 筛选条件VO类
 * @author JanelDQ
 * @date 2014/10/25
 */
package vo;

import util.DocumentType;

public class RequirementVO {

	/**
	 * 起始时间
	 */
	public String time1 = null;
	
	/**
	 * 截止时间
	 */
	public String time2 = null;
	
	/**
	 * 商品编号
	 */
	public String commodityId = null;
	
	/**
	 * 客户编号
	 */
	public String customerId = null;
	
	/**
	 * 业务员
	 */
	public String salesman = null;
	
	/**
	 * 操作员
	 */
	public String operator = null;
	/**
	 * 仓库
	 */
	public String storage = null;
	
	/**
	 * 单据类型
	 */
	public DocumentType type = null;
	
	/**
	 * 构造方法
	 * @param time1
	 * @param time2
	 * @param commodityId
	 * @param customerId
	 * @param salesman
	 * @param storage
	 */
	public RequirementVO(String time1,String time2, String commodityId, 
			String customerId,String salesman, String storage){
		this.time1 = time1;
		this.time2 = time2;
		this.commodityId = commodityId;
		this.customerId = customerId;
		this.salesman = salesman;
		this.storage = storage;
	}
	
	public RequirementVO(String time1,String time2, DocumentType type,
			String customerId,String salesman, String storage, String operator){
		this.time1 = time1;
		this.time2 = time2;
		this.type = type;
		this.customerId = customerId;
		this.salesman = salesman;
		this.storage = storage;
		this.operator = operator;
	}
}
