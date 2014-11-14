/**
 * payment MockCustomer
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import businesslogic.customerbl.Customer;

public class MockCustomer extends Customer{
	
	private double receivables;
	
    private double paybles;
	   	
	public double getReceivables() {
		return receivables;
	}

	public void setReceivables(double receivables) {
		this.receivables = receivables;
	}

	public double getPaybles() {
		return paybles;
	}

	public void setPaybles(double paybles) {
		this.paybles = paybles;
	}

	public void updatePay(double money){
		this.paybles = this.paybles + money;			
	}
	
	public void updateRec(double money){
		this.receivables = this.receivables + money;
	}
}
