/**
 * writeoff MockOver
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.writeoffbl;

import businesslogic.exceptionbl.Overflow;
import util.DocumentType;
import util.ResultMessage;
import vo.DocumentVO;

public class MockOver extends Overflow implements CanWriteOff{
	
	public MockOver() {
	}


	@Override
	public ResultMessage createWriteOffDocument(String id) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public DocumentType getType() {
		return DocumentType.OVERFLOW;
	}


	@Override
	public ResultMessage manualCreateDocument(DocumentVO vo) {
		return ResultMessage.SUCCESS;
	}

	
}