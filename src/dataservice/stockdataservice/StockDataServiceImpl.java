/**
 * 库存盘点数据实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.stockdataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.StockPO;

public class StockDataServiceImpl extends UnicastRemoteObject implements StockDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(StockPO po) throws RemoteException {
	}

	@Override
	public ArrayList<StockPO> findByDate(String batch, String batchNumber)
			throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<StockPO> show() throws RemoteException {
		return null;
	}

}
