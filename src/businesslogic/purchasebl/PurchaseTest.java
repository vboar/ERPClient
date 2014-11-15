/**
 * Purchase
 * @author oneoneO
 * @date 2014/11/15
 */
package businesslogic.purchasebl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import util.ResultMessage;
import vo.CommodityLineItemVO;

public class PurchaseTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ArrayList<CommodityLineItemVO> list=new ArrayList<CommodityLineItemVO>();
		list.add(new CommodityLineItemVO("","","",10,0,0,""));
		
		Purchase p=new Purchase();
		assertEquals(ResultMessage.SUCCESS,p.updateCommodityByPurchase(list));
		
		assertEquals(ResultMessage.SUCCESS,p.updateCustomerByPurchase("飞利浦",  100));
		assertEquals(ResultMessage.SUCCESS,p.addLog("2014/11/15 Purchase"));
	}

}
