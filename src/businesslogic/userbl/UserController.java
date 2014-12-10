/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.userbl;

import businesslogicservice.userblservice.UserBLService;
import util.ResultMessage;
import vo.UserVO;

import java.util.ArrayList;

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
	public UserVO getById(String id) {
		return user.getById(id);
	}

	@Override
	public ArrayList<UserVO> fuzzyFind(String keyWord) {
		
		return user.fuzzyFind(keyWord);
	}

	@Override
	public ArrayList<UserVO> fuzzyFindOperator(String keyword) {
		
		return user.fuzzyFindOperator(keyword);
	}

}
