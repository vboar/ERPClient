/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.customerbl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import util.ResultMessage;

public class CustomerTest {

	@Test
	public void testCreateLog() {
		Customer customer = new Customer();
		assertEquals(ResultMessage.SUCCESS, customer.createLog("2014/11/13 create an account!"));
	}
	
}
