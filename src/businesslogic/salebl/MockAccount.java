/**
 *MockAccount
 *@author oenoneO
 *@date 2014/11/14
 */
package businesslogic.salebl;

import businesslogic.accountbl.Account;
import po.AccountPO;
import util.ResultMessage;

public class MockAccount extends Account{
public ResultMessage updateAccountBySale(String name,double total){
	AccountPO a=new AccountPO("","",0);
	a.setBalance(a.getBalance()+total);
	return null;
}
}
