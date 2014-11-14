/**
 * 报溢报损单数据驱动程序
 */
package dataservice.exceptiondataservice;

import java.rmi.RemoteException;

import po.ExceptionPO;
import util.DocumentStatus;

public class ExceptionDataServiceTxtFileImpl_Driver {

	public void drive(ExceptionDataService exceptionDataService) throws RemoteException {
		System.out.println("报溢报损单\n");
		exceptionDataService.insert(new ExceptionPO());
		exceptionDataService.update(new ExceptionPO());
		exceptionDataService.show("2014/10/26/09/00/00", "2014/10/26/10/00/00");
		exceptionDataService.findById("ECP-20141023-00001");
		exceptionDataService.findByStatus(DocumentStatus.NONCHECKED);
	}
	
}
