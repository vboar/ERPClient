/**
 * 促销策略数据桩
 * @date 2014/10/26
 * @author chengcheng
 */

package dataservice.promotiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CustomerGiftPO;
import po.SpecialOfferPO;
import po.TotalGiftPO;

public class PromotionDataServiceTxtFileImpl_Stub implements
		PromotionDataService {

	@Override
	public void insert(CustomerGiftPO po) throws RemoteException {
		System.out.println("添加成功");

	}

	@Override
	public void insert(TotalGiftPO po) throws RemoteException {
		System.out.println("添加成功");

	}

	@Override
	public void insert(SpecialOfferPO po) throws RemoteException {
		System.out.println("添加成功");

	}

	@Override
	public void update(CustomerGiftPO po) throws RemoteException {
		System.out.println("更新成功");

	}

	@Override
	public void update(TotalGiftPO po) throws RemoteException {
		System.out.println("更新成功");

	}

	@Override
	public void update(SpecialOfferPO po) throws RemoteException {
		System.out.println("更新成功");

	}

	@Override
	public ArrayList<CustomerGiftPO> findByVip() throws RemoteException {
		ArrayList<CustomerGiftPO> voList=new ArrayList<CustomerGiftPO>();
		voList.add(new CustomerGiftPO("00001",5,null,100.0,10.0,"2014/10/23","2014/10/25",false));
		
		return voList;
	}

	@Override
	public ArrayList<SpecialOfferPO> findByCommodity() throws RemoteException {
		ArrayList<SpecialOfferPO> voList=new ArrayList<SpecialOfferPO>();
		voList.add(new SpecialOfferPO("00002", null, 100.0, "2014/10/23","2014/10/25",false));
		
		return voList;
	}

	@Override
	public ArrayList<TotalGiftPO> findByPrice() throws RemoteException {
		ArrayList<TotalGiftPO> voList=new ArrayList<TotalGiftPO>();
		voList.add(new TotalGiftPO("00002",10000.0,null,10.0,10.0,"2014/10/23","2014/10/25",false));
		return voList;
	}
	

}
