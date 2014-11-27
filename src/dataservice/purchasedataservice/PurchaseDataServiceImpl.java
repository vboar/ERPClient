/**
 * 进货数据实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.purchasedataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.PurchasePO;

public class PurchaseDataServiceImpl extends UnicastRemoteObject implements PurchaseDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PurchaseDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(PurchasePO po) throws RemoteException {
	}

	@Override
	public void update(PurchasePO po) throws RemoteException {
	}

	@Override
	public ArrayList<PurchasePO> findByTime(String time1, String time2)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<PurchasePO> findByCustomer(String customer)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<PurchasePO> findByStorage(String storage)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<PurchasePO> show() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<PurchasePO> findByStatus(int status)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchasePO getById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
