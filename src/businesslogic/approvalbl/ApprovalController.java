/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import util.DocumentStatus;
import util.ResultMessage;
import vo.DocumentVO;
import vo.PaymentVO;
import businesslogic.messagebl.MessageController;
import businesslogicservice.approvalblservice.ApprovalBLService;

public class ApprovalController implements ApprovalBLService{
	Approval a=new Approval();

	@Override
	public ResultMessage approve(DocumentVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage approvePayment(PaymentVO vo) {
		if(vo.approvalState==DocumentStatus.PASSED){
		a.sendMessage("单据"+vo.id+"审批通过！");
		}else{
			a.sendMessage("单据"+vo.id+"审批未通过！");
		}
		
		return a.approvePayment(vo.transferList,vo.id,vo.customerId,vo.total);
	}

}
