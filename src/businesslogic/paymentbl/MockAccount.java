/**
 * payment MockAccount
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import businesslogic.accountbl.Account;

public class MockAccount extends Account{

	double account;
	
	public MockAccount(double account){	
		this.account = account;
	}
	
	public void updateAccount(double money){
		this.account = this.account + money;
	}

	public double getAccount() {
		return this.account;
	}
}
