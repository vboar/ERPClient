/**
 * 用户业务逻辑接口
 * @author chengcheng
 * @date 2014/10/25
 */

package businesslogicservice.userblservice;

import util.ResultMessage;
import vo.UserVO;

import java.util.ArrayList;

public interface UserBLService {
	
	/**
	 * 增加用户
	 * @param vo
	 * @return 结果消息
	 */
	public ResultMessage add(UserVO vo);
	
	/**
	 * 删除用户
	 * @param vo
	 * @return 结果消息
	 */
	public ResultMessage delete(UserVO vo);	
	
	/**
	 * 更新用户信息
	 * @param vo
	 * @return 结果消息
	 */
	public ResultMessage update(UserVO vo);
	
	/**
	 * 模糊查找（用户名、姓名）
	 * @param keyWord
	 * @return
	 */
	public ArrayList<UserVO> fuzzyFind(String keyWord);
    
    /**
     * 显示用户列表
     * @return 用户列表
     */
    public ArrayList<UserVO> show();

	/**
	 * 根据ID准确查找用户
	 * @param id
	 * @return
	 */
	public UserVO getById(String id);
	
	
	/**
	 * 模糊查找operator（业务员）
	 * @param keyword
	 * @return
	 */
	public ArrayList<UserVO> fuzzyFindOperator(String keyword);

	public ResultMessage updatePassword(String oldword, String newword);
}
