package test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.UserPO;
import dataservice.datafactoryservice.DataFactoryImpl;


public class DataTest {

	public static void main(String[] args) throws RemoteException {
		
		// insert
//		DataFactoryImpl.getInstance().getUserData().insert(new UserPO("a0001", "123", 0, 0, "王一"));
//		DataFactoryImpl.getInstance().getUserData().insert(new UserPO("a0002", "123", 1, 1, "王二"));
//		DataFactoryImpl.getInstance().getUserData().insert(new UserPO("a0003", "123", 2, 2, "张三"));
//		DataFactoryImpl.getInstance().getUserData().insert(new UserPO("a0004", "123", 2, 3, "李四"));
//		DataFactoryImpl.getInstance().getUserData().insert(new UserPO("a0015", "123", 5, 1, "小白"));
		
		// remove
//		DataFactoryImpl.getInstance().getUserData().delete(new UserPO("a0003", "123", 2, 2, "张三"));
		
		// update
//		DataFactoryImpl.getInstance().getUserData().update(new UserPO("a0015", "123456", 1, 2, "白"));
		
		// findById
		ArrayList<UserPO> lists = DataFactoryImpl.getInstance().getUserData().findById("a000");
		for(UserPO po: lists) {
			System.out.println(po.getId() + " " +  po.getName());
		}
		
		// findByName
//		ArrayList<UserPO> lists = DataFactoryImpl.getInstance().getUserData().findByName("王");
//		for(UserPO po: lists) {
//			System.out.println(po.getId() + " " +  po.getName());
//		}
		
		// findByType
//		ArrayList<UserPO> lists = DataFactoryImpl.getInstance().getUserData().findByType(0);
//		for(UserPO po: lists) {
//			System.out.println(po.getId() + " " +  po.getType());
//		}
		
		// getById
//		UserPO po = DataFactoryImpl.getInstance().getUserData().getById("a0015");
//		System.out.println(po.getId() + " " +  po.getName());
//		
//		DataFactoryImpl.getInstance().getCategoryData().insert(new CategoryPO("a0002", "123", 1));
	}
	
}
