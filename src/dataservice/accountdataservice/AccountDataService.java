/**
 * 账户管理数据层操作接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package dataservice.accountdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.AccountPO;

public interface AccountDataService extends Remote {
	
	public void insert(AccountPO po) throws RemoteException;

	public void delete(AccountPO po) throws RemoteException;

	public void update(AccountPO po) throws RemoteException;
	
	public ArrayList<AccountPO> findByName(String name) throws RemoteException;
	
	public AccountPO findById(String id) throws RemoteException;

	public ArrayList<AccountPO> show() throws RemoteException;

}
