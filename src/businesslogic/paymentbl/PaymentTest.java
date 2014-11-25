/**
 * paymentTest类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Test;

import util.ResultMessage;

public class PaymentTest {

	@Test
	public void testUpdate() throws RemoteException {
		MockCustomer customer = new MockCustomer();
		customer.setPaybles(1000);
		customer.setReceivables(2000);
		MockAccount account = new MockAccount(3000);
		Payment pay = new Payment(customer,account);
		pay.update(200,"12345678");
		assertEquals(800,(int)customer.getPaybles());
		assertEquals(2800,(int)account.getAccount());
	}

	@Test
	public void testCreateLog() {
		MockCustomer customer = new MockCustomer();
		MockAccount account = new MockAccount(3000);
		Payment pay = new Payment(customer,account);
		assertEquals(ResultMessage.SUCCESS, pay.createLog("2014/11/13 create an Payment!"));
	}
}
