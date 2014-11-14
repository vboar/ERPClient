package businesslogic.businessconditionbl;

import static org.junit.Assert.*;

import org.junit.Test;

public class BusinessConditionTest {

	@Test
	public void testGetSaleIn() {
		MockSale ms = new MockSale(3000,1000);
		MockOver mo = new MockOver(200);
		MockLoss ml = new MockLoss(100);
		MockPresent mpre = new MockPresent(300);
		BusinessCondition bc = new BusinessCondition(ml,mo,ms,mpre);
		assertEquals(3000,(int)bc.getSaleIn());
	}

	@Test
	public void testGetCommodityIn() {
		MockSale ms = new MockSale(3000,1000);
		MockOver mo = new MockOver(200);
		MockLoss ml = new MockLoss(100);
		MockPresent mpre = new MockPresent(300);
		BusinessCondition bc = new BusinessCondition(ml,mo,ms,mpre);
		assertEquals(200,(int)bc.getCommodityIn());
	}

	@Test
	public void testGetCost() {
		MockSale ms = new MockSale(3000,1000);
		MockOver mo = new MockOver(200);
		MockLoss ml = new MockLoss(100);
		MockPresent mpre = new MockPresent(300);
		BusinessCondition bc = new BusinessCondition(ml,mo,ms,mpre);
		assertEquals(1000,(int)bc.getCost());
	}

	@Test
	public void testGetCommodityOut() {
		MockSale ms = new MockSale(3000,1000);
		MockOver mo = new MockOver(200);
		MockLoss ml = new MockLoss(100);
		MockPresent mpre = new MockPresent(300);
		BusinessCondition bc = new BusinessCondition(ml,mo,ms,mpre);
		assertEquals(400,(int)bc.getCommodityOut());
	}

	@Test
	public void testGetProfit() {
		MockSale ms = new MockSale(3000,1000);
		MockOver mo = new MockOver(200);
		MockLoss ml = new MockLoss(100);
		MockPresent mpre = new MockPresent(300);
		BusinessCondition bc = new BusinessCondition(ml,mo,ms,mpre);
		assertEquals(1800,(int)bc.getProfit());
	}

}
