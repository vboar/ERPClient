/**
 * 系统消息PO类
 * @author oneoneO
 * @date  2014/10/25 
 */
package po;

import java.io.Serializable;

public class MessagePO implements Serializable {

	/**
	 *序列化UID 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 消息编号
	 */
	private String id;
	
	/**
	 * 创建时间
	 */
	private String time;
	
	/**
	 * 状态		
	 */
	private int state;
	
	/**
	 * 接收者
	 */
	private String receiverId;
	
	/**
	 * 消息内容
	 */
    private String content;

	/**
	 * 构造方法
	 * @param id
	 * @param time
	 * @param state
	 * @param receiverId
	 * @param content
	 */
    public MessagePO(String id,String time,int state,String receiverId,String content){
    	this.id=id;
    	this.time=time;
    	this.state=state;
    	this.receiverId=receiverId;
    	this.content=content;
    }

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public String getContent() {
		return content;
	}
	
}
