/**
 * Account逻辑类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package businesslogic.accountbl;

import util.ResultMessage;

public class Account {

	public ResultMessage createLog(String content){	
		MockLog log = new MockLog(content);
		return log.add();		
	}
}
