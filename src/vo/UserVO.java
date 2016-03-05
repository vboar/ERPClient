/**
 * 用户vo类
 * @author chengcheng
 * @date 2014/10/25
 */

package vo;

import util.UserType;

public class UserVO {
	
	/**
	 * 用户名
	 */
	public String id;
	
	/**
	 * 密码
	 */
	public String password;
	
	/**
	 * 类型
	 */
	public UserType type;
	
	/**
	 * 权限
	 */
	public int permission;
	
	/**
	 * 姓名
	 */
	public String name;
	
	/**
	 * 构造方法
	 * @param id
	 * @param password
	 * @param type
	 * @param permission
	 * @param name
	 */
	public UserVO(String id,String password,UserType type,int permission,String name){
		this.id=id;
		this.password=password;
		this.type=type;
		this.permission=permission;
		this.name=name;
	}
	
}
