/**
 * 现金费用单服务桩程序
 * @author JanelDQ
 *
 */
package businesslogicservice.paymentblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.CashVO;
import vo.ClauseLineItemVO;

public class CashBLService_Stub implements CashBLService {

	@Override
	public ResultMessage create(CashVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CashVO> show(String time1, String time2) {
		ArrayList<CashVO> list = new ArrayList<CashVO>();
		ArrayList<ClauseLineItemVO> clauseList = new ArrayList<ClauseLineItemVO>();
		clauseList.add(new ClauseLineItemVO("浩克",700,"无"));
		list.add(new CashVO("XJFYD-"+time1+"-00001","金刚狼","工商银行账户1",clauseList,700,DocumentStatus.PASSED));
		return list;
	}

	@Override
	public ResultMessage update(CashVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CashVO> findByStatus(DocumentStatus status) {
		ArrayList<CashVO> list = new ArrayList<CashVO>();
		ArrayList<ClauseLineItemVO> clauseList = new ArrayList<ClauseLineItemVO>();
		clauseList.add(new ClauseLineItemVO("浩克",700,"无"));
		list.add(new CashVO("XJFYD-20141025-00001","金刚狼","工商银行账户1",clauseList,700,status));
		return list;
	}


	@Override
	public ArrayList<CashVO> findById(String id) {
		ArrayList<CashVO> list = new ArrayList<CashVO>();
		ArrayList<ClauseLineItemVO> clauseList = new ArrayList<ClauseLineItemVO>();
		clauseList.add(new ClauseLineItemVO("浩克",700,"无"));
		list.add(new CashVO(id,"金刚狼","工商银行账户1",clauseList,700,DocumentStatus.PASSED));
		return list;
	}

}
