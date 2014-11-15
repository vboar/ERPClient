/**
 * 报溢报损单VO类
 * @author Vboar
 * @date 2014/10/25
 */
package vo;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

public class ExceptionVO implements DocumentVO {

	/**
	 * 报溢报损单编号
	 */
	public String id;
	
	/**
	 * 创建时间
	 */
	public String time;
	
	/**
	 * 商品列表
	 */
	public ArrayList<ExceptionLineItemVO> list;
	
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
	 * @param list
	 * @param status
	 * @param type
	 * @param isWriteoff
	 */
	public ExceptionVO(String id, String time, ArrayList<ExceptionLineItemVO> list,
			DocumentStatus status, DocumentType type, boolean isWriteoff) {
		this.id = id;
		this.time = time;
		this.list = list;
		this.status = status;
		this.type = type;
		this.isWriteoff = isWriteoff;
	}
	
	public ExceptionVO(String id,String time){
		this.id = id;
		this.time = time;
	}
	
	public ExceptionVO(){
		
	}
}
