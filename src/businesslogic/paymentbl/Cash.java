/**
 * Cash类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import util.ResultMessage;
import businesslogic.accountbl.MockLog;

public class Cash {
	
	MockAccount account;
	
	public Cash(MockAccount account){
		this.account = account;
	}
	
	public ResultMessage update(double money){
		this.account.updateAccount(-money);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage createLog(String content){	
		MockLog log = new MockLog(content);
		return log.add();	
	}
	
}
