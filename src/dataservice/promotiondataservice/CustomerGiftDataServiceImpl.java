package dataservice.promotiondataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.CustomerGiftPO;

public class CustomerGiftDataServiceImpl extends UnicastRemoteObject implements CustomerGiftDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerGiftDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(CustomerGiftPO po) throws RemoteException {
	}

	@Override
	public void update(CustomerGiftPO po) throws RemoteException {
	}

	@Override
	public ArrayList<CustomerGiftPO> show() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CustomerGiftPO> findByValid(boolean valid)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerGiftPO getById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
