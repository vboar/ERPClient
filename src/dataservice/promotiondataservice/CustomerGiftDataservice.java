/**
 * 客户等级促销策略数据接口
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.promotiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CustomerGiftPO;

public interface CustomerGiftDataservice extends Remote {

	public void insert(CustomerGiftPO po) throws RemoteException;
	
	public void update(CustomerGiftPO po) throws RemoteException;
	
	public ArrayList<CustomerGiftPO> show() throws RemoteException;
	
}
