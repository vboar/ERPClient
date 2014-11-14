/**
 * writeoff mockPresent
 * @author JaneLDQ
 * @date 2014/11/14
 */

package businesslogic.writeoffbl;

import util.ResultMessage;
import businesslogic.presentbl.Present;

public class MockPresent extends Present {

	public MockPresent() {
	}

	public ResultMessage autoCreateWriteOffPresent(String id) {
		return ResultMessage.SUCCESS;
	}
}
