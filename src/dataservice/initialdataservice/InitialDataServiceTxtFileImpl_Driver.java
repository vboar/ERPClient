/**
 * 期初建账数据驱动程序
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.initialdataservice;

import java.rmi.RemoteException;

import po.InitialPO;

public class InitialDataServiceTxtFileImpl_Driver {

	public void drive(InitialDataService initialDataService) throws RemoteException {
		
		System.out.println("期初建账：\n");
		initialDataService.findById("INT-2014");
		initialDataService.show();
		initialDataService.insert(new InitialPO());
		
	}
	
}
