/**
 * @author JaneLDQ
 * @date 2014/10/25
 */
package businesslogicservice.writeoffblservice;

import util.DocumentType;
import util.ResultMessage;

public interface WriteoffBLService {

	/**
	 * 红冲单据
	 * @param type
	 * @param id
	 * @return
	 */
	public ResultMessage create(DocumentType type,String id);
}