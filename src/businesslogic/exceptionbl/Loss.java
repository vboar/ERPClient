/**
 * loss逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.exceptionbl;

import util.ResultMessage;
import businesslogic.stockbl.MockLog;


public class Loss {

	private MockCommodity mc;
	
	public Loss() {}
	
	public Loss(MockCommodity mc) {
		this.mc = mc;
	}
	
	public boolean isLoss(int newNum) {
		if(mc.getNum() > newNum) return true;
		return false;
	}
	
	public ResultMessage createLog(String content) {
		MockLog ml = new MockLog(content);
		return ml.create();
	}
	
}
