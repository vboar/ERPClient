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
	public String customerId;
	
	/**
	 * 客户姓名
	 */
	public String customerName;
	
	/**
	 * 赠品列表
	 */
	public ArrayList<PresentLineItemVO> list;
	
	/**
	 * 审批状态
	 */
	public DocumentStatus documentStatus;
	
	/**
	 * 单据类型
	 */
	public DocumentType documentType;
	
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
	public PresentVO(String id, String time, String customerId,
			String customerName, ArrayList<PresentLineItemVO> list,
			DocumentStatus documentStatus,DocumentType documentType,boolean isWriteoff) {
		super();
		this.id = id;
		this.time = time;
		this.customerId = customerId;
		this.customerName = customerName;
		this.list = list;
		this.documentStatus = documentStatus;
		this.documentType=documentType;
		this.isWriteoff = isWriteoff;
	}
	
	public PresentVO(String time, String clientName){
		this.time = time;
		this.customerName = clientName;
	}
	
	public String lineItemToString(){
		String str="";
		for(int i=0; i<list.size()-1; ++i){
			str =  str + list.get(i).toString()+"\n";
		}
		str = str + list.get(list.size()-1).toString();
		return str;
	}
}
