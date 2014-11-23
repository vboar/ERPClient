/**
 * 用户类
 * @author chengcheng
 * @date 2014/10/25
 */
package po;

import java.io.Serializable;

import util.UserType;

public class UserPO implements Serializable{
	
	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户名
	 */
	private String id;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 类型
	 */
	private int type;
	
	/**
	 * 权限
	 */
	private int permission;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 
	 * @param id
	 * @param password
	 * @param type
	 * @param perssion
	 * @param name
	 */
	public UserPO(String id,String password,int type,int perssion,String name){
		this.id=id;
		this.password=password;
		this.permission=perssion;
		this.name=name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
}
