/**
 * 消息vo
 * @author chengcheng
 * @date 2014/10/25
 */
package vo;

public class MessageVO {
	
	/**
	 * id
	 */	
    public String id;
	
	/**
	 * 创建时间
	 */			
	public String time;
	
	/**
	 * 状态  0为未查看	
	 */
	public int state;
	
	/**
	 * 接收者
	 */
	public String receiver;
	
	/**
	 * 消息内容
	 */
    public String content;

	/**
	 * 构造函数
	 * @param id
	 * @param time
	 * @param state
	 * @param receiver
	 * @param content
	 */
    public MessageVO(String id,String time,int state,String receiver,String content){
    	this.id=id;
    	this.time=time;
    	this.state=state;
    	this.receiver=receiver;
    	this.content=content;
    }

}
