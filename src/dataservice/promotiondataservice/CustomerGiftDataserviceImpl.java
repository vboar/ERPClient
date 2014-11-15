package dataservice.promotiondataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.CustomerGiftPO;

public class CustomerGiftDataserviceImpl extends UnicastRemoteObject implements CustomerGiftDataservice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerGiftDataserviceImpl() throws RemoteException {
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

}
