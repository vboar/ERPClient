/**
 * 消息处理数据桩
 * @date 2014/10/26
 * @author chengcheng
 */
package dataservice.messagedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.MessagePO;
import po.UserPO;

public class MessageDataServiceTxtFileImpl_Stub implements MessageDataService {

	@Override
	public void insert(MessagePO po) throws RemoteException {
		System.out.println("添加成功");

	}

	@Override
	public void update(MessagePO po) throws RemoteException {
		System.out.println("更新成功");

	}

	@Override
	public ArrayList<MessagePO> showByUser(UserPO po) throws RemoteException {
		ArrayList<MessagePO> poList=new ArrayList<MessagePO>();
		poList.add(new MessagePO("MSG-20141023-00001","22:41:32",0,"XS001","赠送单ZSD-20141023-00001已通过审批"));
		return poList;
	}

}
