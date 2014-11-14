package businesslogic.messagebl;

import util.ResultMessage;
import businesslogic.accountbl.MockLog;

public class Message {
public ResultMessage createLog(String content){
		
		MockLog log = new MockLog(content);
		return log.add();	
		
	}


}
