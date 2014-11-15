/**
 * 销售数据处理服务桩程序
 * @author oneoneO
 * @date 2014/10/26
 */
package dataservice.saledataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CommodityLineItemPO;
import po.SalePO;
import util.DocumentStatus;
import util.DocumentType;

public class SaleDataServiceTxtFileImpl_Stub implements SaleDataService{

	@Override
	public void insert(SalePO po) throws RemoteException {
		System.out.println("Insert Succeed!\n");
	}


	@Override
	public ArrayList<SalePO> findByTime(String time1, String time2)
			throws RemoteException {
		ArrayList<SalePO> result=new ArrayList<SalePO>();
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
		result.add(new SalePO("XSD-20141023-00001","17:54:54","00001","钢铁侠","美队","XS001-浩克","1",commodity,-350
                ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
		System.out.println("FindByTime succeed!\n");
		return result;
	}

	@Override
	public ArrayList<SalePO> findByCustomer(String customer)
			throws RemoteException {
		ArrayList<SalePO> result=new ArrayList<SalePO>();
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
		result.add(new SalePO("XSD-20141023-00001","17:54:54","00001","钢铁侠","美队","XS001-浩克","1",commodity,-350
                ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
		return result;
	}

	@Override
	public ArrayList<SalePO> findBySalesman(String salesman)
			throws RemoteException {
		ArrayList<SalePO> result=new ArrayList<SalePO>();
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
		result.add(new SalePO("XSD-20141023-00001","17:54:54","00001","钢铁侠","美队","XS001-浩克","1",commodity,-350
                ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
		System.out.println("FindBySalesman succeed!\n");
		return result;
	}

	@Override
	public ArrayList<SalePO> findByStorage(String Storage)
			throws RemoteException {
		ArrayList<SalePO> result=new ArrayList<SalePO>();
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
		result.add(new SalePO("XSD-20141023-00001","17:54:54","00001","钢铁侠","美队","XS001-浩克","1",commodity,-350
                ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
		System.out.println("FindByStorage succeed!\n");
		return result;
	}

	@Override
	public ArrayList<SalePO> show() throws RemoteException {
		ArrayList<SalePO> result=new ArrayList<SalePO>();
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
		result.add(new SalePO("XSD-20141023-00001","17:54:54","00001","钢铁侠","美队","XS001-浩克","1",commodity,-350
                ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
		System.out.println("Find succeed!\n");
		return result;
	}

	@Override
	public void update(SalePO po) throws RemoteException {
		System.out.println("Update Succeed!");
		
	}

}
