/**
 * 消息处理数据接口
 * @date 2014/10/26
 * @author chengcheng
 */
package dataservice.messagedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.MessagePO;
import po.UserPO;

public interface MessageDataService {
	public void insert(MessagePO po)throws RemoteException;
	public void update(MessagePO po) throws RemoteException;
	public ArrayList<MessagePO> showByUser(UserPO po) throws RemoteException;


}
