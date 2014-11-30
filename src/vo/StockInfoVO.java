/**
 * 库存查看信息VO类
 * @author Vboar
 * @date 2014/10/25
 */
package vo;

public class StockInfoVO {

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
	 * 入库数量
	 */
	public int inNumber;
	
	/**
	 * 出库数量
	 */
	public int outNumber;
	
	/**
	 * 入库金额
	 */
	public double inMoney;
	
	/**
	 * 出库金额
	 */
	public double outMoney;

	public StockInfoVO() {
	}
	
	/**
	 * 构造方法
	 * @param id
	 * @param name
	 * @param model
	 * @param inNumber
	 * @param outNumber
	 * @param inMoney
	 * @param outMoney
	 */
	public StockInfoVO(String id, String name, String model, int inNumber,
			 double inMoney, int outNumber, double outMoney) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.inNumber = inNumber;
		this.outNumber = outNumber;
		this.inMoney = inMoney;
		this.outMoney = outMoney;
	}
	
}
