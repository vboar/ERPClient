/**
 * 单据审批逻辑接口
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.approvalblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.DocumentVO;
import vo.PaymentVO;
import vo.PresentVO;

public interface ApprovalBLService {
	
	/**
	 * 查找赠送单据
	 * @param way 0-所有，1-根据状态，2-根据日期时间段
	 * @param status
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<PresentVO> findPresent(int way, int status, String time1, String time2);
	
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
