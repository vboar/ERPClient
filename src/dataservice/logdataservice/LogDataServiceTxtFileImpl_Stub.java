/**
 * 日志数据服务桩程序
 * @author oneoneO
 * @date 2014/10/26
 */
package dataservice.logdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.LogPO;

public class LogDataServiceTxtFileImpl_Stub implements LogDataService{

	@Override
	public void insert(LogPO po) throws RemoteException {
		System.out.println("Insert Succeed!");
	}

	@Override
	public ArrayList<LogPO> find(String time1, String time2) throws RemoteException {
		ArrayList<LogPO> result=new ArrayList<LogPO>();
		System.out.println("Find Succeed!");
		return result;
	}

	@Override
	public ArrayList<LogPO> show() throws RemoteException {
		ArrayList<LogPO> result=new ArrayList<LogPO>();
		System.out.println("Show Succeed!");
		return result;
	}

}
