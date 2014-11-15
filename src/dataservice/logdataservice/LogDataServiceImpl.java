/**
 * 日志数据实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.logdataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.LogPO;

public class LogDataServiceImpl extends UnicastRemoteObject implements LogDataService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(LogPO po) throws RemoteException {
	}

	@Override
	public ArrayList<LogPO> find(String time1, String time2)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<LogPO> show() throws RemoteException {
		return null;
	}

}
