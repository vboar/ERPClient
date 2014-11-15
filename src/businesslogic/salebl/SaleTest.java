/**
 * SaleTest
 * @author oneoneO
 * @date 2014/11/15
 */
package businesslogic.salebl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import util.ResultMessage;
import vo.CommodityLineItemVO;

public class SaleTest {

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
		ArrayList<CommodityLineItemVO> sli=new ArrayList<CommodityLineItemVO>();
		sli.add(new CommodityLineItemVO("","","",10,0,0,""));
		
		Sale sl=new Sale();
		assertEquals(ResultMessage.SUCCESS,sl.updateCommodityBySale(sli));
		
		assertEquals(ResultMessage.SUCCESS,sl.updateCustomerBySale("飞利浦", 100));
		assertEquals(ResultMessage.SUCCESS,sl.addlog("2014/11/15 Sale"));
	}

}
