/**
 * 库存PO类
 * @author Vboar
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;

public class StockPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 商品编号
	 */
	public String commodityId;
	
	/**
	 * 商品名称
	 */
	private String commodityName;
	
	/**
	 * 商品型号
	 */
	private String commodityModel;
	
	/**
	 * 库存数量
	 */
	private int number;
	
	/**
	 * 库存均价
	 */
	private double avgPrice;
	
	/**
	 * 批次
	 */
	private String batch;
	
	/**
	 * 批号
	 */
	private String batchNumber;
	
	/**
	 * 无参构造
	 */
	public StockPO() {}
	
	/**
	 * 构造方法
	 * @param commodityId
	 * @param commodityName
	 * @param commodityModel
	 * @param number
	 * @param avgPrice
	 * @param batch
	 * @param batchNumber
	 */
	public StockPO(String commodityId, String commodityName, String commodityModel, 
			int number, double avgPrice, String batch, String batchNumber) {
		this.commodityId = commodityId;
		this.commodityName = commodityName;
		this.commodityModel = commodityModel;
		this.number = number;
		this.avgPrice = avgPrice;
		this.batch = batch;
		this.batchNumber = batchNumber;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public String getCommodityModel() {
		return commodityModel;
	}

	public int getNumber() {
		return number;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public String getBatch() {
		return batch;
	}

	public String getBatchNumber() {
		return batchNumber;
	}
	
}
