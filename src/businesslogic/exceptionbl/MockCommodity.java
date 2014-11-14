/**
 * exception mockcommodity
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.exceptionbl;

import businesslogic.commoditybl.Commodity;

public class MockCommodity extends Commodity {

	private String id;
	
	private int num;
	
	private int warningNumber;
	
	public MockCommodity(String id, int num, int warningNumber) {
		this.id = id;
		this.num = num;
		this.warningNumber = warningNumber;
	}

	public String getId() {
		return id;
	}

	public int getNum() {
		return num;
	}
	
	public int getWarningNumber() {
		return warningNumber;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
}
