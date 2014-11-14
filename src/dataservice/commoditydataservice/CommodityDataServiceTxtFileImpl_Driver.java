/**
 * 商品数据驱动程序
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.commoditydataservice;

import java.rmi.RemoteException;

import po.CommodityPO;

public class CommodityDataServiceTxtFileImpl_Driver {

	public void drive(CommodityDataService commodityDataService) throws RemoteException {
		System.out.println("商品\n");
		commodityDataService.insert(new CommodityPO());
		commodityDataService.delete(new CommodityPO());
		commodityDataService.update(new CommodityPO());
		commodityDataService.findById("00001-00001-00001");
		commodityDataService.findByName("飞利浦吊灯");
		commodityDataService.findByModel("FLP01");
		commodityDataService.show();
	}
}
