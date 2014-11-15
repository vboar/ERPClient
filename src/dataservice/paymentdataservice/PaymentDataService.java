/**
 * 收付款单数据处理接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package dataservice.paymentdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PaymentPO;

public interface PaymentDataService extends Remote {

	public void insert(PaymentPO po) throws RemoteException;

	public void update(PaymentPO po) throws RemoteException;

	public ArrayList<PaymentPO> show() throws RemoteException;

	public ArrayList<PaymentPO> findById(String id) throws RemoteException;

	public ArrayList<PaymentPO> findByTime(String time1,String time2) throws RemoteException;

	public ArrayList<PaymentPO> findByCustomer(String customerId) throws RemoteException;

	public ArrayList<PaymentPO> findByOperator(String operator) throws RemoteException;
		
}
