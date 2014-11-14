/**
 * 账户数据处理桩程序
 * @author JaneLDQ
 * @date 2014/10/25
 */
package dataservice.accountdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.AccountPO;


public class AccountDataServiceTxtFileImpl_Stub implements  AccountDataService{

	@Override
	public ArrayList<AccountPO> find(String name) throws RemoteException {
		ArrayList<AccountPO> list = new ArrayList<AccountPO>();
		list.add(new AccountPO(name,"6111013200067574123",0));
		return list;
	}

	@Override
	public void insert(AccountPO po) throws RemoteException {
		System.out.println("Insert Succeed!");
	}

	@Override
	public void delete(AccountPO po) throws RemoteException {
		if(po.getName().equals("工商银行账户1")){
			System.out.println("Delete Succeed!");	return;
		}
		System.out.println("Delete Failed!");
	}

	@Override
	public void update(AccountPO po) throws RemoteException {
		if(po.getName().equals("工商银行账户1")){
			System.out.println("Update Succeed!");	return;
		}
		System.out.println("Update Failed!");
	}

	@Override
	public ArrayList<AccountPO> show() throws RemoteException {
		ArrayList<AccountPO> list = new ArrayList<AccountPO>();
		list.add(new AccountPO("工商银行账户1","6111013200067574123",10000));
		list.add(new AccountPO("中国银行账户1","3214313200067574123",20000));
		return list;
	}

}
