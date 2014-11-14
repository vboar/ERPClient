/**
 * writeoff MockPurchase
 * @author JaneLDQ
 * @date 2014/11/14
 */

package businesslogic.writeoffbl;

import util.ResultMessage;
import businesslogic.purchasebl.Purchase;

public class MockPurchase extends Purchase {

	public MockPurchase(){
	}
	
	public ResultMessage autoCreateWriteOffPurchase(String id) {
		return ResultMessage.SUCCESS;
	}

}
