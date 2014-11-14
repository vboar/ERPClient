/**
 * 消息处理数据驱动
 * @date 2014/10/26
 * @author chengcheng
 */
package dataservice.messagedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.MessagePO;
import po.UserPO;
import util.UserType;

public class MessageDataServiceTxtFileImpl_Driver {
	public void drive(MessageDataService messageDataService) throws RemoteException{
		MessagePO po=new MessagePO("MSG-20141023-00001","22:41:32",0,"XS001","赠送单ZSD-20141023-00001已通过审批");
		messageDataService.insert(po);
		messageDataService.update(po);
		
		UserPO po2=new UserPO("xs001", "123456", UserType.MANAGER, 0, "金刚狼");
		ArrayList<MessagePO> poList=new ArrayList<MessagePO>();
		poList=messageDataService.showByUser(po2);
		System.out.println("下面是查询信息");
		System.out.println(poList.get(0).getContent());
		
	}

}
