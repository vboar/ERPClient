/**
 * 商品分类数据桩程序
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.commoditydataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CategoryPO;

public class CategoryDataServiceTxtFileImpl_Stub implements CategoryDataService {
	
	@Override
	public void insert(CategoryPO po) throws RemoteException {
		System.out.println("Insert Succeed!\n");
	}

	@Override
	public void delete(CategoryPO po) throws RemoteException {
		System.out.println("Delete Succeed!\n");
	}

	@Override
	public void update(CategoryPO po) throws RemoteException {
		System.out.println("Update Succeed!\n");
	}

	@Override
	public ArrayList<CategoryPO> findById(String id) throws RemoteException {
		System.out.println("FindById Succeed!\n");
		ArrayList<CategoryPO> list = new ArrayList<CategoryPO>();
		list.add(new CategoryPO());
		return list;
	}

	@Override
	public ArrayList<CategoryPO> findByName(String name) throws RemoteException {
		System.out.println("FindByName Succeed!\n");
		ArrayList<CategoryPO> list = new ArrayList<CategoryPO>();
		list.add(new CategoryPO());
		return list;
	}

	@Override
	public ArrayList<CategoryPO> show() throws RemoteException {
		System.out.println("Show Succeed!\n");
		ArrayList<CategoryPO> list = new ArrayList<CategoryPO>();
		list.add(new CategoryPO());
		return list;
	}

	
	
}
