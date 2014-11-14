/**
 * 收付款服务接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package businesslogicservice.paymentblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PaymentVO;

public interface PaymentBLService {
	/**
	 * 创建收付款单
	 * @param vo
	 * @return
	 */
	public ResultMessage create(PaymentVO vo);
	
	/**
	 * 显示一段时间内的收付款单记录
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<PaymentVO> show(String time1, String time2);
	
	/**
	 * 更新收付款单审批状态
	 * @param vo
	 * @return
	 */
	public ResultMessage update(PaymentVO vo);

	/**
	 * 根据审批状态查找单据
	 * @param status
	 * @return
	 */
	public ArrayList<PaymentVO> findByStatus(DocumentStatus status);
	
	/**
	 * 根据编号模糊查找单据
	 * @param id
	 * @return
	 */
	public ArrayList<PaymentVO> findById(String id);
}
