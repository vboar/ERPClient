/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.userbl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import util.ResultMessage;

public class UserTest {

	@Test
	public void testCreateLog() {
		User user = new User();
		assertEquals(ResultMessage.SUCCESS, user.createLog("2014/11/13 create a User!"));
	}
	
}
