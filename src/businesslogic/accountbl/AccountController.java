package businesslogic.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import util.ResultMessage;
import vo.AccountVO;
import businesslogicservice.accountblservice.AccountBLService;

public class AccountController implements AccountBLService {

	@Override
	public ArrayList<AccountVO> find(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage add(AccountVO vo) {
		Account a=new Account();
		try {
			a.add(vo);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage delete(AccountVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(AccountVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AccountVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AccountVO> findByAccount(String account) {
		// TODO 自动生成的方法存根
		return null;
	}

}
