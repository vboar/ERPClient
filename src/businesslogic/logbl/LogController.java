/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.logbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.LogVO;
import businesslogicservice.logblservice.LogBLService;

public class LogController implements LogBLService {
	Log l=new Log();

	@Override
	public ResultMessage add(String content) {
		return l.add(content);
	}

	@Override
	public ArrayList<LogVO> findByTime(String time1, String time2) {
		return l.findByTime(time1,time2);
	}

	@Override
	public ArrayList<LogVO> show() {
		return l.show();
	}

}
