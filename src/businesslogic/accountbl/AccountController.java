package businesslogic.accountbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.AccountVO;
import businesslogicservice.accountblservice.AccountBLService;

public class AccountController implements AccountBLService {
	Account a=new Account();
	@Override
	public ArrayList<AccountVO> find(String name) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public AccountVO findByAccount(String account) {
		return a.findByAccount(account);
	}

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


}
