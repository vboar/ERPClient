package businesslogicservice.purchaseblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
	 * 显示全部进货类单据
	 * @param time1
	 * @param time2
	 * @return
	 * @throws RemoteException 
	 */
	public ArrayList<PurchaseVO> show();
	
}
