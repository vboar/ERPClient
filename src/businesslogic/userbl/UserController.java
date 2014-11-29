/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.userbl;

import java.util.ArrayList;

import util.ResultMessage;
import util.UserType;
import vo.UserVO;
import businesslogicservice.userblservice.UserBLService;

public class UserController implements UserBLService {

	User user;
	
	public UserController(){
		this.user = new User();
	}
	
	@Override
	public ResultMessage add(UserVO vo) {
		 return user.addUser(vo);
		 
		 
	}

	@Override
	public ResultMessage delete(UserVO vo) {
		 return user.delete(vo);
		 
		 
	}

	@Override
	public ResultMessage update(UserVO vo) {
		 return user.update(vo);
		 
	}

	@Override
	public ArrayList<UserVO> findByName(String name) {
		 return user.findByName(name);
		 
	}

	@Override
	public ArrayList<UserVO> findByType(UserType type) {
		
		 return user.findByType(type);
		 
	}

	@Override
	public ArrayList<UserVO> show() {
		return user.show();
	}

	@Override
	public ArrayList<UserVO> findById(String id) {
		return user.findById(id);
	}

}
