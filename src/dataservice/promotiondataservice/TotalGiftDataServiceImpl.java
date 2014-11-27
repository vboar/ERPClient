package dataservice.promotiondataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.TotalGiftPO;

public class TotalGiftDataServiceImpl extends UnicastRemoteObject implements TotalGiftDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TotalGiftDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(TotalGiftPO po) throws RemoteException {
	}

	@Override
	public void update(TotalGiftPO po) throws RemoteException {
	}

	@Override
	public ArrayList<TotalGiftPO> show() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<TotalGiftPO> findByValid(boolean valid) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TotalGiftPO getById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
