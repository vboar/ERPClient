/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.promotionbl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import vo.CustomerGiftVO;

public class PromotionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByVip() {

		ArrayList<CustomerGiftVO> list = new ArrayList<CustomerGiftVO>();
		list.add(new CustomerGiftVO("00001", 5, null, 100.0, 10.0,
				"2014/10/23", "2014/10/25", false));
		list.add(new CustomerGiftVO("00002", 4, null, 100.0, 10.0,
				"2014/10/23", "2014/10/25", false));
		ArrayList<CustomerGiftVO> list2 = new ArrayList<CustomerGiftVO>();
		list2.add(new CustomerGiftVO("00001", 5, null, 100.0, 10.0,
				"2014/10/23", "2014/10/25", false));
		CustomerGiftPromotion bl = new CustomerGiftPromotion();
		MockCustomer customer = new MockCustomer(5);
		for (int i = 0; i < list2.size(); i++) {
			assertEquals(list2.get(i).id,
					bl.findByVip(customer.getLevel(), list).get(i).id);
		}
	}

}
