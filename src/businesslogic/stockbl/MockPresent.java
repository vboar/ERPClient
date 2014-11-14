/**
 * stock mockpresent
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.stockbl;

import businesslogic.presentbl.Present;

public class MockPresent extends Present {
	
	private String commodityId;
	
	private int num;
	
	public MockPresent(String commodityId, int num) {
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
