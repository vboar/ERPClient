/**
 * 特殊促销策略数据接口
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.promotiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.SpecialOfferPO;

public interface SpecialOfferDataService extends Remote {

	public void insert(SpecialOfferPO po) throws RemoteException;
	
	public void update(SpecialOfferPO po) throws RemoteException;
	
	public ArrayList<SpecialOfferPO> show() throws RemoteException;
	
}
