/**
 * 销售数据处理服务接口
 * @author oneoneO
 * @date 2014/10/26
 */
package dataservice.saledataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.SalePO;

public interface SaleDataService extends Remote {
 
	public void insert(SalePO po) throws RemoteException;
	
	public void update(SalePO po) throws RemoteException;
	
	public ArrayList<SalePO> findByTime(String time1, String time2) throws RemoteException;

	public ArrayList<SalePO> findByCustomer(String customer) throws RemoteException;
	
	public ArrayList<SalePO> findBySalesman(String salesman) throws RemoteException;
	
	public ArrayList<SalePO> findByStorage(String storage) throws RemoteException;
	
	public ArrayList<SalePO> findByStatus(int status) throws RemoteException;

	public ArrayList<SalePO> show() throws RemoteException;
	
	public SalePO getById(String id) throws RemoteException;
	
}
