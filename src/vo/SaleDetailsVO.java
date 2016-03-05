package vo;

/**
 * 销售明细表VO
 * @author JanelDQ
 * @date 2014/12/7
 */
public class SaleDetailsVO {

	/**
	 * 时间
	 */
	public String time;

	/**
	 * 商品名称
	 */
	public String name;

	/**
	 * 型号
	 */
	public String model;

	/**
	 * 数量
	 */
	public int number;

	/**
	 * 价格
	 */
	public double price;

	/**
	 * 总价
	 */
	public double total;

	/**
	 * 构造函数
	 * @param time
	 * @param name
	 * @param model
	 * @param number
	 * @param price
	 * @param total
	 */
	public SaleDetailsVO(String time, String name, String model,
			int number, double price, double total){
		this.time = time;
		this.name = name;
		this.model = model;
		this.number = number;
		this.price = price;
		this.total = total;
	}
	
}
