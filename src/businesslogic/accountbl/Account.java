/**
 * Account逻辑类
 * @author JaneLDQ
 * @date 2014/11/13
 */
package businesslogic.accountbl;

import businesslogic.logbl.Log;
import dataservice.datafactoryservice.DataFactoryImpl;
import po.AccountPO;
import util.ResultMessage;
import vo.AccountVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

//oneoneO
public class Account {
	Log l=new Log();
	
	public ResultMessage createLog(String content){	
		//TODO
		return null;		
	}
	
	public ResultMessage add(AccountVO vo){
		try {
			if(DataFactoryImpl.getInstance().getAccountData().getByAccount(vo.account) != null){
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
			if(DataFactoryImpl.getInstance().getAccountData().getByAccount(vo.account) == null){
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
	
	public ResultMessage update(AccountVO vo){
		try {
			DataFactoryImpl.getInstance().getAccountData().update(new AccountPO(vo.name,vo.account,vo.balance));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public ArrayList<AccountVO> show() {
		ArrayList<AccountVO> lists = new ArrayList<AccountVO>();
		try {
			ArrayList<AccountPO> list2 = DataFactoryImpl.getInstance().getAccountData().show();
			for(AccountPO po: list2) {
				lists.add(this.poToVo(po));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return lists;
	}
	
	public ArrayList<AccountVO> fuzzyFind(String keyword){
		ArrayList<AccountVO> result=show();
		for(AccountVO vo:result){
			String key=vo.account+vo.name;
			if(!key.contains(keyword)){
				result.remove(vo);
			}
		}
		return result;
	}
	
	
	
	public AccountVO findByAccount(String account){
		AccountVO result=new AccountVO("","",0);
		try {
		 result=poToVo(DataFactoryImpl.getInstance().getAccountData().getByAccount(account));
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
