/**
 * 系统日志vo
 * @date 2014/10/25
 * @author chengcheng
 */

package vo;

public class LogVO {
	/**
	 * 编号
	 */
	public String id;
	
	/**
	 * 操作时间
	 */
	public String time;
	
	/**
	 * 操作员
	 */
	public String operator;
	
	/**
	 * 操作内容
	 */
	public String content;
	
	/**
	 * 构造函数
	 * @param id
	 * @param time
	 * @param operator
	 * @param content
	 */
	public LogVO(String id,String time,String operator,String content){
		this.id=id;
		this.time=time;
		this.operator=operator;
		this.content=content;
	}
	

}
