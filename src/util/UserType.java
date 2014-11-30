/**
 * 用户类型枚举类型
 * @author Vboar
 * 2014/10/25
 */
package util;

public enum UserType {
	STOCKKEEPER, // 库存管理员
	COUNTER, // 财务人员
	SALESMAN, // 销售人员
	MANAGER, // 总经理
	ADMINISTRATOR; // 系统管理员

	public String toFriendString(){
		switch (this) {
		case STOCKKEEPER:
			return "库存管理人员";
		case COUNTER:
			return "财务人员";
		case SALESMAN:
			return "进货销售人员";
		case MANAGER:
			return "总经理";
		case ADMINISTRATOR:
			return "系统管理员";
		default:
			return null;
		}
	}
	
	public static UserType check(String name){
		switch (name) {
		case "库存管理人员":
			return STOCKKEEPER;
		case "财务人员":
			return COUNTER;
		case "进货销售人员":
			return SALESMAN;
		case "总经理":
			return MANAGER;
		case "系统管理员":
			return ADMINISTRATOR;
		default:
			return null;
		}
	}
}
