/**
 * Account逻辑类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package businesslogic.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.logbl.Log;
import dataservice.datafactoryservice.DataFactoryImpl;
import po.AccountPO;
import util.ResultMessage;
import vo.AccountVO;

//oneoneO
public class Account {
	Log l=new Log();
	
	public ResultMessage createLog(String content){	
		MockLog log = new MockLog(content);
		return log.add();		
	}
	
	public ResultMessage add(AccountVO vo) throws RemoteException{
		if(DataFactoryImpl.getInstance().getAccountData().findByName(vo.name).size()==0){
			l.add("Add account failed:account exists");
			return ResultMessage.EXIST;
		}else{
			DataFactoryImpl.getInstance().getAccountData().insert(new AccountPO(vo.name,vo.account,vo.balance));
		}
		
		l.add("Add account successfully");
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage delete(AccountVO vo) throws RemoteException{
		if(DataFactoryImpl.getInstance().getAccountData().findByName(vo.name).size()==0){
			l.add("Delete account failed:account doesn't exists");
			return ResultMessage.FAILED;
		}else{
			DataFactoryImpl.getInstance().getAccountData().delete(new AccountPO(vo.name,vo.account,vo.balance));
		}
		
		l.add("Delete account successfully");
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage update(AccountPO po) throws RemoteException{
		DataFactoryImpl.getInstance().getAccountData().update(po);
		return ResultMessage.SUCCESS;
	}
	
	public AccountPO findByAccount(String account) throws RemoteException{
		AccountPO result=DataFactoryImpl.getInstance().getAccountData().findByAccount(account);
		return result;
	}
}
