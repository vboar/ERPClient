/**
 * 组合商品类
 * @author chengcheng
 * @date 2014/10/25
 */

package po;

import java.io.Serializable;

public class CombinationCommodityLineItemPO implements Serializable{
	
	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 商品id
	 */
	private String commodityId;
	
	
	/**
	 * 商品数量
	 */
	private int commodityNumber;
	
	/**
	 * 
	 * @param commodityName
	 * @param commodityType
	 * @param commodityNumer
	 */
	public CombinationCommodityLineItemPO(String commodityId,
			int commodityNumber){
		this.commodityId=commodityId;
		this.commodityNumber=commodityNumber;
		
	}


	public String getCommodityId() {
		return commodityId;
	}


	public int getCommodityNumber() {
		return commodityNumber;
	}
	


}
