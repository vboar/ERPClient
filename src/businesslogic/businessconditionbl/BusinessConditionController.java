package businesslogic.businessconditionbl;

import util.ResultMessage;
import vo.BusinessConditionVO;
import businesslogicservice.businessconditionblservice.BusinessConditionBLService;

public class BusinessConditionController implements BusinessConditionBLService {

	BusinessCondition bc = new BusinessCondition();
	
	@Override
	public BusinessConditionVO show(String time1, String time2) {
		return null;
	}

	@Override
	public ResultMessage exportExcel(String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
