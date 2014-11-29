/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.userbl;

import java.util.ArrayList;

import util.ResultMessage;
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
	public ArrayList<UserVO> show() {
		return user.show();
	}

	@Override
	public ArrayList<UserVO> fuzzyFind(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

}
