/**
 * 报溢报损单数据操作接口
 * @author Vboar
 * @date 2014/10/25
 */

package dataservice.exceptiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ExceptionPO;

public interface ExceptionDataService extends Remote {

	public void insert(ExceptionPO po) throws RemoteException;
	
	public void update(ExceptionPO po) throws RemoteException;
	
	public ArrayList<ExceptionPO> show(String time1, String time2) throws RemoteException;
	
	public ArrayList<ExceptionPO> findById(String id) throws RemoteException;
	
	public ArrayList<ExceptionPO> findByStatus(int status) throws RemoteException;
	
	public ExceptionPO getById(String id) throws RemoteException;
	
}
