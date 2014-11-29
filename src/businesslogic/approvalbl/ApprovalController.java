/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PaymentVO;
import vo.PresentVO;
import businesslogicservice.approvalblservice.ApprovalBLService;

public class ApprovalController implements ApprovalBLService{
	Approval a=new Approval();

	@Override
	public ResultMessage approvePayment(PaymentVO vo) {
		if(vo.approvalState==DocumentStatus.PASSED){
		a.sendMessage("单据"+vo.id+"审批通过！");
		}else{
			a.sendMessage("单据"+vo.id+"审批未通过！");
		}
		
		return a.approvePayment(vo.transferList,vo.id,vo.customerId,vo.total);
	}

	@Override
	public ArrayList<PresentVO> findPresent(int way, int status, String time1,
			String time2) {
		// TODO 自动生成的方法存根
		return null;
	}

}
