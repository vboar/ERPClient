/**
 * overflow逻辑类
 */

package businesslogic.exceptionbl;

import businesslogic.stockbl.MockLog;
import util.ResultMessage;


public class Overflow {

	private MockCommodity mc;
	
	public Overflow() {}
	
	public Overflow(MockCommodity mc) {
		this.mc = mc;
	}
	
	public boolean isOverflow(int newNum) {
		if(mc.getNum() < newNum) return true;
		return false;
	}
	
	public ResultMessage createLog(String content) {
		MockLog ml = new MockLog(content);
		return ml.create();
	}
	
}
