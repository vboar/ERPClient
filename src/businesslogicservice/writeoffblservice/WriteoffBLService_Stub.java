/**
 * 红冲服务桩程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package businesslogicservice.writeoffblservice;

import util.ResultMessage;
import vo.DocumentVO;

public class WriteoffBLService_Stub implements WriteoffBLService{

	@Override
	public ResultMessage create(DocumentVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage copy(DocumentVO vo) {
		return ResultMessage.SUCCESS;
	}

}
