/**
 * payment MockAccount
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

public class MockAccount {

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
