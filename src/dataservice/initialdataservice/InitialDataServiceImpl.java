/**
 * 期初建账数据实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.initialdataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.InitialPO;

public class InitialDataServiceImpl extends UnicastRemoteObject implements InitialDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InitialDataServiceImpl() throws RemoteException {
		super();
	}
	
	@Override
	public void insert(InitialPO po) throws RemoteException {
	}

	@Override
	public ArrayList<InitialPO> findById(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<InitialPO> show() throws RemoteException {
		return null;
	}

}
