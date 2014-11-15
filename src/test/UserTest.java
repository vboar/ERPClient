package test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import util.UserType;
import vo.UserVO;
import businesslogic.userbl.User;

public class UserTest {

	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		
		User user = new User();
		for(int i = 0; i < 10; i++) {
			user.addUser(new UserVO(Integer.toString(i), "2", UserType.ADMINISTRATOR, 0, "3"));
		}
	}
	
}
