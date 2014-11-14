/**
 * 销售数据处理服务驱动程序
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

public class SaleDataServiceTxtFileImpl_Driver {

	public void drive(SaleDataService sds) throws RemoteException{
	    System.out.println("销售:"+"\n");

		
		ArrayList<CommodityLineItemPO> commodity=new ArrayList<CommodityLineItemPO>();
		commodity.add(new CommodityLineItemPO("00001-00001-00001-00001","飞利浦吊灯","FLP01",-5,-40,-200,"自提"));
	    sds.insert(new SalePO("XSD-20141023-00001","17:54:54","00001","钢铁侠","美队","XS001-浩克","1",commodity,-350
                ,-70,0,-280,"自提",DocumentStatus.NONCHECKED,false,DocumentType.SALE));
	    
	    sds.show();
	    
	    sds.findByCommodityName("飞利浦吊灯");
	    
	    sds.findByCustomer("钢铁侠");
	    
	    sds.findBySalesman("美队");
	    
	    sds.findByStorage("1");
	    
	    sds.findByTime("2014/10/20","2014/10/26");

	    sds.update(new SalePO("XSD-20141023-00001","17:54:54","00001","钢铁侠","美队","XS001-浩克","1",commodity,-350
                ,-70,0,-280,"自提",DocumentStatus.PASSED,false,DocumentType.SALE));
}
}
