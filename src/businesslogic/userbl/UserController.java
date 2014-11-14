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

	@Override
	public ResultMessage add(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserVO> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserVO> findByType(UserType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserVO> findByid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage login(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
