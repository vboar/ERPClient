/**
 * BusinessHitoryTest类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import vo.ExceptionVO;
import vo.PaymentVO;
import vo.PurchaseVO;
import vo.SaleVO;

public class BusinessHistoryTest {

	@Test
	public void testShowSaleByCustomer() {
		ArrayList<SaleVO> list = new ArrayList<SaleVO>();
		list.add(new SaleVO("A",200));
		MockSale ms = new MockSale(list);
		BusinessHistory bh = new BusinessHistory(ms);
		ArrayList<SaleVO> findList = bh.showSaleByCustomer("A");
		assertEquals(200, (int)findList.get(0).totalAfterDiscount);
	}

	@Test
	public void testShowPurchaseByTime() {
		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		list.add(new PurchaseVO("A",200));
		MockPurchase mpur = new MockPurchase(list);
		BusinessHistory bh = new BusinessHistory(mpur);
		ArrayList<PurchaseVO> findList = bh.showPurchaseByOperator("A");
		assertEquals(200, (int)findList.get(0).total);
	}

	@Test
	public void testShowPaymentByTime() {
		ArrayList<PaymentVO> list = new ArrayList<PaymentVO>();
		list.add(new PaymentVO("2014/11/1",200));
		MockPayment mpay = new MockPayment(list);
		BusinessHistory bh = new BusinessHistory(mpay);
		ArrayList<PaymentVO> findList = bh.showPaymentByTime("2014/11/1","2014/11/14");
		assertEquals(200, (int)findList.get(0).total);
	}

	@Test
	public void testShowExceptionByTime() {
		ArrayList<ExceptionVO> list = new ArrayList<ExceptionVO>();
		list.add(new ExceptionVO("1","2014/11/1"));
		MockLoss me = new MockLoss(list);
		BusinessHistory bh = new BusinessHistory(me);
		ArrayList<ExceptionVO> findList = bh.showExceptionByTime("2014/11/1","2014/11/14");
		assertEquals("2014/11/1", findList.get(0).time);
	}

	

}
