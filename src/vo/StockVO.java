/**
 * 库存VO类
 * @author Vboar
 * @date 2014/10/25
 */
package vo;

public class StockVO {
	
	/**
	 * 商品编号
	 */
	public String commodityId;
	
	/**
	 * 商品名称
	 */
	public String commodityName;
	
	/**
	 * 商品型号
	 */
	public String commodityModel;
	
	/**
	 * 库存数量
	 */
	public int number;
	
	/**
	 * 库存均价
	 */
	public double avgPrice;
	
	/**
	 * 批次
	 */
	public String batch;
	
	/**
	 * 批号
	 */
	public String batchNumber;
	
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
	public StockVO(String commodityId, String commodityName, String commodityModel, 
			int number, double avgPrice, String batch, String batchNumber) {
		this.commodityId = commodityId;
		this.commodityName = commodityName;
		this.commodityModel = commodityModel;
		this.number = number;
		this.avgPrice = avgPrice;
		this.batch = batch;
		this.batchNumber = batchNumber;
	}

	public StockVO() {
	}
	
}
