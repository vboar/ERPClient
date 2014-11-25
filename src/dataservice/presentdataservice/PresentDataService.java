/**
 * 赠送单数据操作接口
 * @author JaneLDQ
 * @date 2014/10/26
 */
package dataservice.presentdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PresentPO;

public interface PresentDataService extends Remote {
	
	public void insert(PresentPO po) throws RemoteException;
	
	public void update(PresentPO po) throws RemoteException;
	
	public ArrayList<PresentPO> findById(String id);
	
	public ArrayList<PresentPO> showByTime(String time1,String time2);
	
}
