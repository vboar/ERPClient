/**
 * 进货数据处理服务接口
 * @author oneoneO
 * @date 2014/10/26
 */
package dataservice.purchasedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PurchasePO;

public interface PurchaseDataService {

	public void insert(PurchasePO po)throws RemoteException;

	public void update(PurchasePO po)throws RemoteException;
	
	public ArrayList<PurchasePO> findByTime(String time1,String time2)throws RemoteException;
	
	public ArrayList<PurchasePO> findByCommodityName(String commodityName)throws RemoteException;
	
	public ArrayList<PurchasePO> findByCustomer(String customer)throws RemoteException;
	
	public ArrayList<PurchasePO> findBySalesman(String salesman)throws RemoteException;
	
	public ArrayList<PurchasePO> findByStorage(String Storage)throws RemoteException;

	public ArrayList<PurchasePO> show()throws RemoteException;
}
