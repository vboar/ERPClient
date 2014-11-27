package businesslogic.loginbl;

import java.rmi.RemoteException;

import po.UserPO;
import util.ResultMessage;
import util.UserType;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Login {

	private String currentUserName;
	
	public static String currentUserId;
	
	private UserType currentUserType;
	
	private int type;
	
	public Login(String id, int type) {
		Login.currentUserId = id;
		this.type  = type;
	}

	/**
	 * 用户登录验证
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage login(String password) {
		UserPO po = null;
		try {
			po= DataFactoryImpl.getInstance().getUserData().getById(currentUserId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// 用户ID错误（ID不存在或者不对应）
		if(po == null) return ResultMessage.WRONG_ID;
		if(po.getType() != type) return ResultMessage.WRONG_ID;
		// 用户密码错误
		if(!po.getPassword().equals(password)) return ResultMessage.WRONG_PASSWD;
		// 登录成功
		currentUserType = UserType.values()[type];
		currentUserName = po.getName();
		return ResultMessage.SUCCESS;
	}
	
	public String getCurrentUserName() {
		return currentUserName;
	}

	public String getCurrentUserId() {
		return currentUserId;
	}

	public UserType getCurrentUserType() {
		return currentUserType;
	}
	
	
}
