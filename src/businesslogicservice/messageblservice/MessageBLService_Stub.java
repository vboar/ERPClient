/**
 * 消息的逻辑桩
 * @author chengcheng
 * @date 2014/10/25
 * 
 */
package businesslogicservice.messageblservice;

import java.util.ArrayList;

import vo.MessageVO;
import vo.UserVO;

public class MessageBLService_Stub implements MessageBLService {

	@Override
	public void send(MessageVO vo) {
		System.out.println();
	}

	@Override
	public void update(MessageVO vo) {		
		System.out.println();
	}

	@Override
	public ArrayList<MessageVO> showByUser(UserVO vo) {
		ArrayList<MessageVO> voList=new ArrayList<MessageVO>();
		voList.add(new MessageVO("MSG-20141023-00001","22:41:32",0,"XS001","赠送单ZSD-20141023-00001已通过审批"));
		voList.add(new MessageVO("MSG-20141023-00001","22:41:32",0,"XS001","赠送单ZSD-20141023-00001已通过审批"));
		
		return voList;
	}

}
