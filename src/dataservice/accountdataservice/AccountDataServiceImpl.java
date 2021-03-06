/**
 * 账户管理数据操作实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.accountdataservice;

import po.AccountPO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AccountDataServiceImpl extends UnicastRemoteObject implements AccountDataService {

	private static final long serialVersionUID = 1L;

	public AccountDataServiceImpl() throws RemoteException {
		super();
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
	public ArrayList<AccountPO> findByName(String name) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<AccountPO> show() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<AccountPO> showByInitial(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<AccountPO> findByAccount(String account)
			throws RemoteException {
		return null;
	}

	@Override
	public AccountPO getByAccount(String account) throws RemoteException {
		return null;
	}

}
