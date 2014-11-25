/**
 * Payment逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import java.rmi.RemoteException;

import po.AccountPO;
import businesslogic.accountbl.Account;
import businesslogic.accountbl.MockLog;
import util.ResultMessage;

//oneoneO
public class Payment {

	MockCustomer customer;
	
	MockAccount account;
	
	public Payment(MockCustomer customer, MockAccount account){
		this.customer = customer;
		this.account = account;	
	}
	
	public Payment(){
		
	}
	
	public ResultMessage update(double money,String account) throws RemoteException{
		Account acc=new Account();
		AccountPO temp=acc.findByAccount(account).get(0);
		temp.setBalance(temp.getBalance()-money);
		acc.update(temp);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage createLog(String content){	
		MockLog log = new MockLog(content);
		return log.add();
	}
}
