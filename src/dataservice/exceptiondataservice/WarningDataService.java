/**
 * 报警单数据操作实现
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.exceptiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.WarningPO;

public interface WarningDataService extends Remote {

	public void insert(WarningPO po) throws RemoteException;

	public ArrayList<WarningPO> show(String time1, String time2) throws RemoteException;

	public ArrayList<WarningPO> findById(String id) throws RemoteException;
	
	public WarningPO getById(String id) throws RemoteException;
	
}
