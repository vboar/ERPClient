package businesslogic.accountbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.AccountVO;
import businesslogicservice.accountblservice.AccountBLService;

public class AccountController implements AccountBLService {
	
	Account account = new Account();

	@Override
	public ResultMessage add(AccountVO vo) {
		return account.add(vo);
	}

	@Override
	public ResultMessage delete(AccountVO vo) {
		return account.delete(vo);
	}

	@Override
	public ResultMessage update(AccountVO vo) {
		return account.update(vo);
	}

	@Override
	public ArrayList<AccountVO> show() {
		return account.show();
	}

	@Override
	public ArrayList<AccountVO> fuzzyFind(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
