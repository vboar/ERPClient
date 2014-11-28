/**
 * 账户管理数据操作实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.accountdataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.AccountPO;
import vo.AccountVO;

public class AccountDataServiceImpl extends UnicastRemoteObject implements AccountDataService {

	private static final long serialVersionUID = 1L;

	public AccountDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(AccountPO po) {
	}

	@Override
	public void delete(AccountPO po) {
	}

	@Override
	public void update(AccountPO po) {
	}

	@Override
	public ArrayList<AccountPO> findByName(String name) {
		return null;
	}

	@Override
	public AccountPO findByAccount(String account) {
		return null;
	}

	@Override
	public ArrayList<AccountPO> show() {
		return null;
	}

}
