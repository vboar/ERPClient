/**
 * 赠送单数据操作接口
 * @author JaneLDQ
 * @date 2014/10/26
 */
package dataservice.presentdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PresentPO;

public interface PresentDataService extends Remote {
	
	public void insert(PresentPO po) throws RemoteException;
	
	public void update(PresentPO po) throws RemoteException;
	
	public ArrayList<PresentPO> findById(String id) throws RemoteException;
	
	public ArrayList<PresentPO> findByTime(String time1,String time2) throws RemoteException;
	
	public ArrayList<PresentPO> findByStatus(int status) throws RemoteException;
	
	public ArrayList<PresentPO> findByCustomerId(String customerId) throws RemoteException;
	
	public PresentPO getById(String id) throws RemoteException;
	
}
