/**
 * 账户管理服务接口
 * @author JaneLDQ
 * @date 2014/10/25
 */
package businesslogicservice.accountblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.AccountVO;

public interface AccountBLService {
	
	/**
	 * 增加账户
	 * @param vo
	 * @return
	 */
	public ResultMessage add(AccountVO vo);
	
	/**
	 * 删除账户
	 * @param vo
	 * @return
	 */
	public ResultMessage delete(AccountVO vo);
	
	/**
	 * 更新账户
	 * @param vo
	 * @return
	 */
	public ResultMessage update(AccountVO vo);
	
	/**
	 * 显示所有账户信息
	 * @return
	 */
	public ArrayList<AccountVO> show();
	
	/**
	 * 模糊查找（账号和名称）
	 * @param keyWord
	 * @return
	 */
	public ArrayList<AccountVO> fuzzyFind(String keyWord);

}
