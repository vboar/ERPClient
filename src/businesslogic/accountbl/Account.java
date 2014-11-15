/**
 * Account逻辑类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package businesslogic.accountbl;

import po.AccountPO;
import util.ResultMessage;
import vo.AccountVO;

public class Account {

	public ResultMessage createLog(String content){	
		MockLog log = new MockLog(content);
		return log.add();		
	}
	
	public ResultMessage add(AccountVO vo){
		new AccountPO(vo.name,vo.account,vo.balance);
		return ResultMessage.SUCCESS;
	}
}
