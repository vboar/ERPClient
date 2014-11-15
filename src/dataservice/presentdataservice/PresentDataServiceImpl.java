package dataservice.presentdataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.PresentPO;

public class PresentDataServiceImpl extends UnicastRemoteObject implements PresentDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PresentDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(PresentPO po) throws RemoteException {
	}

	@Override
	public void update(PresentPO po) throws RemoteException {
	}

	@Override
	public ArrayList<PresentPO> show() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<PresentPO> findById(String id) {
		return null;
	}

	@Override
	public ArrayList<PresentPO> findByOperator(String operator) {
		return null;
	}

	@Override
	public ArrayList<PresentPO> findByTime(String time1, String time2) {
		return null;
	}

}
