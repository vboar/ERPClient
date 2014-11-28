package businesslogicservice.purchaseblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PurchaseVO;

public interface PurchaseBLService {
	/**
	 * 添加进货类单据
	 * @param vo
	 * @throws RemoteException 
	 */
	public ResultMessage add(PurchaseVO vo) throws RemoteException;

	
	/**
	 * 按时间区间筛选单据
	 * @param time1
	 * @param time2
	 * @return
	 * @throws RemoteException 
	 */
	public ArrayList<PurchaseVO> findByTime(String time1,String time2) throws RemoteException;
	
	/**
	 * 按商品名称筛选单据
	 * @param commodityName
	 * @return
	 */
	public ArrayList<PurchaseVO> findByCommodityName(String commodityName);
	
	/**
	 * 按客户筛选单据
	 * @param customer
	 * @return
	 * @throws RemoteException 
	 */
	public ArrayList<PurchaseVO> findByCustomer(String customer) throws RemoteException;
	
	/**
	 * 按业务员筛选单据
	 * @param salesman
	 * @return
	 */
	public ArrayList<PurchaseVO> findBySalesman(String salesman);
	
	/**
	 * 按仓库筛选单据
	 * @param Storage
	 * @return
	 * @throws RemoteException 
	 */
	public ArrayList<PurchaseVO> findByStorage(String Storage) throws RemoteException;

	
	/**
	 * 显示全部进货类单据
	 * @param time1
	 * @param time2
	 * @return
	 * @throws RemoteException 
	 */
	public ArrayList<PurchaseVO> show() throws RemoteException;
	
	public ResultMessage updateCommodityByPurchase(ArrayList<CommodityLineItemVO> list);
}
