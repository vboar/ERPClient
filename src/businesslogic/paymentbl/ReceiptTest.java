/**
 * ReceiptTest类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import static org.junit.Assert.*;

import org.junit.Test;

import util.ResultMessage;

public class ReceiptTest {

	@Test
	public void testUpdate() {
		MockCustomer customer = new MockCustomer();
		customer.setPaybles(1000);
		customer.setReceivables(2000);
		MockAccount account = new MockAccount(3000);
		Receipt rec = new Receipt(customer,account);
		rec.update(200);
		assertEquals(1800,(int)customer.getReceivables());
		assertEquals(3200,(int)account.getAccount());
	}

	@Test
	public void testCreateLog() {
		MockCustomer customer = new MockCustomer();
		MockAccount account = new MockAccount(3000);
		Receipt rec = new Receipt(customer,account);
		assertEquals(ResultMessage.SUCCESS, rec.createLog("2014/11/13 create an Receipt!"));
	}

}
