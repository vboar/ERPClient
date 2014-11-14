/**
 * 商品数据桩程序
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.commoditydataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 商品数据桩程序
 * @author Vboar
 * @date 2014/10/26
 */
import po.CommodityPO;

public class CommodityDataServiceTxtFileImpl_Stub implements CommodityDataService {

	@Override
	public void insert(CommodityPO po) throws RemoteException {
		System.out.println("Insert Succeed!\n");
	}

	@Override
	public void delete(CommodityPO po) throws RemoteException {
		System.out.println("Delete Succeed!\n");
	}

	@Override
	public void update(CommodityPO po) throws RemoteException {
		System.out.println("Update Succeed!\n");
	}

	@Override
	public ArrayList<CommodityPO> findById(String id) throws RemoteException {
		System.out.println("FindById Succeed!\n");
		ArrayList<CommodityPO> list = new ArrayList<CommodityPO>();
		list.add(new CommodityPO());
		return list;
	}

	@Override
	public ArrayList<CommodityPO> findByName(String name)
			throws RemoteException {
		System.out.println("FindByName Succeed!\n");
		ArrayList<CommodityPO> list = new ArrayList<CommodityPO>();
		list.add(new CommodityPO());
		return list;
	}

	@Override
	public ArrayList<CommodityPO> findByModel(String model)
			throws RemoteException {
		System.out.println("FindByModel Succeed!\n");
		ArrayList<CommodityPO> list = new ArrayList<CommodityPO>();
		list.add(new CommodityPO());
		return list;
	}

	@Override
	public ArrayList<CommodityPO> show() throws RemoteException {
		System.out.println("Show Succeed!\n");
		ArrayList<CommodityPO> list = new ArrayList<CommodityPO>();
		list.add(new CommodityPO());
		return list;
	}

}
