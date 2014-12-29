/**
 * 特价包促销策略类
 * @author chengcheng
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

public class SpecialOfferPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String id;
	/**
	 * 商品列表
	 */
	private ArrayList<CommodityLineItemPO> commodityList;
	
	/**
	 * 总价
	 */
	private double total;
	
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
	 * 构造方法
	 * @param id
	 * @param commodityList
	 * @param total
	 * @param startTime
	 * @param endTime
	 * @param valid
	 */
	public SpecialOfferPO(String id,ArrayList<CommodityLineItemPO> commodityList
			,double total,String startTime,String endTime,boolean valid){
		this.id=id;
		this.commodityList=commodityList;
		this.total=total;
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

	public ArrayList<CommodityLineItemPO> getCommodityList() {
		return commodityList;
	}

	public double getTotal() {
		return total;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

}
