/**
 * 报溢报损单数据接口
 * @author Vboar
 * @date 2014/10/25
 */

package dataservice.exceptiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ExceptionPO;
import util.DocumentStatus;

public interface ExceptionDataService extends Remote {

	public void insert(ExceptionPO po) throws RemoteException;
	
	public void update(ExceptionPO po) throws RemoteException;
	
	public ArrayList<ExceptionPO> show(String time1, String time2) throws RemoteException;
	
	public ArrayList<ExceptionPO> findById(String id) throws RemoteException;
	
	public ArrayList<ExceptionPO> findByStatus(DocumentStatus status) throws RemoteException;
	
}
