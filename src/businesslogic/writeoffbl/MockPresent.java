/**
 * writeoff mockPresent
 * @author JaneLDQ
 * @date 2014/11/14
 */

package businesslogic.writeoffbl;

import util.DocumentType;
import util.ResultMessage;
import vo.DocumentVO;
import businesslogic.presentbl.Present;

public class MockPresent extends Present implements CanWriteOff{

	private DocumentType type = DocumentType.PRESENT;
	
	public MockPresent() {
	}

	@Override
	public ResultMessage createWriteOffDocument(String id) {
		return ResultMessage.SUCCESS;
	}

	public DocumentType getType() {
		return type;
	}

	@Override
	public ResultMessage manualCreateDocument(DocumentVO vo) {
		return ResultMessage.SUCCESS;
	}
	
}
