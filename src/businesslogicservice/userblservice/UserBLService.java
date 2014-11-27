/**
 * 用户业务逻辑接口
 * @author chengcheng
 * @date 2014/10/25
 */

package businesslogicservice.userblservice;

import java.util.ArrayList;

import util.ResultMessage;
import util.UserType;
import vo.UserVO;

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
	 * 根据姓名模糊查找用户
	 * @param name
	 * @return 用户列表
	 */
    public ArrayList<UserVO> findByName(String name);
    
    /**
     * 根据用户类型查找用户
     * @param type
     * @return 用户列表
     */
    public ArrayList<UserVO> findByType(UserType type);
    
    /**
     * 根据用户id查找用户
     * @param id
     * @return 用户列表
     */
    public  ArrayList<UserVO> findById(String id);
    
    /**
     * 显示用户列表
     * @return 用户列表
     */
    public ArrayList<UserVO> show();



}
