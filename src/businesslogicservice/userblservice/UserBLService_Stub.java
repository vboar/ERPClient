/**
 * 用户业务逻辑桩程序
 * @author chengcheng
 * @date 2014/10/25
 */


package businesslogicservice.userblservice;

import java.util.ArrayList;

import util.ResultMessage;
import util.UserType;
import vo.UserVO;

public class UserBLService_Stub implements UserBLService {

	@Override
	public ResultMessage add(UserVO vo) {
		if(vo.name.equals("金刚狼")){
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(UserVO vo) {
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(UserVO vo) {
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<UserVO> findByName(String name) {
		ArrayList<UserVO> volist=new ArrayList<>();
		UserVO  vo1=new UserVO("xs001", "123456", UserType.MANAGER, 0, "金刚狼");
		volist.add(vo1);
		return volist;
	}

	@Override
	public ArrayList<UserVO> findByType(UserType type) {
		ArrayList<UserVO> volist=new ArrayList<>();
		UserVO  vo1=new UserVO("xs001", "123456", UserType.MANAGER, 0, "金刚狼");
		volist.add(vo1);
		return volist;
	}

	@Override
	public ArrayList<UserVO> findByid(String id) {
		ArrayList<UserVO> volist=new ArrayList<>();
		UserVO  vo1=new UserVO("xs001", "123456", UserType.MANAGER, 0, "金刚狼");
		volist.add(vo1);
		return volist;
	}

	@Override
	public ArrayList<UserVO> show() {
 
		ArrayList<UserVO> volist=new ArrayList<>();
		UserVO  vo1=new UserVO("xs001", "123456", UserType.MANAGER, 0, "金刚狼");
		volist.add(vo1);
		return volist;
	}

	@Override
	public ResultMessage login(String id, String password) {
		if(id.equals("xs0001")&&password.equals("123456")){
			return ResultMessage.SUCCESS;
		}
		return ResultMessage.FAILED;
	}

}
