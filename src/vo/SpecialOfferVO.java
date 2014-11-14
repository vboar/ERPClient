/**
 * 捆绑商品包的促销策略vo类
 * @date 2014/10/25
 * @author chengcheng
 */
package vo;

import java.util.ArrayList;

public class SpecialOfferVO {
	/**
	 * id
	 */
	public String id;
	
	/**
	 * 商品列表
	 */
	public ArrayList<CombinationCommodityLineItemVO> commodityList;
	
	/**
	 * 总价
	 */
	public double total;
	
	/**
	 * 起始时间
	 */
	public String startTime;
	
	/**
	 * 间隔时间
	 */
	public String endTime;
	
	/**
	 * 是否生效
	 */
	public boolean valid;
	
	/**
	 * 
	 * @param CommodityList
	 * @param total
	 * @param startTime
	 * @param endTime
	 * @param valid 
	 */
	public SpecialOfferVO(String id,ArrayList<CombinationCommodityLineItemVO> commodityList
			,double total,String startTime,String endTime, boolean valid){
		this.id= id;
		this.commodityList=commodityList;
		this.total=total;
		this.startTime=startTime;
		this.endTime=endTime;
		this.valid=valid;
	}

}
