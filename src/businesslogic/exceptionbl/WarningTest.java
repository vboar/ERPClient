package businesslogic.exceptionbl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.ResultMessage;

public class WarningTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsWarning() {
		MockCommodity mc = new MockCommodity("1", 100, 80);
		MockSale ms = new MockSale("1", 25);
		Warning warning = new Warning(mc, ms);
		assertEquals(true, warning.isWarning());
	}

	@Test
	public void testCreateLog() {
		Warning warning = new Warning();
		assertEquals(ResultMessage.SUCCESS, warning.createLog("2014/11/14 warning!"));
	}

}
