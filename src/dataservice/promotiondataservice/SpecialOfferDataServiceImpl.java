package dataservice.promotiondataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.SpecialOfferPO;

public class SpecialOfferDataServiceImpl extends UnicastRemoteObject implements SpecialOfferDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpecialOfferDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(SpecialOfferPO po) throws RemoteException {
	}

	@Override
	public void update(SpecialOfferPO po) throws RemoteException {
	}

	@Override
	public ArrayList<SpecialOfferPO> show() throws RemoteException {
		return null;
	}

}
