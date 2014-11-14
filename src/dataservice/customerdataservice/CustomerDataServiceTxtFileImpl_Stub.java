/**
 * 客户管理数据桩
 * @date 2014/10/26
 * @author chengcheng
 */
package dataservice.customerdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CustomerPO;


public class CustomerDataServiceTxtFileImpl_Stub implements CustomerDataService {

	@Override
	public void insert(CustomerPO po) throws RemoteException {
		System.out.println("插入成功");

	}

	@Override
	public void delete(CustomerPO po) throws RemoteException {
		System.out.println("删除成功");

	}

	@Override
	public void update(CustomerPO po) throws RemoteException {
		System.out.println("更新成功");

	}

	@Override
	public ArrayList<CustomerPO> findByName(String name) throws RemoteException {
		ArrayList<CustomerPO> poList=new  ArrayList<CustomerPO>();
		poList.add(new CustomerPO("xs001", 0, 1, name, "12345678901", 
				"美国加利福尼州", "210000", "Ironman@gmail.com", 10000, 20000, 0, "美队", false));
		
		return poList;
	}

	@Override
	public ArrayList<CustomerPO> findById(String id) throws RemoteException {
		ArrayList<CustomerPO> poList=new  ArrayList<CustomerPO>();
		poList.add(new CustomerPO(id, 0, 1, "金刚狼", "12345678901", 
				"美国加利福尼州", "210000", "Ironman@gmail.com", 10000, 20000, 0, "美队", false));
		
		return poList;
	}

	@Override
	public ArrayList<CustomerPO> show() throws RemoteException {
		ArrayList<CustomerPO> poList=new  ArrayList<CustomerPO>();
		poList.add(new CustomerPO("xs0001", 0, 1, "金刚狼", "12345678901", 
				"美国加利福尼州", "210000", "Ironman@gmail.com", 10000, 20000, 0, "美队", false));
		
		return poList;
	}

}
