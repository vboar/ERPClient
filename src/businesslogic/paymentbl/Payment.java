/**
 * Payment逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import businesslogic.accountbl.MockLog;
import util.ResultMessage;

public class Payment {

	MockCustomer customer;
	
	MockAccount account;
	
	public Payment(MockCustomer customer, MockAccount account){
		this.customer = customer;
		this.account = account;	
	}
	
	public Payment(){
		
	}
	
	public ResultMessage update(double money){
		this.customer.updatePay(-money);
		this.account.updateAccount(-money);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage createLog(String content){	
		MockLog log = new MockLog(content);
		return log.add();
	}
}
