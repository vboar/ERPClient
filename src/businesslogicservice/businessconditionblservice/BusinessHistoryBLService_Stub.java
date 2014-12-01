/**
 * 经营历程查看服务桩程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package businesslogicservice.businessconditionblservice;

import util.DocumentType;
import util.ResultMessage;
import vo.ClauseLineItemVO;
import vo.DocumentVO;
import vo.RequirementVO;

import java.util.ArrayList;

public class BusinessHistoryBLService_Stub implements DetailHistoryBLService{

	@Override
	public ArrayList<DocumentVO> show(RequirementVO vo) {
		ArrayList<DocumentVO> list = new ArrayList<DocumentVO>();
		if(vo.type!=DocumentType.CASH) return list;
		ArrayList<ClauseLineItemVO> clauseList = new ArrayList<ClauseLineItemVO>();
		clauseList.add(new ClauseLineItemVO("浩克",700,"无"));
//		list.add(new CashVO("XJFYD-20141024-00001","金刚狼","工商银行账户1",
//				clauseList,700,DocumentStatus.PASSED));
		return list;
	}

	@Override
	public ResultMessage exportExcel(String path) {
		if(path.equals("BusinessHitory.txt")) return ResultMessage.SUCCESS;
		return ResultMessage.FAILED;
	}

	
}
