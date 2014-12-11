/**
 * 消息的逻辑接口
 * @author chengcheng
 * @date 2014/10/25
 * 
 */
package businesslogicservice.messageblservice;

import java.util.ArrayList;

import vo.MessageVO;
import vo.UserVO;

public interface MessageBLService {
	
	/**
	 * 发送消息
	 * @param vo
	 */
	public void add(MessageVO vo);
	
	public void delete(MessageVO vo);
	
	/**
	 * 阅读消息
	 * @param vo
	 */
	public void update(MessageVO vo);
	
	/**
	 * 查看消息列表
	 * @param vo
	 * @return 消息列表
	 */
	public ArrayList<MessageVO> showByUser(UserVO vo);
	
	public ArrayList<MessageVO> showByState(int state);

}
