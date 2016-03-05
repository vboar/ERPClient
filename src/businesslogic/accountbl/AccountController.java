package businesslogic.accountbl;

import businesslogicservice.accountblservice.AccountBLService;
import util.ResultMessage;
import vo.AccountVO;

import java.util.ArrayList;

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
		return account.fuzzyFind(keyWord);
	}
	
}
