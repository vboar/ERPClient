/**
 * 商品管理数据操作接口
 * @author Vboar
 * @date 2014/10/25
 */
package dataservice.commoditydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CommodityPO;

public interface CommodityDataService extends Remote {

	public void insert(CommodityPO po) throws RemoteException;
	
	public void delete(CommodityPO po) throws RemoteException;
	
	public void update(CommodityPO po) throws RemoteException;
	
	public ArrayList<CommodityPO> findById(String id) throws RemoteException;
	
	public ArrayList<CommodityPO> findByName(String name) throws RemoteException;
	
	public ArrayList<CommodityPO> findByModel(String model) throws RemoteException;
	
	public CommodityPO getById(String id) throws RemoteException;
	
	public ArrayList<CommodityPO> show() throws RemoteException;
	
}
