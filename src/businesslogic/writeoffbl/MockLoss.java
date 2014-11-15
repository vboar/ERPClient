/**
 * writeoff MockLoss
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.writeoffbl;

import util.DocumentType;
import util.ResultMessage;
import vo.DocumentVO;
import businesslogic.exceptionbl.Loss;

public class MockLoss extends Loss implements CanWriteOff{

	@Override
	public ResultMessage createWriteOffDocument(String id) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public DocumentType getType() {
		return DocumentType.LOSS;
	}

	@Override
	public ResultMessage manualCreateDocument(DocumentVO vo) {
		return ResultMessage.SUCCESS;
	}
	
}
