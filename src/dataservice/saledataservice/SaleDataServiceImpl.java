/**
 * 销售数据实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.saledataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.SalePO;

public class SaleDataServiceImpl extends UnicastRemoteObject implements SaleDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaleDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(SalePO po) throws RemoteException {
	}

	@Override
	public void update(SalePO po) throws RemoteException {
	}

	@Override
	public ArrayList<SalePO> findByTime(String time1, String time2)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<SalePO> findByCustomer(String customer)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<SalePO> findBySalesman(String salesman)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<SalePO> findByStorage(String Storage)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<SalePO> show() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<SalePO> findByStatus(int status) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalePO getById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
