/**
 * 报警单数据接口
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.exceptiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.WarningPO;

public interface WarningDataService {

	public void insert(WarningPO po) throws RemoteException;

	public ArrayList<WarningPO> show(String time1, String time2) throws RemoteException;

	public ArrayList<WarningPO> findById(String id) throws RemoteException;
	
}
