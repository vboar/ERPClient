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

public class UserDataServiceImpl extends UnicastRemoteObject implements UserDataService {

	public UserDataServiceImpl() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void insert(UserPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UserPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UserPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<UserPO> findById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserPO> findByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserPO> findByType(int type) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPO getById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserPO> show() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
