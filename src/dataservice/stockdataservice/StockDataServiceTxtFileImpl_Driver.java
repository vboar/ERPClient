/**
 * 库存盘点数据驱动程序
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.stockdataservice;

import java.rmi.RemoteException;

import po.StockPO;

public class StockDataServiceTxtFileImpl_Driver {

	public void drive(StockDataService stockDataService) throws RemoteException {
		
		System.out.println("库存盘点\n");
		stockDataService.insert(new StockPO());
		stockDataService.findByDate("2014/10/23", "00001");
		stockDataService.show();
		
	}
	
}
