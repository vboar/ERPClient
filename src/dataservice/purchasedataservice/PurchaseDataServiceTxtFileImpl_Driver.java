/**
 * 进货数据处理服务驱动程序
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

public class PurchaseDataServiceTxtFileImpl_Driver {

	public void drive(PurchaseDataService pds) throws RemoteException{
		System.out.println("进货：\n");
		
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",50,20,1000,"自提"));
		pds.insert(new PurchasePO("JHD-20141023-00001","17:54:54","00002","雷神托尔","XS001-浩克","1",commodity,1750,
	    		"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
		
		pds.show();
		
		pds.findByCustomer("钢铁侠");
		
		pds.findBySalesman("美队");
		
		pds.findByStorage("1");
		
		pds.findByTime("2014/10/10", "2014/10/26");
		
		pds.update(new PurchasePO("JHD-20141023-00001","17:54:54","00002","雷神托尔","XS001-浩克","1",commodity,1750,
	    		"自提",DocumentStatus.PASSED,false,DocumentType.SALE));
		
	}
}
