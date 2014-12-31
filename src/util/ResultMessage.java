/**
 * 结果消息枚举类型
 * @author Vboar
 * 2014/10/25
 */
package util;

public enum ResultMessage {
	SUCCESS, // 成功
	FAILED, // 失败
	WRONG, // 错误

	EXIST,//想要添加的东西在数据库已存在
	TOO_LONG,//输入的内容过长
	TOO_SHORT,//输入的内容过短
	UNVALID,//输入的内容含有非法字符
	NULL, //输入的内容为空
	NOT_FOUND,//没有找到所需要的数据
	HAS_CHILDREN,//有子分类
	IS_TRADE,//已经进行过交易
	TIME_ERROR,//时间前后错误
	
	WRONG_ID, // 错误用户ID
	WRONG_PASSWD,//错误用户密码
	
	HAS_COMMODITY,//父分类下已经存在商品，不能添加子尚品
	HAS_CATEGORY;//父分类下已存在子分类，不能添加商品

	/**
	 * 枚举类型转中文String
	 * @return
	 */
	public String toFriendlyString() {
		switch (this) {
		case TOO_LONG:
			return "过长";
		case TOO_SHORT:
			return "过短";
		case UNVALID:
			return "含有非法字符";
		case WRONG_ID:
			return "用户名错误";
		case WRONG_PASSWD:
			return "密码错误";
		case NULL:
			return "为空";
		default:
			return null;
		}
	}

}
