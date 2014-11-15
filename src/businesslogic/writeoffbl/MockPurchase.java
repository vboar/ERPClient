/**
 * writeoff MockPurchase
 * @author JaneLDQ
 * @date 2014/11/14
 */

package businesslogic.writeoffbl;

import util.DocumentType;
import util.ResultMessage;
import vo.DocumentVO;
import businesslogic.purchasebl.Purchase;

public class MockPurchase extends Purchase implements CanWriteOff{

	public MockPurchase(){
	}
	
	@Override
	public ResultMessage createWriteOffDocument(String id) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public DocumentType getType() {
		return DocumentType.PURCHASE;
	}

	@Override
	public ResultMessage manualCreateDocument(DocumentVO vo) {
		return ResultMessage.SUCCESS;
	}

}
