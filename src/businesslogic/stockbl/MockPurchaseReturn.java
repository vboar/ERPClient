/**
 * stock mockpurchasereturn
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.stockbl;

import businesslogic.purchasebl.PurchaseReturn;

public class MockPurchaseReturn extends PurchaseReturn {

	private String commodityId;
	
	private int num;
	
	public MockPurchaseReturn(String commodityId, int num) {
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
