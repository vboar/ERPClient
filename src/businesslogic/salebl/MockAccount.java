package businesslogic.salebl;

import po.AccountPO;
import util.ResultMessage;

public class MockAccount {
public ResultMessage updateAccountBySale(String name,double total){
	AccountPO a=new AccountPO("","",0);
	a.setBalance(a.getBalance()+total);
	return null;
}
}
