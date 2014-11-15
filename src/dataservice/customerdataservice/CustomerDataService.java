/**
 * 客户管理数据接口
 * @date 2014/10/26
 * @author chengcheng
 */
package dataservice.customerdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CustomerPO;

public interface CustomerDataService extends Remote {
	
	public void insert(CustomerPO po) throws RemoteException;
	
	public void delete(CustomerPO po) throws RemoteException;
	
	public void update(CustomerPO po) throws RemoteException;
	
	public ArrayList<CustomerPO> findByName(String name) throws RemoteException;
	
	public ArrayList<CustomerPO> findById(String id) throws RemoteException;
	
	public ArrayList<CustomerPO> show() throws RemoteException;
}
