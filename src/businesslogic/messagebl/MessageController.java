package businesslogic.messagebl;

import java.util.ArrayList;

import vo.MessageVO;
import vo.UserVO;
import businesslogicservice.messageblservice.MessageBLService;

public class MessageController implements MessageBLService {
	Message m=new Message();
	
	@Override
	public void add(MessageVO vo) {
		m.addLog("发送（增加）一条消息");
		m.add(vo);
	}

	@Override
	public void update(MessageVO vo) {		
		m.addLog("更新消息");
		m.update(vo);
	}

	@Override
	public ArrayList<MessageVO> showByUser(UserVO vo) {
		m.addLog("根据用户显示消息");
		return m.showByUser(vo);
	}

	@Override
	public void delete(MessageVO vo) {
		m.addLog("删除消息");
		m.delete(vo);		
	}

	@Override
	public ArrayList<MessageVO> findByStatus(int state) {
		m.addLog("按照状态查看消息");
		return m.showByStatus(state);
	}

	public String createId(){
		return m.createId();
	}

	@Override
	public ArrayList<MessageVO> show() {
		m.addLog("显示所有消息");
		return m.show();
	}
}
