/**
 * 收付款服务接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package businesslogicservice.paymentblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PaymentVO;
import vo.TransferLineItemVO;

public interface PaymentBLService {
	/**
	 * 创建收付款单
	 * @param vo
	 * @return
	 */
	public ResultMessage create(PaymentVO vo) throws RemoteException;
	
	/**
	 * 显示一段时间内的收付款单记录
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<PaymentVO> show() throws RemoteException;
	
	/**
	 * 更新收付款单审批状态
	 * @param vo
	 * @return
	 */
	public ResultMessage update(ArrayList<TransferLineItemVO> transferlist,String id,String customerId,double total) throws RemoteException;

	/**
	 * 根据审批状态查找单据
	 * @param status
	 * @return
	 */
	public ArrayList<PaymentVO> findByStatus(DocumentStatus status) throws RemoteException;
	
	/**
	 * 根据编号模糊查找单据
	 * @param id
	 * @return
	 */
	public ArrayList<PaymentVO> findById(String id) throws RemoteException;
	
	/**
	 * 根据时间查找单据
	 * @param time1
	 * @param time2
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<PaymentVO> findByTime(String time1,String time2) throws RemoteException;
	
	/**
	 * 根据客户查找单据
	 * @param customerId
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<PaymentVO> findByCustomer(String customerId) throws RemoteException;
	
	/**
	 * 根据操作员查找单据
	 * @param operator
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<PaymentVO> findByOperator(String operator) throws RemoteException;
}
