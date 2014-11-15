/**
 * 报溢报损单数据实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.exceptiondataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.ExceptionPO;
import util.DocumentStatus;

public class ExceptionDataServiceImpl extends UnicastRemoteObject implements ExceptionDataService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(ExceptionPO po) throws RemoteException {
	}

	@Override
	public void update(ExceptionPO po) throws RemoteException {
	}

	@Override
	public ArrayList<ExceptionPO> show(String time1, String time2)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<ExceptionPO> findById(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<ExceptionPO> findByStatus(DocumentStatus status)
			throws RemoteException {
		return null;
	}

}
