/**
 * writeoff mocksale
 * @author JaneLDQ
 * @date 2014/11/14
 */

package businesslogic.writeoffbl;

import util.DocumentType;
import util.ResultMessage;
import vo.DocumentVO;
import businesslogic.salebl.Sale;

public class MockSale extends Sale implements CanWriteOff{

	@Override
	public ResultMessage createWriteOffDocument(String id) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public DocumentType getType() {
		return DocumentType.SALE;
	}

	@Override
	public ResultMessage manualCreateDocument(DocumentVO vo) {
		return ResultMessage.SUCCESS;
	}

}
