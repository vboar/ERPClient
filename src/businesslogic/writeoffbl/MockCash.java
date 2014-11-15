/**
 * writeoff MockCash
 * @author JaneLDQ
 * @date 2014/11/15
 */
package businesslogic.writeoffbl;

import util.DocumentType;
import util.ResultMessage;
import vo.DocumentVO;
import businesslogic.paymentbl.Cash;

public class MockCash extends Cash implements CanWriteOff{

	public MockCash(){		
	}

	@Override
	public ResultMessage createWriteOffDocument(String id) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public DocumentType getType() {
		return DocumentType.CASH;
	}

	@Override
	public ResultMessage manualCreateDocument(DocumentVO vo) {
		return ResultMessage.SUCCESS;
	}

}
