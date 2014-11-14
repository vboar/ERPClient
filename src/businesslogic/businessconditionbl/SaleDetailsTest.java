/**
 * SaleDetailsTest类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.businessconditionbl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import vo.SaleVO;

public class SaleDetailsTest {

	@Test
	public void testShowByTime() {
		ArrayList<SaleVO> list = new ArrayList<SaleVO>();
		list.add(new SaleVO("A",200));
		MockSale ms = new MockSale(list);
		SaleDetails sd = new SaleDetails(ms);
		ArrayList<SaleVO> findList = sd.showByTime("2014/11/10", "2014/11/14");
		assertEquals(200,(int)findList.get(0).totalAfterDiscount);
	}

}
