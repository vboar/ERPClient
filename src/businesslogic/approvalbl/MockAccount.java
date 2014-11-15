/**
 * MockAccount
 * @author oneoneO
 * @date 2014/11/15
 */
package businesslogic.approvalbl;

import po.AccountPO;
import util.ResultMessage;
import businesslogic.accountbl.Account;

public class MockAccount extends Account{

	public ResultMessage updateAccountByApproval(String name,String account,double total){
		AccountPO a=new AccountPO("飞利浦","12345678",100);
		a.setBalance(a.getBalance()+total);
		return ResultMessage.SUCCESS;
	}
}
