/**
 * warning逻辑类
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.exceptionbl;

import util.ResultMessage;
import businesslogic.stockbl.MockLog;


public class Warning {

	private MockCommodity mc;
	private MockSale ms;
	
	public Warning() {}
	
	public Warning(MockCommodity mc, MockSale ms) {
		this.mc = mc;
		this.ms = ms;
	}
	
	public boolean isWarning() {
		mc.setNum(mc.getNum()-ms.getNum());
		if(mc.getNum() < mc.getWarningNumber()) return true;
		return false;
	}
	
	public ResultMessage createLog(String content) {
		MockLog ml = new MockLog(content);
		return ml.create();
	}

}
