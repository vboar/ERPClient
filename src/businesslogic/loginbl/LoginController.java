package businesslogic.loginbl;

import util.ResultMessage;
import businesslogicservice.loginblservice.LoginBLService;

public class LoginController implements LoginBLService{

	@Override
	public ResultMessage login(String id, String password) {
		System.out.println(id+" " + password);
		if(id.equals("admin")&&password.equals("123456")){
			return ResultMessage.SUCCESS;
		}
		return ResultMessage.FAILED;
	}

}
