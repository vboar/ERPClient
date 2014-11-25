/**
 * 总价促销策略数据接口
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.promotiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.TotalGiftPO;

public interface TotalGiftDataService extends Remote {

	public void insert(TotalGiftPO po) throws RemoteException;
	
	public void update(TotalGiftPO po) throws RemoteException;
	
	public ArrayList<TotalGiftPO> show() throws RemoteException;
	
	public ArrayList<TotalGiftPO> findByValid() throws RemoteException;
	
	public TotalGiftPO getById(String id) throws RemoteException;
	
}
