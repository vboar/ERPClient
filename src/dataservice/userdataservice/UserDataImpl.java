/**
 * 客户端RMI
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.userdataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import po.UserPO;
import util.UserType;

public class UserDataImpl extends UnicastRemoteObject implements UserDataService {

	private static final long serialVersionUID = 1L;

	protected UserDataImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(UserPO po) throws RemoteException {
		System.out.println("insert a UserPO");
	}

	@Override
	public void delete(UserPO po) throws RemoteException {
		System.out.println("delete a UserPO");
	}

	@Override
	public void update(UserPO po) throws RemoteException {
		System.out.println("update a UserPO");
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
