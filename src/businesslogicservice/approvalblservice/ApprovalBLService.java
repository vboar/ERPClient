/**
 * 单据审批逻辑接口
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.approvalblservice;

import util.ResultMessage;
import vo.DocumentVO;
import vo.PaymentVO;

public interface ApprovalBLService {
	/**
	 * 单据审批
	 * @param vo
	 * @return 审批结果的消息
	 */
	public ResultMessage approve(DocumentVO vo);
	
	/**
	 * 审批付款单
	 * @param vo
	 * @return
	 */
	public ResultMessage approvePayment(PaymentVO vo);
}
