package businesslogic.loginbl;

import util.ResultMessage;
import util.UserType;

public class Login {

	private String currentUserName;
	
	private String currentUserId;
	
	private UserType currentUserType;
	
	private int type;
	
	public Login(String id, int type){
		this.currentUserId = id;
		this.type  = type;
	}

	public ResultMessage login(String password){
		if((type==UserType.ADMINISTRATOR.ordinal())&&this.currentUserId.equals("a")&&password.equals("1")){
			this.currentUserType = UserType.ADMINISTRATOR;
			return ResultMessage.SUCCESS;
		}
		return ResultMessage.FAILED;
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
