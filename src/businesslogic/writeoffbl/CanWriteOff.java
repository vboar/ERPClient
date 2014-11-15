/**
 * 红冲接口
 * @author JaneLDQ
 * @date 2014/11/15
 */
package businesslogic.writeoffbl;

import util.DocumentType;
import util.ResultMessage;
import vo.DocumentVO;

public interface CanWriteOff {

	public ResultMessage createWriteOffDocument(String id);
	
	public ResultMessage manualCreateDocument(DocumentVO vo);
	
	public DocumentType getType();
	
}
