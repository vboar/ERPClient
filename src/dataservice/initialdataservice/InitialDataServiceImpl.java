/**
 * 期初建账数据操作实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.initialdataservice;

import po.InitialPO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class InitialDataServiceImpl extends UnicastRemoteObject implements InitialDataService {

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
	public InitialPO getById(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<InitialPO> show() throws RemoteException {
		return null;
	}

	@Override
	public void saveEnd() throws RemoteException {

	}


}
