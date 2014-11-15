/**
 * 用户数据实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.userdataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.UserPO;
import util.UserType;

public class UserDataServiceImpl extends UnicastRemoteObject implements UserDataService {

	private static final long serialVersionUID = 1L;

	public UserDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(UserPO po) throws RemoteException {
	}

	@Override
	public void delete(UserPO po) throws RemoteException {
	}

	@Override
	public void update(UserPO po) throws RemoteException {
	}

	@Override
	public ArrayList<UserPO> findById(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<UserPO> findByName(String name) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<UserPO> findByType(UserType type) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<UserPO> show() throws RemoteException {
		return null;
	}

}
