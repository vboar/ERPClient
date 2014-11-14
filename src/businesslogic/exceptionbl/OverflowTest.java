/**
 * overflowtest
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.exceptionbl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import util.ResultMessage;

public class OverflowTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsOverflow() {
		MockCommodity mc = new MockCommodity("1", 100, 500);
		Overflow overflow = new Overflow(mc);
		assertEquals(true, overflow.isOverflow(110));
	}

	@Test
	public void testCreateLog() {
		Overflow overflow = new Overflow();
		assertEquals(ResultMessage.SUCCESS, overflow.createLog("2014/11/14 overflow!"));
	}

}
