/**
 * 根据vip等级指定的促销策略vo类
 * @date 2014/10/25
 * @author chengcheng
 */
package vo;

import java.util.ArrayList;

public class CustomerGiftVO {
	
	/**
	 * id
	 */
	public String id;
	
	/**
	 * 客户级别
	 */
	public int vip;
	
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
	
	/**
	 * 
	 * @param VIP
	 * @param giftName
	 * @param giftModel
	 * @param giftNumber
	 * @param discount
	 * @param voucher
	 * @param startTime
	 * @param endTime
	 * @param valid
	 */
	public CustomerGiftVO(String id,int VIP,ArrayList<CombinationCommodityLineItemVO> giftInfo
			,double discount,double voucher,String startTime,String endTime,boolean valid){
		this.id=id;
		this.vip=VIP;
		this.giftInfo=giftInfo;
		this.discount=discount;
		this.voucher=voucher;
		this.startTime=startTime;
		this.endTime=endTime;
		this.valid=valid;		
	}

	

}
