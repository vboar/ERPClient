package businesslogic.messagebl;

import java.util.ArrayList;

import vo.MessageVO;
import vo.UserVO;
import businesslogicservice.messageblservice.MessageBLService;

public class MessageController implements MessageBLService {

	@Override
	public void send(MessageVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MessageVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<MessageVO> showByUser(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MessageVO> findByStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

}
