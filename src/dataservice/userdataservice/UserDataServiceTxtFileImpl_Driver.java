/**
 * 用户数据驱动
 * @author chengcheng
 * @date 2014/10/25
 */

package dataservice.userdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.UserPO;
import util.UserType;

public class UserDataServiceTxtFileImpl_Driver {
	public void drive(UserDataService userDataService) throws RemoteException{
		userDataService.insert(new UserPO("xs001","123456",UserType.SALESMAN,0,"金刚浪"));
		userDataService.delete(new UserPO("xs001","123456",UserType.SALESMAN,0,"金刚浪"));
		userDataService.update(new UserPO("xs001","123456",UserType.SALESMAN,0,"金刚浪"));
		
		ArrayList<UserPO> volist1=userDataService.findById("xs001");
		System.out.println("下面是查找到的用户name");
		System.out.println(volist1.get(0).getName());
		System.out.println();
		
		ArrayList<UserPO> volist2=userDataService.findByName("金刚狼");
		System.out.println("下面是查找到的用户id");
		System.out.println(volist2.get(0).getId());
		System.out.println();
		
		ArrayList<UserPO> volist3=userDataService.findByType(UserType.SALESMAN);
		System.out.println("下面是查找到的用户id");
		System.out.println(volist3.get(0).getId());
		System.out.println();
		
	}

}
