/**
 * 现金费用单服务接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package businesslogicservice.paymentblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.CashVO;

public interface CashBLService {

	/**
	 * 创建现金费用单
	 * @param vo
	 * @return
	 */
	public ResultMessage create(CashVO vo);
	
	/**
	 * 显示一段时间内的现金费用单记录
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<CashVO> findByTime(String time1,String time2);

	/**
	 * 根据审批状态查找单据
	 * @param status
	 * @return
	 */
	public ArrayList<CashVO> findByStatus(DocumentStatus status);
	
	public ArrayList<CashVO> findById(String id);
	
	public ArrayList<CashVO> show();

}
