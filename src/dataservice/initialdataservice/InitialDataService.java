/**
 * 期初建账数据接口
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.initialdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.InitialPO;

public interface InitialDataService extends Remote {
	
	public void insert(InitialPO po) throws RemoteException;
	
	public ArrayList<InitialPO> findById(String id) throws RemoteException;
	
	public ArrayList<InitialPO> show() throws RemoteException;

}
