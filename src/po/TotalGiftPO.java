/**
 * 总计促销策略类
 * @author chengcheng
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

public class TotalGiftPO implements Serializable {

	/**
	 * 序列化UID
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 总价
	 */
	private double total;
	

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
	 * 间隔时间
	 */
	private String endTime;
	
	/**
	 * 是否生效
	 */
	private boolean valid;
	
	public TotalGiftPO(String id,double total,ArrayList<CommodityLineItemPO> giftInfo,double discount,double voucher,
			String startTime,String endTime,boolean valid){
		this.id=id;
		this.total=total;
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

	public double getTotal() {
		return total;
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
