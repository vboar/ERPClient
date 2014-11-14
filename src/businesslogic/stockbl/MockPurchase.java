/**
 * stock mockpurchase
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.stockbl;

import businesslogic.purchasebl.Purchase;

public class MockPurchase extends Purchase {

	private String commodityId;
	
	private int num;
	
	public MockPurchase(String commodityId, int num) {
		this.commodityId = commodityId;
		this.num = num;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public int getNum() {
		return num;
	}
	
}
