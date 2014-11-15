/**
 * MockMessage
 * @author oenoenO
 * @date 2014/11/15
 */
package businesslogic.approvalbl;

import util.ResultMessage;
import businesslogic.messagebl.Message;

public class MockMessage extends Message{

	public ResultMessage sendMessage(String content){
		return ResultMessage.SUCCESS;
	}
}
