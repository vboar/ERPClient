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
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(AccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(AccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<AccountPO> findByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountPO findById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AccountPO> show() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}



}
