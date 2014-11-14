/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.userbl;

import util.ResultMessage;
import businesslogic.accountbl.MockLog;

public class User {
public ResultMessage createLog(String content){
		
		MockLog log = new MockLog(content);
		return log.add();		
	}

}
