/**
 * 现金费用单数据处理实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.paymentdataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.CashPO;

public class CashDataServiceImpl extends UnicastRemoteObject implements CashDataService {
	
	private static final long serialVersionUID = 1L;

	public CashDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(CashPO po) throws RemoteException {
	}

	@Override
	public void update(CashPO po) throws RemoteException {
	}

	@Override
	public ArrayList<CashPO> show() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CashPO> findById(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CashPO> findByTime(String time1, String time2)
			throws RemoteException {
		return null;
	}

	@Override
	public CashPO getById(String id) throws RemoteException {
		return null;
	}

}
