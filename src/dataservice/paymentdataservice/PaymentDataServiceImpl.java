package dataservice.paymentdataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.PaymentPO;

public class PaymentDataServiceImpl extends UnicastRemoteObject implements PaymentDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(PaymentPO po) throws RemoteException {
	}

	@Override
	public void update(PaymentPO po) throws RemoteException {
	}

	@Override
	public ArrayList<PaymentPO> show() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<PaymentPO> findById(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<PaymentPO> findByTime(String time1, String time2)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<PaymentPO> findByCustomer(String customerId)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<PaymentPO> findByOperator(String operator)
			throws RemoteException {
		return null;
	}

	@Override
	public PaymentPO getById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
