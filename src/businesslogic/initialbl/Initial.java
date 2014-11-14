/**
 * initial逻辑类
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.initialbl;

import util.ResultMessage;

public class Initial {
	
	public ResultMessage createLog(String content) {
		MockLog ml = new MockLog(content);
		return ml.create();
	}
	
}
