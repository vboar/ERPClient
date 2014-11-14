/**
 * 总价折让vo
 * @date 2014/10/25
 * @author chengcheng
 */

package vo;

import java.util.ArrayList;

public class TotalGiftVO {
	/**
	 * id
	 */
	public String id;
	
	/**
	 * 总价
	 */
	public double total;
	
	/**
	 * 赠品的列表
	 */
	public ArrayList<CombinationCommodityLineItemVO> giftInfo;
	
	/**
	 * 价格折让
	 */
	public double discount;
	
	/**
	 * 代金券
	 */
	public double voucher;
	
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
	
	public TotalGiftVO(String id,double total,ArrayList<CombinationCommodityLineItemVO> giftInfo,
			double discount,double voucher,String startTime,String endTime,boolean valid){
		this.id=id;
		this.total=total;
		this.giftInfo=giftInfo;
		this.discount=discount;
		this.voucher=voucher;
		this.startTime=startTime;
		this.endTime=endTime;
		this.valid=valid;		
	}

}
