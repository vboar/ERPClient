/**
 * writeoff MockPayment
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.writeoffbl;

import util.ResultMessage;

public class MockPayment {

	
	public MockPayment() {
	}
	
	public ResultMessage autoCreateWriteOffPayment(String id) {
		return ResultMessage.SUCCESS;
	}
}
