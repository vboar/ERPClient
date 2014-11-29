/**
 * 报溢报损单业务逻辑接口
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.exceptionblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.ExceptionVO;

public interface ExceptionBLService {

	/**
	 * 创建报溢报损单
	 * @param vo
	 * @return 创建成功与否消息
	 */
	public ResultMessage create(ExceptionVO vo);
	
	/**
	 * 根据时间段查找报溢报损单
	 * @param time1
	 * @param time2
	 * @return 报溢报损单列表
	 */
	public ArrayList<ExceptionVO> show(String time1, String time2);
	
	/**
	 * 根据单据状态查找报溢报损单
	 * @param status
	 * @return 报溢报损单列表
	 */
	public ArrayList<ExceptionVO> findByStatus(DocumentStatus status);
	
	/**
	 * 更新报溢报损单信息
	 * @param vo
	 * @return 更新成功与否
	 */
	public ResultMessage update(ExceptionVO vo);
	
}
