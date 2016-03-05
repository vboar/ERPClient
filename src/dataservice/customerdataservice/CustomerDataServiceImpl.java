/**
 * 客户管理数据操作实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.customerdataservice;

import po.CustomerPO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CustomerDataServiceImpl extends UnicastRemoteObject implements CustomerDataService {

	private static final long serialVersionUID = 1L;

	public CustomerDataServiceImpl() throws RemoteException {
		super();
	}
	
	@Override
	public void insert(CustomerPO po) throws RemoteException {
	}

	@Override
	public void delete(CustomerPO po) throws RemoteException {
	}

	@Override
	public void update(CustomerPO po) throws RemoteException {
	}

	@Override
	public ArrayList<CustomerPO> findByName(String name) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CustomerPO> findById(String id) throws RemoteException {
		return null;
	}
	
	@Override
	public CustomerPO getById(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CustomerPO> show() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CustomerPO> showByInitial(String id) throws RemoteException {
		return null;
	}

}
