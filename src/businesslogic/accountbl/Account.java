/**
 * Account逻辑类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package businesslogic.accountbl;

import java.rmi.RemoteException;

import po.AccountPO;
import util.ResultMessage;
import vo.AccountVO;
import businesslogic.logbl.Log;
import dataservice.datafactoryservice.DataFactoryImpl;

//oneoneO
public class Account {
	Log l=new Log();
	
	public ResultMessage createLog(String content){	
		MockLog log = new MockLog(content);
		return log.add();		
	}
	
	public ResultMessage add(AccountVO vo){
		try {
			if(DataFactoryImpl.getInstance().getAccountData().findByName(vo.name).size()==0){
				l.add("Add account failed:account exists");
				return ResultMessage.EXIST;
			}else{
				DataFactoryImpl.getInstance().getAccountData().insert(new AccountPO(vo.name,vo.account,vo.balance));
			}
			l.add("Add account successfully");
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
			
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage delete(AccountVO vo){
		try {
			if(DataFactoryImpl.getInstance().getAccountData().findByName(vo.name).size()==0){
				l.add("Delete account failed:account doesn't exists");
				return ResultMessage.FAILED;
			}else{
				DataFactoryImpl.getInstance().getAccountData().delete(new AccountPO(vo.name,vo.account,vo.balance));
			}
			
			l.add("Delete account successfully");
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage update(AccountPO po){
		try {
			DataFactoryImpl.getInstance().getAccountData().update(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public AccountVO findByAccount(String account){
		AccountVO result=new AccountVO("","",0);
		try {
		 result=poToVo(DataFactoryImpl.getInstance().getAccountData().findByAccount(account));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return result;
	}
	
	public AccountVO poToVo(AccountPO po){
		AccountVO result =new AccountVO("","",0);
		result.account=po.getAccount();
		result.balance=po.getBalance();
		result.name=po.getName();
		return result;
	}
}
