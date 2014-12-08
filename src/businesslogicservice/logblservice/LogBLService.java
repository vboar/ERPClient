/**
 * 系统日志业务逻辑接口
 */
package businesslogicservice.logblservice;

import vo.LogVO;

import java.util.ArrayList;

public interface LogBLService {

	/**
	 * 根据时间和操作员查找，操作员为null时查找全部
	 * @param time1
	 * @param time2
	 * @param operatorId
	 * @return
	 */
	public ArrayList<LogVO> find(String time1,String time2, String operatorId);

	/**
	 * 返回所有
	 * @return
	 */
	public ArrayList<LogVO> show();
}
