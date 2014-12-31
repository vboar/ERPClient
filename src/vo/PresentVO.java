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

	/**
	 * 构造函数
	 * @param id
	 * @param time
	 * @param customerId
	 * @param customerName
	 * @param list
	 * @param documentStatus
	 * @param documentType
	 * @param isWriteoff
	 * @param canWriteOff
	 */
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

	/**
	 * 构造函数
	 * @param time
	 * @param clientName
	 */
	public PresentVO(String time, String clientName){
		this.time = time;
		this.customerName = clientName;
	}

	/**
	 * 无参构造函数
	 */
	public PresentVO(){}

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

	/**
	 * 将条目转成String
	 * @return
	 */
	public String listToStr() {
		String str="";
		for(int i=0; i<this.list.size()-1; ++i){
			str = str + list.get(i)+",";
		}
		str = str + list.get(list.size()-1);
		return str;
		
	}
}
