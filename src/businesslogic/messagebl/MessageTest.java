package businesslogic.messagebl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import util.ResultMessage;

public class MessageTest {

	@Test
	public void testCreateLog() {
		Message account = new Message();
		assertEquals(ResultMessage.SUCCESS, account.createLog("2014/11/13 create a message!"));
	}
	
}
