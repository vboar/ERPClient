/**
 * 账户数据处理驱动程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package dataservice.accountdataservice;

import java.rmi.RemoteException;

import po.AccountPO;

public class AccountDataServiceTxtFileImpl_Driver {

	public void drive(AccountDataService accountDataService) throws RemoteException{
		
		AccountPO po1 = new AccountPO("工商银行账户1","6111013200067574123",10000);
		AccountPO po2 = new AccountPO("中国银行账户1","3214313200067574123",10000);
		accountDataService.find("工商银行账户1");
		
		System.out.println("账户数据处理消息：\n");
		accountDataService.insert(po1);
		accountDataService.delete(po1);
		accountDataService.delete(po2);
		accountDataService.update(po1);
		accountDataService.update(po2);			
		accountDataService.show();
		
	}
}
