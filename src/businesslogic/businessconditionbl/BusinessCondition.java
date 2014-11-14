/**
 * BusinessCondition逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

public class BusinessCondition {

	/**
	 * 销售收入
	 */
	private double saleIn;
	
	/**
	 * 商品类收入
	 */
	private double commodityIn;
	
	/**
	 * 销售成本
	 */
	private double cost;
	
	/**
	 * 商品类支出
	 */
	private double commodityOut;

	private MockSale ms;
	
	private MockLoss ml;
	
	private MockOver mo;
	
	private MockPresent mpre;

	public BusinessCondition( MockLoss ml,MockOver mo, MockSale ms, MockPresent mpre) {
		super();
		this.ms = ms;
		this.ml = ml;
		this.mo = mo;
		this.mpre = mpre;
	}

	public double getSaleIn() {
		this.saleIn = ms.getTotal();
		return saleIn;
	}

	public double getCommodityIn() {
		this.commodityIn = this.mo.getTotal();
		return commodityIn;
	}

	public double getCost() {
		this.cost = this.ms.getCostTotal();
		return cost;
	}

	public double getCommodityOut() {
		this.commodityOut = this.ml.getTotal()+this.mpre.getTotal();
		return commodityOut;
	}
	
	public double getProfit(){
		return this.getCommodityIn()+this.getSaleIn()-this.getCommodityOut()-this.getCost();
	}
		
}
