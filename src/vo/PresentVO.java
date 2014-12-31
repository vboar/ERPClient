/**
 * 赠品单VO类
 * @author Vboar
 * @date 2014/10/25
 */
package vo;

import util.DocumentStatus;
import util.DocumentType;

import java.util.ArrayList;

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
	 * 是否可红冲
	 */
	public boolean canWriteOff;

	public PresentVO(String id, String time, String customerId,
			String customerName, ArrayList<PresentLineItemVO> list,
			DocumentStatus documentStatus,DocumentType documentType,
			boolean isWriteoff, boolean canWriteOff) {
		super();
		this.id = id;
		this.time = time;
		this.customerId = customerId;
		this.customerName = customerName;
		this.list = list;
		this.documentStatus = documentStatus;
		this.documentType=documentType;
		this.isWriteoff = isWriteoff;
		this.canWriteOff = canWriteOff;
	}
	
	public PresentVO(String time, String clientName){
		this.time = time;
		this.customerName = clientName;
	}
	
	public PresentVO(){}
	
	public String lineItemToString(){
		String str="";
		for(int i=0; i<list.size()-1; ++i){
			str =  str + list.get(i).toString()+"\n";
		}
		str = str + list.get(list.size()-1).toString();
		return str;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public DocumentStatus getStatus() {
		return documentStatus;
	}

	@Override
	public DocumentType getType() {
		return documentType;
	}

	@Override
	public boolean isWriteoff() {
		return isWriteoff;
	}

	@Override
	public String getTime() {
		return time;
	}

	@Override
	public void setStatus(DocumentStatus status) {
		this.documentStatus = status;
	}

	public String listToStr() {
		String str="";
		for(int i=0; i<this.list.size()-1; ++i){
			str = str + list.get(i)+",";
		}
		str = str + list.get(list.size()-1);
		return str;
		
	}
}
