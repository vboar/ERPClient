/**
 * 期初建账数据桩程序
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.initialdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.InitialPO;

public class InitialDataServiceTxtFileImpl_Stub  implements InitialDataService {

	@Override
	public void insert(InitialPO po) throws RemoteException {
		System.out.println("Insert Succeed!\n");
	}

	@Override
	public ArrayList<InitialPO> findById(String id) throws RemoteException {
		System.out.println("FindById Succeed!\n");
		ArrayList<InitialPO> list = new ArrayList<InitialPO>();
		list.add(new InitialPO());
		return list;
	}

	@Override
	public ArrayList<InitialPO> show() throws RemoteException {
		System.out.println("Show Succeed!\n");
		ArrayList<InitialPO> list = new ArrayList<InitialPO>();
		list.add(new InitialPO());
		return list;
	}

}
