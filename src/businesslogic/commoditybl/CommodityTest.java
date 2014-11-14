/**
 * commoditytest
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.commoditybl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import util.ResultMessage;

public class CommodityTest {

	@Test
	public void testCreateLog() {
		Commodity commodity = new Commodity();
		assertEquals(ResultMessage.SUCCESS, commodity.createLog("2014/11/14 do commodity!"));
	}

}
