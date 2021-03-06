/**
 * 商品VO类
 * @author Vboar
 * @date 2014/10/25
 */
package vo;

public class CommodityVO {

	/**
	 * 商品编号
	 */
	public String id;
	
	/**
	 * 商品名称
	 */
	public String name;
	
	/**
	 * 商品型号
	 */
	public String model;
	
	/**
	 * 商品数量
	 */
	public int number;
	
	/**
	 * 进价
	 */
	public double purchasePrice;
	
	/**
	 * 售价
	 */
	public double salePrice;
	
	/**
	 * 最近进价
	 */
	public double recentPurchasePrice;
	
	/**
	 * 最近售价
	 */
	public double recentSalePrice;
	
	/**
	 * 警戒数量
	 */
	public int warningNumber;
	
	/**
	 * 是否交易
	 */
	public boolean isTrade;
	
	/**
	 * 它的分类
	 */
	public CategoryVO category;

	/**
	 * 构造函数
	 * @param id
	 * @param name
	 * @param model
	 * @param number
	 * @param purchasePrice
	 * @param salePrice
	 * @param recentPurchasePrice
	 * @param recentSalePrice
	 * @param warningNumber
	 * @param isTrade
	 * @param category
	 */
	public CommodityVO(String id, String name, String model, int number, double purchasePrice,
			double salePrice, double recentPurchasePrice, double recentSalePrice,
	        int warningNumber, boolean isTrade,CategoryVO category) {
		    this.id = id;
		    this.name = name;
		    this.model = model;
		    this.number=number;
		    this.purchasePrice = purchasePrice;
		    this.salePrice = salePrice;
		    this.recentPurchasePrice = recentPurchasePrice;
		    this.recentSalePrice = recentSalePrice;
		    this.warningNumber = warningNumber;
		    this.isTrade = isTrade;
		    this.category=category;
	}
	
}
