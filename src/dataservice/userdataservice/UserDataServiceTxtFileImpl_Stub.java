/**
 * 用户数据桩
 * @author chengcheng
 * @date 2014/10/25
 */
package dataservice.userdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.UserPO;
import util.UserType;

public class UserDataServiceTxtFileImpl_Stub implements UserDataService {

	@Override
	public void insert(UserPO po) throws RemoteException {
		System.out.println("插入成功");

	}

	@Override
	public void delete(UserPO po) throws RemoteException {
		System.out.println("删除成功");
	}

	@Override
	public void update(UserPO po) throws RemoteException {
		System.out.println("插入成功");

	}

	@Override
	public ArrayList<UserPO> findById(String id) throws RemoteException {
		ArrayList<UserPO> volist=new ArrayList<>();
		UserPO  vo1=new UserPO(id, "123456", UserType.MANAGER, 0, "金刚狼");
		volist.add(vo1);
		return volist;	
	}

	@Override
	public ArrayList<UserPO> findByName(String name) throws RemoteException {
		ArrayList<UserPO> volist=new ArrayList<>();
		UserPO  vo1=new UserPO("xs0001", "123456", UserType.MANAGER, 0, name);
		volist.add(vo1);
		return volist;	
	}

	@Override
	public ArrayList<UserPO> findByType(UserType type) throws RemoteException {
		ArrayList<UserPO> volist=new ArrayList<>();
		UserPO  vo1=new UserPO("xs0001", "123456", type, 0, "金刚狼");
		volist.add(vo1);
		return volist;	
	}

	@Override
	public ArrayList<UserPO> show() throws RemoteException {
		ArrayList<UserPO> volist=new ArrayList<>();
		UserPO  vo1=new UserPO("xs0001", "123456", UserType.MANAGER, 0, "金刚狼");
		volist.add(vo1);
		return volist;	
	}

}
