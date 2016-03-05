/**
 * 报警单业务逻辑接口
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.exceptionblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.WarningVO;

public interface WarningBLService {

	/**
	 * 创建报警单
	 * @param vo
	 * @return 创建成功与否
	 */
	public ResultMessage create(WarningVO vo);
	
	/**
	 * 根据时间段查找报警单
	 * @param time1
	 * @param time2
	 * @return 报警单列表
	 */
	public ArrayList<WarningVO> show(String time1, String time2);
	
	public ArrayList<WarningVO> findById(String id);
	
	public WarningVO getByid(String id);
}
