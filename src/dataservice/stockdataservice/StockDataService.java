/**
 * 库存盘点数据接口
 * @author Vboar
 * @date 2014/10/26
 */
package dataservice.stockdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.StockPO;

public interface StockDataService {
	
	public void insert(StockPO po) throws RemoteException;

	public ArrayList<StockPO> findByDate(String batch, String batchNumber) throws RemoteException;
	
	public ArrayList<StockPO> show() throws RemoteException;
	
}
