/**
 * exception mocksale
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.exceptionbl;

import businesslogic.salebl.Sale;

public class MockSale extends Sale {

	private String commodityId;
	
	private int num;
	
	public MockSale(String commodityId, int num) {
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
