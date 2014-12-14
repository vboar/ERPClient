package businesslogic.businessconditionbl;

import util.ResultMessage;
import util.Time;
import vo.BusinessConditionVO;
import vo.RequirementVO;
import businesslogicservice.businessconditionblservice.BusinessConditionBLService;

public class BusinessConditionController implements BusinessConditionBLService {

	BusinessCondition bc = new BusinessCondition();
	
	@Override
	public BusinessConditionVO show(String time1, String time2) {
		String content="查看"+time1+"到"+time2+"间的经营情况";
		bc.addLog(content);
		return bc.show(time1, time2);
	}

	@Override
	public ResultMessage exportExcel(String path,RequirementVO vo) {
		bc.addLog("导出经营情况表");
		return bc.exportExcel(path,vo);
	}

	@Override
	public String getDefaultPath() {
		String path="经营情况表"+Time.getCurrentTime()+".xls";
		return path;
	}

}
