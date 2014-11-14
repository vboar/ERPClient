/**
 * 系统日志po类
 * @author chengcheng
 * @date 2014/10/25
 */

package po;

import java.io.Serializable;

public class LogPO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 编号
	 */
	private String id;
	
	/**
	 * 操作时间
	 */
	private String time;
	
	/**
	 * 操作员
	 */
	private String operator;
	
	/**
	 * 操作内容
	 */
	private String content;
	
	/**
	 * 构造函数
	 * @param id
	 * @param time
	 * @param operator
	 * @param content
	 */
	public LogPO(String id,String time,String operator,String content){
		this.id=id;
		this.time=time;
		this.operator=operator;
		this.content=content;
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

	public String getOperator() {
		return operator;
	}

	public String getContent() {
		return content;
	}

}
