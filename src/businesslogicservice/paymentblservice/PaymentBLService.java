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
	 * 根据审批状态查找单据
	 * @param status
	 * @return
	 */
	public ArrayList<PaymentVO> findByStatus(DocumentStatus status);
	
	/**
	 * 根据时间查找单据
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<PaymentVO> show(String time1,String time2);

}
