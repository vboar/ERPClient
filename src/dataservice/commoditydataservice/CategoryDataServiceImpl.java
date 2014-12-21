/**
 * 商品分类数据操作实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.commoditydataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.CategoryPO;

public class CategoryDataServiceImpl extends UnicastRemoteObject implements CategoryDataService {

	private static final long serialVersionUID = 1L;

	public CategoryDataServiceImpl() throws RemoteException {
		super();
	}
	
	@Override
	public void insert(CategoryPO po) throws RemoteException {
	}

	@Override
	public void delete(CategoryPO po) throws RemoteException {
	}

	@Override
	public void update(CategoryPO po) throws RemoteException {
	}

	@Override
	public ArrayList<CategoryPO> findById(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CategoryPO> findByName(String name) throws RemoteException {
		return null;
	}
	
	@Override
	public CategoryPO getById(String id) throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CategoryPO> show() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<CategoryPO> showByInitial(String id) throws RemoteException {
		return null;
	}

}
