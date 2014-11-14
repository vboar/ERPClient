/**
 * 消息逻辑驱动
 * @date 2014/10/25
 * @author chengcheng
 */
package businesslogicservice.messageblservice;

import java.util.ArrayList;

import util.UserType;
import vo.MessageVO;
import vo.UserVO;


public class MessageBLService_Driver {
	public void drive(MessageBLService messageBLService){
		messageBLService.send(new MessageVO("MSG-20141023-00001","22:41:32",0,"XS001","赠送单ZSD-20141023-00001已通过审批"));
		System.out.println("已经发送消息");
		messageBLService.update(new MessageVO("MSG-20141023-00001","22:41:32",0,"XS001","赠送单ZSD-20141023-00001已通过审批"));
		System.out.println("已经审阅消息");
		System.out.println("下面是xs001的消息");
		ArrayList<MessageVO> volist=messageBLService.showByUser(new UserVO("xs001","123456",UserType.SALESMAN,0,"金刚浪"));
		System.out.println(volist.get(0).content);
		System.out.println(volist.get(1).content);
		
	}


	
}
