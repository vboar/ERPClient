package businesslogic.businessconditionbl;

import util.ResultMessage;
import vo.BusinessConditionVO;
import vo.RequirementVO;
import businesslogicservice.businessconditionblservice.BusinessConditionBLService;

public class BusinessConditionController implements BusinessConditionBLService {

	BusinessCondition bc = new BusinessCondition();
	
	@Override
	public BusinessConditionVO show(String time1, String time2) {
		return null;
	}

	@Override
	public ResultMessage exportExcel(String path,RequirementVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
