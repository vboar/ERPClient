package dataservice.stockdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 库存盘点数据桩程序
 * @author Vboar
 * @date 2014/10/26
 */
import po.StockPO;

public class StockDataServiceTxtFileImpl_Stub implements StockDataService {

	@Override
	public void insert(StockPO po) throws RemoteException {
		System.out.println("Insert Succeed!\n");
	}

	@Override
	public ArrayList<StockPO> findByDate(String batch, String batchNumber)
			throws RemoteException {
		System.out.println("FindByDate Succeed!\n");
		ArrayList<StockPO> list = new ArrayList<StockPO>();
		list.add(new StockPO());
		return list;
	}

	@Override
	public ArrayList<StockPO> show() throws RemoteException {
		System.out.println("Show Succeed!\n");
		ArrayList<StockPO> list = new ArrayList<StockPO>();
		list.add(new StockPO());
		return list;
	}

}
