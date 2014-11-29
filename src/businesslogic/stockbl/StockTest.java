/**
 * stocktest
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.stockbl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

import util.ResultMessage;

public class StockTest {

	@Before
	public void setUp() throws Exception {
	}

	
	public void testCreateLog() {
		Stock stock = new Stock();
		assertEquals(ResultMessage.SUCCESS, stock.createLog("2014/11/14 do stock!"));
	}

}
