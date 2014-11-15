/**
 * MockCustomer
 * @author oenoenO
 * @date 2014/11/15
 */
package businesslogic.purchasebl;

import po.AccountPO;
import util.ResultMessage;

public class MockCustomer {
	public ResultMessage updateCustomerByPurchase(String name,double total){
		AccountPO a=new AccountPO("","",0);
		a.setBalance(a.getBalance()+total);
		return ResultMessage.SUCCESS;
	}
}
