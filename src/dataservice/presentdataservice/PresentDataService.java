/**
 * 赠送单数据处理接口
 * @author JaneLDQ
 * @date 2014/10/26
 */
package dataservice.presentdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PresentPO;

public interface PresentDataService {
	
	public void insert(PresentPO po) throws RemoteException;
	
	public void update(PresentPO po) throws RemoteException;

	public ArrayList<PresentPO> show() throws RemoteException;
	
	public ArrayList<PresentPO> findById(String id);
	
	public ArrayList<PresentPO> findByOperator(String operator);
	
	public ArrayList<PresentPO> findByTime(String time1,String time2);
	
}
