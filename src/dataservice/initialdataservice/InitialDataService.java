/**
 * 期初建账数据操作接口
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.initialdataservice;

import po.InitialPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InitialDataService extends Remote {
	
	public void insert(InitialPO po) throws RemoteException;
	
	public ArrayList<InitialPO> findById(String id) throws RemoteException;
	
	public InitialPO getById(String id) throws RemoteException;
	
	public ArrayList<InitialPO> show() throws RemoteException;

	public void saveEnd() throws RemoteException;

}
