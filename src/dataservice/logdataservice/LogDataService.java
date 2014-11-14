/**
 * 日志数据服务接口
 * @author oneoneO
 * @date 2014/10/26
 */
package dataservice.logdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.LogPO;

public interface LogDataService {

	public void insert(LogPO po)throws RemoteException;
	
	public ArrayList<LogPO> find(String time1,String time2)throws RemoteException;
	
	public ArrayList<LogPO> show()throws RemoteException;
}
