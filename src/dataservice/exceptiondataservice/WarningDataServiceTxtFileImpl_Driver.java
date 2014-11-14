/**
 * 报警单数据驱动程序
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.exceptiondataservice;

import java.rmi.RemoteException;

import po.WarningPO;

public class WarningDataServiceTxtFileImpl_Driver {

	public void drive(WarningDataService warningDataService) throws RemoteException {
		
		System.out.println("报警单\n");
		warningDataService.insert(new WarningPO());
		warningDataService.show("2014/10/26/09/00/00", "2014/10/26/10/00/00");
		warningDataService.findById("WAR-20141023-00001");
	
	}
	
}
