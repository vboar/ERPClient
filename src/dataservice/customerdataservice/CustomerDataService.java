/**
 * 客户管理数据操作接口
 * @date 2014/10/26
 * @author chengcheng
 */
package dataservice.customerdataservice;

import po.CustomerPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CustomerDataService extends Remote {
	
	public void insert(CustomerPO po) throws RemoteException;
	
	public void delete(CustomerPO po) throws RemoteException;
	
	public void update(CustomerPO po) throws RemoteException;
	
	public ArrayList<CustomerPO> findByName(String name) throws RemoteException;
	
	public ArrayList<CustomerPO> findById(String id) throws RemoteException;
	
	public CustomerPO getById(String id) throws RemoteException;
	
	public ArrayList<CustomerPO> show() throws RemoteException;

	public ArrayList<CustomerPO> showByInitial(String id) throws RemoteException;
}
