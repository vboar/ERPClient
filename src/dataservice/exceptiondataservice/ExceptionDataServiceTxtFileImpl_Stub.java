/**
 * 报溢报损单数据桩程序
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.exceptiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ExceptionPO;
import util.DocumentStatus;

public class ExceptionDataServiceTxtFileImpl_Stub implements ExceptionDataService {

	@Override
	public void insert(ExceptionPO po) throws RemoteException {
		System.out.println("Insert Succeed!\n");
	}

	@Override
	public void update(ExceptionPO po) throws RemoteException {
		System.out.println("Update Succeed!\n");
	}

	@Override
	public ArrayList<ExceptionPO> show(String time1, String time2)
			throws RemoteException {
		System.out.println("Show Succeed!\n");
		ArrayList<ExceptionPO> list = new ArrayList<ExceptionPO>();
		list.add(new ExceptionPO());
		return list;
	}

	@Override
	public ArrayList<ExceptionPO> findById(String id) throws RemoteException {
		System.out.println("FindById Succeed!\n");
		ArrayList<ExceptionPO> list = new ArrayList<ExceptionPO>();
		list.add(new ExceptionPO());
		return list;
	}

	@Override
	public ArrayList<ExceptionPO> findByStatus(DocumentStatus status)
			throws RemoteException {
		System.out.println("FindByStatus Succeed!\n");
		ArrayList<ExceptionPO> list = new ArrayList<ExceptionPO>();
		list.add(new ExceptionPO());
		return list;
	}

}
