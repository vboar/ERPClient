/**
 * 赠品单VO类
 * @author Vboar
 * @date 2014/10/25
 */
package vo;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

public class PresentVO implements DocumentVO {
	
	/**
	 * 赠品单编号
	 */
	public String id;
	
	/**
	 * 创建时间
	 */
	public String time;
	
	/**
	 * 客户编号
	 */
	public String clientId;
	
	/**
	 * 客户姓名
	 */
	public String clientName;
	
	/**
	 * 赠品列表
	 */
	public ArrayList<PresentLineItemVO> list;
	
	/**
	 * 审批状态
	 */
	public DocumentStatus status;
	
	/**
	 * 单据类型
	 */
	public DocumentType type;
	
	/**
	 * 是否为红冲单据
	 */
	public boolean isWriteoff;
	
	/**
	 * 构造方法
	 * @param id
	 * @param time
	 * @param clientId
	 * @param clientName
	 * @param list
	 * @param status
	 * @param type
	 * @param isWriteoff
	 */
	public PresentVO(String id, String time, String clientId,
			String clientName, ArrayList<PresentLineItemVO> list,
			DocumentStatus status,boolean isWriteoff) {
		super();
		this.id = id;
		this.time = time;
		this.clientId = clientId;
		this.clientName = clientName;
		this.list = list;
		this.status = status;
		this.isWriteoff = isWriteoff;
	}
	
	public PresentVO(String time, String clientName){
		this.time = time;
		this.clientName = clientName;
	}
	
}
