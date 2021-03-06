/**
 * 现金费用单数据操作接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package dataservice.paymentdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CashPO;

public interface CashDataService extends Remote {

	public void insert(CashPO po) throws RemoteException;

	public void update(CashPO po) throws RemoteException;

	public ArrayList<CashPO> show() throws RemoteException;

	public ArrayList<CashPO> findById(String id) throws RemoteException;

	public ArrayList<CashPO> findByTime(String time1, String time2) throws RemoteException;
	
	public ArrayList<CashPO> findByStatus(int status) throws RemoteException;
	
	public CashPO getById(String id) throws RemoteException;
		
}
