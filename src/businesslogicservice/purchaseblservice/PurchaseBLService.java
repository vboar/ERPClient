package businesslogicservice.purchaseblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PurchaseVO;

public interface PurchaseBLService {
	/**
	 * 添加进货类单据
	 * @param vo
	 * @throws RemoteException 
	 */
	public ResultMessage add(PurchaseVO vo);

	/**
	 * 按时间区间筛选单据
	 * @param time1
	 * @param time2
	 * @return
	 * @throws RemoteException 
	 */
	public ArrayList<PurchaseVO> findByTime(String time1,String time2);
	
	/**
	 * 按单据状态筛选数据
	 * @param status
	 * @return
	 */
	public ArrayList<PurchaseVO> findByStatus(DocumentStatus status);
	
	/**
	 * 按客户筛选单据
	 * @param customer
	 * @return
	 */
	public ArrayList<PurchaseVO> findByCustomer(String customer);
	
	/**
	 * 按仓库筛选单据
	 * @param Storage
	 * @return
	 */
	public ArrayList<PurchaseVO> findByStorage(String Storage);

	/**
	 * 显示全部进货类单据
	 * @param time1
	 * @param time2
	 * @return
	 * @throws RemoteException 
	 */
	public ArrayList<PurchaseVO> show();
	
}
