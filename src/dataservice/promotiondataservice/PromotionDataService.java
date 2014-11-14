/**
 * 促销策略数据接口
 * @date 2014/10/26
 * @author chengcheng
 */

package dataservice.promotiondataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CustomerGiftPO;
import po.SpecialOfferPO;
import po.TotalGiftPO;

public interface PromotionDataService {
	public void insert(CustomerGiftPO po)throws RemoteException;
	public void insert(TotalGiftPO po)throws RemoteException;
	public void insert(SpecialOfferPO po)throws RemoteException;
	
	public void update(CustomerGiftPO po)throws RemoteException;
	public void update(TotalGiftPO po)throws RemoteException;
	public void update(SpecialOfferPO po)throws RemoteException;
	
	public ArrayList<CustomerGiftPO> findByVip() throws RemoteException; 
	public ArrayList<SpecialOfferPO> findByCommodity() throws RemoteException; 
	public ArrayList<TotalGiftPO> findByPrice() throws RemoteException; 
	


}
