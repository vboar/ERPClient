/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.userbl;

import java.rmi.RemoteException;
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
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(UserVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(UserVO vo) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
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
		try {
			return user.findById(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<UserVO> show() {
		try {
			return user.show();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<UserVO> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
