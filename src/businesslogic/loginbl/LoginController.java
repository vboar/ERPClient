package businesslogic.loginbl;

import util.ResultMessage;
import util.UserType;
import businesslogicservice.loginblservice.LoginBLService;

public class LoginController implements LoginBLService{

	private Login login;
		
	@Override
	public ResultMessage login(int type, String id, String password) {
		this.login = new Login(id,type);
		return this.login.login(password);
	}

	@Override
	public String getUserName() {
		return this.login.getCurrentUserName();
	}

	@Override
	public String getUserId() {
		return this.login.getCurrentUserId();
	}

	@Override
	public UserType getUserType() {
		return this.login.getCurrentUserType();
	}

}
