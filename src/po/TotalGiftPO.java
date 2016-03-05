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
	private ArrayList<PresentLineItemPO> giftInfo;
	
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

	/**
	 * 构造方法
	 * @param id
	 * @param total
	 * @param giftInfo
	 * @param voucher
	 * @param startTime
	 * @param endTime
	 * @param valid
	 */
	public TotalGiftPO(String id,double total,ArrayList<PresentLineItemPO> giftInfo,double voucher,
			String startTime,String endTime,boolean valid){
		this.id=id;
		this.total=total;
		this.giftInfo=giftInfo;
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

	public ArrayList<PresentLineItemPO> getGiftInfo() {
		return giftInfo;
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
