package dataservice.userdataservice;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.UserPO;
import util.UserType;

public class UserDataClient {

	public static void main(String[] args) {
		try {
//			UserDataService userDataService = (UserDataService) Naming.lookup("rmi://127.0.0.1:8888/UserDataService");
//			ArrayList<UserPO> list = userDateService.show();
//			System.out.println("id: " + list.get(0).getId());
			UserDataService userDataService = (UserDataService) Naming.lookup("rmi://127.0.0.1:8888/UserDataService");
			UserPO po =new UserPO("123", "456", UserType.MANAGER, 0, "hello");
			userDataService.insert(po);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
