/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.logbl;

import businesslogicservice.logblservice.LogBLService;
import vo.LogVO;

import java.util.ArrayList;

public class LogController implements LogBLService {

	Log log =new Log();

	@Override
	public ArrayList<LogVO> find(String time1, String time2, String operatorId) {
		return log.find(time1,time2,operatorId);
	}

	@Override
	public ArrayList<LogVO> show() {
		return log.show();
	}

}
