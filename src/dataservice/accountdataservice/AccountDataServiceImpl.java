/**
 * 账户管理数据实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.accountdataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.AccountPO;

public class AccountDataServiceImpl extends UnicastRemoteObject implements AccountDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountDataServiceImpl() throws RemoteException {
		super();
	}
	
	@Override
	public ArrayList<AccountPO> find(String name) throws RemoteException {
		return null;
	}

	@Override
	public void insert(AccountPO po) throws RemoteException {
	}

	@Override
	public void delete(AccountPO po) throws RemoteException {
	}

	@Override
	public void update(AccountPO po) throws RemoteException {
	}

	@Override
	public ArrayList<AccountPO> show() throws RemoteException {
		return null;
	}

}
