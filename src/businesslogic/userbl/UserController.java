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
		 try {
			return user.addUser(vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //ResultMessage.SUCCESS;
		 return null;
	}

	@Override
	public ResultMessage delete(UserVO vo) {
		 try {
			return user.delete(vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //ResultMessage.SUCCESS;
		 return null;
	}

	@Override
	public ResultMessage update(UserVO vo) {
		 try {
			return user.update(vo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //ResultMessage.SUCCESS;
		 return null;
	}

	@Override
	public ArrayList<UserVO> findByName(String name) {
		 try {
			return user.findByName(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //ResultMessage.SUCCESS;
		 return null;
	}

	@Override
	public ArrayList<UserVO> findByType(UserType type) {
		
		 try {
			return user.findByType(type);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //ResultMessage.SUCCESS;
		 return null;
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
		try {
			return user.findById(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
