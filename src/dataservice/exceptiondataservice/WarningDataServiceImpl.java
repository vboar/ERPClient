/**
 * 报警单数据操作实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.exceptiondataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.WarningPO;

public class WarningDataServiceImpl extends UnicastRemoteObject implements WarningDataService {

	private static final long serialVersionUID = 1L;

	public WarningDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(WarningPO po) throws RemoteException {
	}

	@Override
	public ArrayList<WarningPO> show(String time1, String time2)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<WarningPO> findById(String id) throws RemoteException {
		return null;
	}

	@Override
	public WarningPO getById(String id) throws RemoteException {
		return null;
	}
	
}
