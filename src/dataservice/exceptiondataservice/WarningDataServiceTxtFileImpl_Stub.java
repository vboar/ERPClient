/**
 * 报警单数据桩程序
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.exceptiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.WarningPO;

public class WarningDataServiceTxtFileImpl_Stub implements WarningDataService {

	@Override
	public void insert(WarningPO po) throws RemoteException{
		System.out.println("Insert Succeed!\n");
	}

	@Override
	public ArrayList<WarningPO> show(String time1, String time2) throws RemoteException {
		System.out.println("Show Succeed!\n");
		ArrayList<WarningPO> list = new ArrayList<WarningPO>();
		list.add(new WarningPO());
		return list;
	}

	@Override
	public ArrayList<WarningPO> findById(String id) throws RemoteException {
		System.out.println("FindById Succeed!\n");
		ArrayList<WarningPO> list = new ArrayList<WarningPO>();
		list.add(new WarningPO());
		return list;
	}

}
