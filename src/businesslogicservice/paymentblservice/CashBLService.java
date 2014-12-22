/**
 * 现金费用单服务接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package businesslogicservice.paymentblservice;

import util.DocumentStatus;
import util.ResultMessage;
import vo.CashVO;
import vo.PaymentVO;

import java.util.ArrayList;

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

	public String createId();

	public CashVO getById(String id);

}
