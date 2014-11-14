/**
 * 系统日志业务逻辑桩
 */
package businesslogicservice.logblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.LogVO;

public class LogBLService_Stub implements LogBLService {

	@Override
	public ArrayList<LogVO> show(String time1, String time2) {
		ArrayList<LogVO> logvolist=new ArrayList<LogVO>();
		logvolist.add(new LogVO("LOG-20141023-00001","23:47:12","CW001-精钢狼","新建一张付款单"));
		logvolist.add(new LogVO("LOG-20141025-00001","23:47:12","CW001-精钢狼","新建一张付款单"));
		
		return logvolist;
	}

	@Override
	public ResultMessage add(LogVO vo) {
		
		return ResultMessage.SUCCESS;
	}

}
