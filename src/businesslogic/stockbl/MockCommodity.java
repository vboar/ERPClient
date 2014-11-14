/**
 * stock mockcommodity
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.stockbl;

import businesslogic.commoditybl.Commodity;

public class MockCommodity extends Commodity {

	private String id;
	
	private int num;
	
	public MockCommodity(String id, int num) {
		this.id = id;
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public int getNum() {
		return num;
	}
	
}
