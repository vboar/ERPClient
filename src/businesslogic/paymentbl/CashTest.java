/**
 * cashTest类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import static org.junit.Assert.*;

import org.junit.Test;

import util.ResultMessage;

public class CashTest {

	@Test
	public void testUpdate() {
		MockAccount account = new MockAccount(3000);
		Cash cash = new Cash(account);
		cash.update(300);
		assertEquals(2700,(int)account.getAccount());
	}

	@Test
	public void testCreateLog() {
		MockAccount account = new MockAccount(3000);
		Cash cash = new Cash(account);
		assertEquals(ResultMessage.SUCCESS, cash.createLog("2014/11/14 create an Cash!"));
	}

}
