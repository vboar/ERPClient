/**
 * present逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.presentbl;

import util.ResultMessage;


public class Present {
	
	private MockCommodity mc;
	
	public Present() {}
	
	public Present(MockCommodity mc) {
		this.mc = mc;
	}
	
	public ResultMessage afterApproval(int number) {
		return mc.updateNum(number);
	}
	
	public ResultMessage createLog(String content) {
		MockLog ml = new MockLog(content);
		return ml.create();
	}
	
}
