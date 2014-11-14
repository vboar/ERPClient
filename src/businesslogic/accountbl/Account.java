package businesslogic.accountbl;

import util.ResultMessage;

public class Account {

	public ResultMessage createLog(String content){
		
		MockLog log = new MockLog(content);
		return log.add();	
		
	}
}
