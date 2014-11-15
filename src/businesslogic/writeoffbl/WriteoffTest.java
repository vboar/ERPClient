package businesslogic.writeoffbl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import util.DocumentType;
import util.ResultMessage;
import vo.CashVO;
import vo.ExceptionVO;
import vo.PaymentVO;

public class WriteoffTest {

	@Test
	public void testAutoCreate() {
		Writeoff wt = new Writeoff();
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.CASH, "XJFYD-00001"));
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.OVERFLOW, "BYD-00001"));
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.PAYMENT, "FKD-00001"));
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.SALE, "XSD-00001"));
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.PRESENT, "ZSD-00001"));
		assertEquals(ResultMessage.SUCCESS,wt.autoCreate(DocumentType.PURCHASE, "JHD-00001"));
		
	}

	@Test
	public void testManualCreate() {
		Writeoff wt = new Writeoff();
		assertEquals(ResultMessage.SUCCESS,wt.manualCreate(DocumentType.CASH, new CashVO()));
		assertEquals(ResultMessage.SUCCESS,wt.manualCreate(DocumentType.OVERFLOW, new ExceptionVO()));
		assertEquals(ResultMessage.SUCCESS,wt.manualCreate(DocumentType.PAYMENT, new PaymentVO()));
	}
	
	@Test
	public void testCreateLog(){
		Writeoff wt = new Writeoff();
		assertEquals(ResultMessage.SUCCESS, wt.createLog("2014/11/13 create an writeoff!"));
	}
	
}
