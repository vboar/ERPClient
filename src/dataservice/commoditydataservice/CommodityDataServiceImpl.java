/**
 * 商品管理数据操作实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.commoditydataservice;

import po.CommodityPO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CommodityDataServiceImpl extends UnicastRemoteObject implements CommodityDataService {
	
	private static final long serialVersionUID = 1L;

	public CommodityDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(CommodityPO po) throws RemoteException {
	}

	@Override
	public void delete(CommodityPO po) throws RemoteException {
	}

	@Override
	public void update(CommodityPO po) throws RemoteException {
	}

	@Override
	public ArrayList<CommodityPO> findById(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CommodityPO> findByName(String name) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CommodityPO> findByModel(String model) throws RemoteException {
		return null;
	}
	
	@Override
	public CommodityPO getById(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CommodityPO> show() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CommodityPO> showByInitial(String id) throws RemoteException {
		return null;
	}

}
