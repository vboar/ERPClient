/**
 * vip等级促销类
 * @author chengcheng
 * @date 2014/10/25
 */

package po;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerGiftPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 客户级别
	 */
	private int vip;
	
	/**
	 * 赠品的列表
	 */
	private ArrayList<CommodityLineItemPO> giftInfo;
	
	/**
	 * 价格折让
	 */
	private double discount;
	
	/**
	 * 代金券
	 */
	private double voucher;
	
	/**
	 * 起始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
	 * 是否生效
	 */
	private boolean valid;
	
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
	public CustomerGiftPO(String id,int VIP,ArrayList<CommodityLineItemPO> giftInfo,
			double discount,double voucher,String startTime,String endTime,boolean valid){
		this.id=id;
		this.vip=VIP;
		this.giftInfo=giftInfo;
		this.discount=discount;
		this.voucher=voucher;
		this.startTime=startTime;
		this.endTime=endTime;
		this.valid=valid;		
	}

	public String getId() {
		return id;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getVIP() {
		return vip;
	}

	public ArrayList<CommodityLineItemPO> getGiftInfo() {
		return giftInfo;
	}

	public double getDiscount() {
		return discount;
	}

	public double getVoucher() {
		return voucher;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
	
	

}
