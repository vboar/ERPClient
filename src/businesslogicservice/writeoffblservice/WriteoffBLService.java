/**
 * @author JaneLDQ
 * @date 2014/10/25
 */
package businesslogicservice.writeoffblservice;

import util.DocumentType;
import util.ResultMessage;
import vo.DocumentVO;

public interface WriteoffBLService {

	/**
	 * 红冲操作
	 * @param vo
	 * @return
	 */
	public ResultMessage create(DocumentType type, String id);
	
	/**
	 * 复制
	 * @param vo
	 * @return
	 */
	public ResultMessage copy(DocumentVO vo);
}