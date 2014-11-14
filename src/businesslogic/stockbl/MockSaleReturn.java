/**
 * stock mocksalereturn
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.stockbl;

import businesslogic.salebl.SaleReturn;

public class MockSaleReturn extends SaleReturn {

	private String commodityId;
	
	private int num;
	
	public MockSaleReturn(String commodityId, int num) {
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
