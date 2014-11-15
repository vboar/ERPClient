/**
 * 客户管理数据实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.customerdataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.CustomerPO;

public class CustomerDataServiceImpl extends UnicastRemoteObject implements CustomerDataService {

	/**
	 * 
	 */
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
	public ArrayList<CustomerPO> show() throws RemoteException {
		return null;
	}

}
