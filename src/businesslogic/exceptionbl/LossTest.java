/**
 * losstest
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.exceptionbl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.ResultMessage;

public class LossTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsLoss() {
		MockCommodity mc = new MockCommodity("1", 100, 500);
		Loss loss = new Loss(mc);
		assertEquals(true, loss.isLoss(80));
	}

	@Test
	public void testCreateLog() {
		Loss loss = new Loss();
		assertEquals(ResultMessage.SUCCESS, loss.createLog("2014/11/14 loss!"));
	}

}
