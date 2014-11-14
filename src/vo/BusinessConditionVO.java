/**
 * 经营情况查看表VO类
 * @author JanelDQ
 *
 */
package vo;

public class BusinessConditionVO {

	/**
	 * 销售收入
	 */
	public double saleIncome;
	
	/**
	 * 商品报溢收入
	 */
	public double commodityOverIncome;
	
	/**
	 * 成本调价收入
	 */
	public double costAdjustIncome;
	
	/**
	 * 代金券与实际差额收入
	 */
	public double voucherIncome;
	
	/**
	 * 折让后收入
	 */
	public double incomeAfterDiscount;
	
	/**
	 * 折让
	 */
	public double discount;
	
	/**
	 * 销售成本
	 */
	public double saleCost;
	
	/**
	 * 商品报损支出
	 */
	public double costByLoss;
	
	/**
	 * 商品赠出支出
	 */
	public double costBySending;
	
	/**
	 * 总支出
	 */
	public double totalCost;
	
	/**
	 * 利润
	 */
	public double profit;

	/**
	 * 构造方法
	 * @param saleIncome
	 * @param commodityOverIncome
	 * @param costAdjustIncome
	 * @param voucherIncome
	 * @param incomeAfterDiscount
	 * @param discount
	 * @param saleCost
	 * @param costByLoss
	 * @param costBySending
	 * @param totalCost
	 * @param profit
	 */
	public BusinessConditionVO(double saleIncome, double commodityOverIncome,
			double costAdjustIncome, double voucherIncome,
			double incomeAfterDiscount, double discount, double saleCost,
			double costByLoss, double costBySending, double totalCost,
			double profit) {
		super();
		this.saleIncome = saleIncome;
		this.commodityOverIncome = commodityOverIncome;
		this.costAdjustIncome = costAdjustIncome;
		this.voucherIncome = voucherIncome;
		this.incomeAfterDiscount = incomeAfterDiscount;
		this.discount = discount;
		this.saleCost = saleCost;
		this.costByLoss = costByLoss;
		this.costBySending = costBySending;
		this.totalCost = totalCost;
		this.profit = profit;
	}
	
	
}
