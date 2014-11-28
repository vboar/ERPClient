/**
 * 账户管理数据操作接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package dataservice.accountdataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import po.AccountPO;

public interface AccountDataService extends Remote {
	
	public void insert(AccountPO po);

	public void delete(AccountPO po);

	public void update(AccountPO po);
	
	public ArrayList<AccountPO> findByName(String name);
	
	public AccountPO findByAccount(String account);

	public ArrayList<AccountPO> show();

}
