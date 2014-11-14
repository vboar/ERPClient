/**
 * 系统日志业务逻辑接口
 */
package businesslogicservice.logblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.LogVO;

public interface LogBLService {
	
	/**
	 * 查询系统日志
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<LogVO> show(String time1,String time2);
	
	/**
	 * 添加系统日志
	 * @param vo
	 * @return
	 */
	public ResultMessage add(LogVO vo);
	

}
