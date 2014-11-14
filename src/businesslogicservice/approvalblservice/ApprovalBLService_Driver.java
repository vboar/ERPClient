/**
 * 单据审批逻辑驱动
 * date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.approvalblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.CashVO;
import vo.ClauseLineItemVO;

public class ApprovalBLService_Driver {
	public void drive(ApprovalBLService approvalBLService){
		ArrayList<ClauseLineItemVO> clauseList = new ArrayList<ClauseLineItemVO>();
		clauseList.add(new ClauseLineItemVO("浩克",700,"无"));
		ResultMessage result = approvalBLService.approve(new CashVO("XJFYD-20141025-00001","金刚狼","工商银行账户1",
				clauseList,700,DocumentStatus.PASSED));
		if (result == ResultMessage.SUCCESS) System.out.println("审批操作成功！\n");
		else System.out.println("审批操作失败失败！\n");
	}
}
