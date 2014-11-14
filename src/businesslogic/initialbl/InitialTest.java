/**
 * initialtest
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.initialbl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import util.ResultMessage;

public class InitialTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateLog() {
		Initial initial = new Initial();
		assertEquals(ResultMessage.SUCCESS, initial.createLog("2014/11/14 do initial!"));
	}

}
