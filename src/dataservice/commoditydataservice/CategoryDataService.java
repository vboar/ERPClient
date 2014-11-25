/**
 * 商品分类数据操作接口
 * @author Vboar
 * @date 2014/10/25
 */
package dataservice.commoditydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CategoryPO;

public interface CategoryDataService extends Remote {
	
	public void insert(CategoryPO po) throws RemoteException;
	
	public void delete(CategoryPO po) throws RemoteException;
	
	public void update(CategoryPO po) throws RemoteException;
	
	public ArrayList<CategoryPO> findById(String id) throws RemoteException;
	
	public ArrayList<CategoryPO> findByName(String name) throws RemoteException;
	
	public CategoryPO getById(String id) throws RemoteException;
	
	public ArrayList<CategoryPO> show() throws RemoteException;

}
