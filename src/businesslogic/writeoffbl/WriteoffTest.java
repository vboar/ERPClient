package businesslogic.writeoffbl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import util.DocumentType;
import util.ResultMessage;

public class WriteoffTest {

	@Test
	public void testAutoCreate() {
		MockCash mc = new MockCash();
		MockOver mo = new MockOver();
		MockPayment mpay = new MockPayment();
		MockPurchase mpur = new MockPurchase();
		MockSale ms = new MockSale();
		MockPresent mpre = new MockPresent();
		Writeoff wt = new Writeoff(mo,mc,mpay,mpre,mpur,ms);
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.CASH, "XJFYD-00001"));
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.OVERFLOW, "BYD-00001"));
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.PAYMENT, "FKD-00001"));
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.SALE, "XSD-00001"));
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.PRESENT, "ZSD-00001"));
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.PURCHASE, "JHD-00001"));
		
	}

}
