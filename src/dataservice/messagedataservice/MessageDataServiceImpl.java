/**
 * 消息数据操作实现
 * @author Vboar
 * @date 2014/11/15
 */

package dataservice.messagedataservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.MessagePO;
import po.UserPO;

public class MessageDataServiceImpl extends UnicastRemoteObject implements MessageDataService {

	private static final long serialVersionUID = 1L;

	public MessageDataServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public void insert(MessagePO po) throws RemoteException {
	}

	@Override
	public void delete(MessagePO po) throws RemoteException {
	}

	@Override
	public void update(MessagePO po) throws RemoteException {
	}

	@Override
	public ArrayList<MessagePO> showByUser(UserPO po) throws RemoteException {
		return null;
	}

}
