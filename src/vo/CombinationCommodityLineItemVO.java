/**
 * 组合商品类
 * @author chengcheng
 * @date 2014/10/25
 */
package vo;

public class CombinationCommodityLineItemVO {
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
	public CombinationCommodityLineItemVO(String commodityId,
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
