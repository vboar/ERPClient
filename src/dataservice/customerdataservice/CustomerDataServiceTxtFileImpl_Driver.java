/**
 * 客户管理数据驱动
 * @date 2014/10/26
 * @author chengcheng
 */
package dataservice.customerdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CustomerPO;

public class CustomerDataServiceTxtFileImpl_Driver {
	public void drive(CustomerDataService customerDataService) throws RemoteException{
		CustomerPO po=new CustomerPO("xs001", 0, 1, "金刚狼", "12345678901", 
				"美国加利福尼州", "210000", "Ironman@gmail.com", 10000, 20000, 0, "美队", false);
		customerDataService.insert(po);
		customerDataService.delete(po);
		customerDataService.update(po);
		
		System.out.println("下面是按id查找的结果");
		ArrayList<CustomerPO> poList=new  ArrayList<CustomerPO>();
		poList=customerDataService.findById("xs001");
		System.out.println(poList.get(0).getName());
		
		
		System.out.println("下面是按name查找的结果");
		poList=customerDataService.findByName("金刚狼");
		System.out.println(poList.get(0).getName());
		
		System.out.println("下面是显示的结果");
		poList=customerDataService.show();
		System.out.println(poList.get(0).getName());
	}

}
