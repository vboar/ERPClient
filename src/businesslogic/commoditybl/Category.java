/**
 * category逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.commoditybl;

import util.ResultMessage;

public class Category {

	public ResultMessage createLog(String content) {
		MockLog ml = new MockLog(content);
		return ml.create();
	}

}
