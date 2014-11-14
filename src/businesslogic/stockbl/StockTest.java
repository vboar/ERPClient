/**
 * stocktest
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.stockbl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import util.ResultMessage;
import vo.StockInfoVO;
import vo.StockVO;

public class StockTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShowStockInfo() {
		MockCommodity mc = new MockCommodity("1", 0);
		MockPresent mp = new MockPresent(mc.getId(), 10);
		MockPurchase mpc = new MockPurchase(mc.getId(), 50);
		MockPurchaseReturn mpcr = new MockPurchaseReturn(mc.getId(), 40);
		MockSale ms = new MockSale(mc.getId(), 200);
		MockSaleReturn msr = new MockSaleReturn(mc.getId(), 100);
		Stock stock = new Stock(mp, mpc, mpcr, ms, msr);
		ArrayList<StockInfoVO> stockIntoList = stock.showStockInfo(null, null);
		assertEquals(150, stockIntoList.get(0).inNumber);
		assertEquals(250, stockIntoList.get(0).outNumber);
	}
	
	@Test
	public void testShowCheck() {
		MockCommodity mc = new MockCommodity("1", 0);
		MockPresent mp = new MockPresent(mc.getId(), 10);
		MockPurchase mpc = new MockPurchase(mc.getId(), 50);
		MockPurchaseReturn mpcr = new MockPurchaseReturn(mc.getId(), 40);
		MockSale ms = new MockSale(mc.getId(), 200);
		MockSaleReturn msr = new MockSaleReturn(mc.getId(), 100);
		Stock stock = new Stock(mp, mpc, mpcr, ms, msr);
		ArrayList<StockVO> stockList = stock.showCheck();
		assertEquals(-100, stockList.get(0).number);
	}
	
	@Test
	public void testCreateLog() {
		Stock stock = new Stock();
		assertEquals(ResultMessage.SUCCESS, stock.createLog("2014/11/14 do stock!"));
	}

}
