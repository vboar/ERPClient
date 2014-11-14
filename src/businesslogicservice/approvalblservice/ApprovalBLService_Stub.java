/**
 * 单据审批逻辑桩
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.approvalblservice;

import util.ResultMessage;
import vo.DocumentVO;

public class ApprovalBLService_Stub implements ApprovalBLService {

	@Override
	public ResultMessage approve(DocumentVO vo) {
		
		return ResultMessage.SUCCESS;
	}

}
