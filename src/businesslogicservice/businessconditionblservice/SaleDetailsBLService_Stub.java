/**
 * 销售明细查看桩程序
 * @author JaneLDQ
 * @date 2014/10/25
 */
package businesslogicservice.businessconditionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.DocumentVO;
import vo.RequirementVO;

public class SaleDetailsBLService_Stub implements DetailHistoryBLService {

	@Override
	public ArrayList<DocumentVO> show(RequirementVO vo) {
		// TODO 待SaleVO实现DocumentVO接口，将DocumentVO替换成SaleVO
		ArrayList<DocumentVO> list = new ArrayList<DocumentVO>();
		return list;
	}

	@Override
	public ResultMessage exportExcel(String path) {
		if(path.equals("SaleDetails.txt")) return ResultMessage.SUCCESS;
		return ResultMessage.FAILED;
	}

}
