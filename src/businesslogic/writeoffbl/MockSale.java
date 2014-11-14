/**
 * writeoff mocksale
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.writeoffbl;

import util.ResultMessage;
import businesslogic.salebl.Sale;

public class MockSale extends Sale {

	public ResultMessage autoCreateWriteOffSale(String id) {
		return ResultMessage.SUCCESS;
	}

}
