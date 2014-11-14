/**
 * categotytest
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.commoditybl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import util.ResultMessage;

public class CategoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateLog() {
		Category category = new Category();
		assertEquals(ResultMessage.SUCCESS, category.createLog("2014/11/14 do category"));
	}

}
