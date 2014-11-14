/**
 * 查看经营情况服务桩程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package businesslogicservice.businessconditionblservice;

import util.ResultMessage;
import vo.BusinessConditionVO;

public class BusinessConditionBLService_Stub implements
		BusinessConditionBLService {

	public BusinessConditionVO show(String time1, String time2) {		
		return new BusinessConditionVO(10000, 200, 200, 300,8000, 
				2000, 4000,100, 200,4300,4400);
	}

	@Override
	public ResultMessage exportExcel(String path) {
		if(path.equals("BusinessCondition.txt")) return ResultMessage.SUCCESS;
		return ResultMessage.FAILED;
	}

}
