/**
 * 单据审批逻辑桩
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.approvalblservice;

import util.ResultMessage;
import vo.DocumentVO;
import vo.PaymentVO;

public class ApprovalBLService_Stub implements ApprovalBLService {

	@Override
	public ResultMessage approve(DocumentVO vo) {
		
		return ResultMessage.SUCCESS;
	}



	@Override
	public ResultMessage approvePayment(PaymentVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

}
