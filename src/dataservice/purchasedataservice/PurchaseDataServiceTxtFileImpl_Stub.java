/**
 * 进货数据处理服务桩程序
 * @author oneoneO
 * @date 2014/10/26
 */
package dataservice.purchasedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CommodityLineItemPO;
import po.PurchasePO;
import util.DocumentStatus;
import util.DocumentType;


public class PurchaseDataServiceTxtFileImpl_Stub implements PurchaseDataService{

	@Override
	public void insert(PurchasePO po) throws RemoteException {
		System.out.println("Insert Succeed!");		
	}


	@Override
	public ArrayList<PurchasePO> findByTime(String time1, String time2)
			throws RemoteException {
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,20,1000,"自提"));
	    ArrayList<PurchasePO> list=new ArrayList<PurchasePO>();
	    list.add(new PurchasePO("JHD-20141023-00001","17:54:54","00002","雷神托尔","XS001-浩克","1",commodity,1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    System.out.println("FindByTime Succeed!");
	    return list;
	}

	@Override
	public ArrayList<PurchasePO> findByCustomer(String customer)
			throws RemoteException {
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,20,1000,"自提"));
	    ArrayList<PurchasePO> list=new ArrayList<PurchasePO>();
	    list.add(new PurchasePO("JHD-20141023-00001","17:54:54","00002","雷神托尔","XS001-浩克","1",commodity,1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    System.out.println("FindByCustomer Succeed!");
	    return list;
	}

	@Override
	public ArrayList<PurchasePO> findBySalesman(String salesman)
			throws RemoteException {
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,20,1000,"自提"));
	    ArrayList<PurchasePO> list=new ArrayList<PurchasePO>();
	    list.add(new PurchasePO("JHD-20141023-00001","17:54:54","00002","雷神托尔","XS001-浩克","1",commodity,1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    System.out.println("FindBySalesman Succeed!");
	    return list;
	}

	@Override
	public ArrayList<PurchasePO> findByStorage(String Storage)
			throws RemoteException {
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,20,1000,"自提"));
	    ArrayList<PurchasePO> list=new ArrayList<PurchasePO>();
	    list.add(new PurchasePO("JHD-20141023-00001","17:54:54","00002","雷神托尔","XS001-浩克","1",commodity,1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    System.out.println("FindByStorage Succeed!");
	    return list;
	}

	@Override
	public ArrayList<PurchasePO> show() throws RemoteException {
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,20,1000,"自提"));
	    ArrayList<PurchasePO> list=new ArrayList<PurchasePO>();
	    list.add(new PurchasePO("JHD-20141023-00001","17:54:54","00002","雷神托尔","XS001-浩克","1",commodity,1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	 
	    System.out.println("Find Succeed!");
	    return list;
	}


	@Override
	public void update(PurchasePO po) throws RemoteException {
		System.out.println("Update Succeed!");
		
	}

}
