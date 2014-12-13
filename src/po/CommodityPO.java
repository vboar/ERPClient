/**
 * 商品PO类
 * @author oneoneO
 * @date  2014/10/25 
 */
package po;

import java.io.Serializable;

public class CommodityPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品编号
	 */
	private String id;
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 商品型号
	 */
	private String model;
	
	/**
	 * 数量
	 */
	private int number;
	
	/**
	 * 进价
	 */
	private double purchasePrice;
	
	/**
	 * 售价
	 */
	private double salePrice;
	
	/**
	 * 最近进价
	 */
	private double recentPurchasePrice;
	
	/**
	 * 最近售价 
	 */
	private double recentSalePrice;
	
	/**
	 * 警戒值
	 */
	private int warningNumber;
	
	/**
	 * 是否进行过交易
	 */
	private boolean isTrade;
	
	/**
	 * 无参构造
	 */
	public CommodityPO() {}
	
	/**
	 * 构造函数
	 * @param name
	 * @param model
	 * @param number
	 * @param purchasePrice
	 * @param salePrice
	 * @param recentPurchasePrice
	 * @param recentSalePrice
	 * @param warningNumber
	 * @param isTrade
	 */
	public CommodityPO(String id,String name,String model,int number,double purchasePrice,
			double salePrice,double recentPurchasePrice,double recentSalePrice,
	        int warningNumber,boolean isTrade){
		    this.id=id;
		    this.name=name;
		    this.model=model;
			this.number=number;
		    this.purchasePrice=purchasePrice;
		    this.salePrice=salePrice;
		    this.recentPurchasePrice=recentPurchasePrice;
		    this.recentSalePrice=recentSalePrice;
		    this.warningNumber=warningNumber;
		    this.isTrade=isTrade;
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getRecentPurchasePrice() {
		return recentPurchasePrice;
	}

	public void setRecentPurchasePrice(double recentPurchasePrice) {
		this.recentPurchasePrice = recentPurchasePrice;
	}

	public double getRecentSalePrice() {
		return recentSalePrice;
	}

	public void setRecentSalePrice(double recentSalePrice) {
		this.recentSalePrice = recentSalePrice;
	}

	public int getWarningNumber() {
		return warningNumber;
	}

	public void setWarningNumber(int warningNumber) {
		this.warningNumber = warningNumber;
	}

	public boolean isTrade() {
		return isTrade;
	}

	public void setTrade(boolean isTrade) {
		this.isTrade = isTrade;
	}

	public String getId() {
		return id;
	}
	
}
