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
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(PresentPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PresentPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<PresentPO> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PresentPO> showByTime(String time1, String time2) {
		// TODO Auto-generated method stub
		return null;
	}



}
