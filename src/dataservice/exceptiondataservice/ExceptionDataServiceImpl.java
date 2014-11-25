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

public class ExceptionDataServiceImpl extends UnicastRemoteObject implements ExceptionDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(ExceptionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ExceptionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ExceptionPO> show(String time1, String time2)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ExceptionPO> findById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ExceptionPO> findByStatus(int status)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExceptionPO getById(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	


}
