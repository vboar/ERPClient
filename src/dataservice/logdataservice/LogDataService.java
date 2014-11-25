/**
 * 日志数据层操作接口
 * @author oneoneO
 * @date 2014/10/26
 */
package dataservice.logdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.LogPO;

public interface LogDataService extends Remote {

	public void insert(LogPO po) throws RemoteException;
	
	public ArrayList<LogPO> find(String time1, String time2) throws RemoteException;
	
	public ArrayList<LogPO> show() throws RemoteException;
}
