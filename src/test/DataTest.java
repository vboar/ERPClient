package test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import po.UserPO;
import dataservice.datafactoryservice.DataFactoryImpl;

public class UserTest {

	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		
		for(int i = 0; i < 10; i++) {
			DataFactoryImpl.getInstance().getUserData().insert(
					new UserPO("1", "2", i, i, "5"));
		}
	}
	
}
