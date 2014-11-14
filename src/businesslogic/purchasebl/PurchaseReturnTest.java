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

public class PurchaseReturnTest {

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
	public void testUpdateCommodityByPurchase() {
		ArrayList<CommodityLineItemVO> list=new ArrayList<CommodityLineItemVO>();
		list.add(new CommodityLineItemVO("","","",-10,0,0,""));
		
		PurchaseReturn p=new PurchaseReturn();
		assertEquals(ResultMessage.SUCCESS,p.updateCommodityByPurchase(list));
	}

}
