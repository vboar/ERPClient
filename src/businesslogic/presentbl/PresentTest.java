package businesslogic.presentbl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.ResultMessage;
import businesslogic.commoditybl.Commodity;

public class PresentTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAfterApproval() {
		MockCommodity mc = new MockCommodity("1", 50);
		Present present = new Present(mc);
		mc.updateNum(10);
		assertEquals(40, mc.getNum());
	}

	@Test
	public void testCreateLog() {
		Present present = new Present();
		assertEquals(ResultMessage.SUCCESS, present.createLog("2014/11/14 do commodity!"));
	}

}
