package businesslogic.accountbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.AccountVO;
import businesslogicservice.accountblservice.AccountBLService;

public class AccountController implements AccountBLService {
	
	Account account = new Account();

	@Override
	public ResultMessage add(AccountVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage delete(AccountVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage update(AccountVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<AccountVO> show() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<AccountVO> fuzzyFind(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}


}
