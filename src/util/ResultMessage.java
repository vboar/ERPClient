/**
 * 结果消息枚举类型
 * @author Vboar
 * 2014/10/25
 */
package util;

public enum ResultMessage {
	SUCCESS, // 成功
	FAILED, // 失败
		
	EXIST,//想要添加的东西在数据库已存在
	TOO_LONG,//输入的内容过长
	TOO_SHORT,//输入的内容过短
	UNVALID,//输入的内容含有非法字符
	NOT_FOUND,//没有找到所需要的数据
	HAS_CHILDREN//有子分类
	
	
	
}
