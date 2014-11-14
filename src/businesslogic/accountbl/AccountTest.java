package businesslogic.accountbl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import util.ResultMessage;

public class AccountTest {

	@Test
	public void testCreateLog() {
		Account account = new Account();
		assertEquals(ResultMessage.SUCCESS, account.createLog("2014/11/13 create an account!"));
	}
	
}
