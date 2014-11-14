/**
 * 账户管理服务桩程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package businesslogicservice.accountblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.AccountVO;

public class AccountBLService_Stub implements AccountBLService {

	@Override
	public ArrayList<AccountVO> find(String name) {
		ArrayList<AccountVO> list = new ArrayList<AccountVO>();
		list.add(new AccountVO(name,"6111013200067574123",10000));
		return list;
	}

	@Override
	public ResultMessage add(AccountVO vo) {
		if(vo.name.equals("工商银行账户1")){
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(AccountVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(AccountVO vo) {
		if(vo.name.equals("工商银行账户1")){
			return ResultMessage.SUCCESS; 
		}
		return ResultMessage.FAILED;
	}

	@Override
	public ArrayList<AccountVO> show() {
		ArrayList<AccountVO> list = new ArrayList<AccountVO>();
		list.add(new AccountVO("工商银行账户1","6111013200067574123",10000));
		list.add(new AccountVO("中国银行账户1","6111013200067574123",10000));
		return list;
	}

}
