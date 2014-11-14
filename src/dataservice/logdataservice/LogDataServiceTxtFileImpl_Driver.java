/**
 * 日志数据服务驱动程序
 * @author oneoneO
 * @date 2014/10/26
 */
package dataservice.logdataservice;

import java.rmi.RemoteException;

import po.LogPO;

public class LogDataServiceTxtFileImpl_Driver {

	public void drive(LogDataService lds) throws RemoteException{
		System.out.println("日志：\n");
		
		lds.insert(new LogPO("LOG-20141023-00001","23:47:12","CW001-精钢狼","新建一张付款单"));
	
		lds.find("2014/10/25","2014/20/26");
		
	    lds.show();
	}
}
