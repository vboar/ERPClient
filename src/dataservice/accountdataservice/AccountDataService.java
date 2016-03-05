/**
 * 账户管理数据操作接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package dataservice.accountdataservice;

import po.AccountPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AccountDataService extends Remote {
	
	public void insert(AccountPO po) throws RemoteException;

	public void delete(AccountPO po) throws RemoteException;

	public void update(AccountPO po) throws RemoteException;
	
	public ArrayList<AccountPO> findByName(String name) throws RemoteException;
	
	public ArrayList<AccountPO> findByAccount(String account) throws RemoteException;
	
	public AccountPO getByAccount(String account) throws RemoteException;

	public ArrayList<AccountPO> show() throws RemoteException;

	public ArrayList<AccountPO> showByInitial(String id) throws RemoteException;

}
