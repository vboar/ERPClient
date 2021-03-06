/**
 * 进货单VO类
 * @author oneoneO
 * @date 2014/10/25
 */

package vo;

import util.DocumentStatus;
import util.DocumentType;

import java.util.ArrayList;

public class PurchaseVO implements DocumentVO {

	/**
	 * 单据编号
	 */
	public String id;

	/**
	 * 对应进货单ID(针对退货单)
	 */
	public String purId;
	
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
	 * 操作员
	 */
	public String operatorId;

	/**
	 * 仓库
	 */
	public String storage;

	/**
	 * 入库商品列表
	 */
	public ArrayList<CommodityLineItemVO> saleList;

	/**
	 * 总额
	 */
	public double total;

	/**
	 * 备注
	 */
	public String remark;

	/**
	 * 审批状态
	 */
	public DocumentStatus documentStatus;

	/**
	 * 是否为红冲单据
	 */
	public boolean isWriteOff;
	
	/**
	 * 是否可退货
	 */
	public boolean canReturn;
	
	/**
	 * 是否可红冲
	 */
	public boolean canWriteOff;

	/**
	 * 单据类型
	 */
	public DocumentType receiptType;

	/**
	 * 构造函数
	 * @param id
	 * @param purId
	 * @param time
	 * @param customerId
	 * @param customerName
	 * @param operatorId
	 * @param storage
	 * @param saleList
	 * @param total
	 * @param remark
	 * @param documentStatus
	 * @param isWriteOff
	 * @param canReturn
	 * @param canWriteOff
	 * @param receiptType
	 */
	public PurchaseVO(String id, String purId, String time,String customerId, String customerName,
			String operatorId, String storage,
			ArrayList<CommodityLineItemVO> saleList, double total,
			String remark, DocumentStatus documentStatus, boolean isWriteOff,boolean canReturn,
			boolean canWriteOff, DocumentType receiptType) {
		this.id = id;
		this.purId = purId;
		this.time=time;
		this.customerId = customerId;
		this.customerName = customerName;
		this.operatorId = operatorId;
		this.storage = storage;
		this.saleList = saleList;
		this.total = total;
		this.remark = remark;
		this.documentStatus = documentStatus;
		this.isWriteOff = isWriteOff;
		this.receiptType = receiptType;
		this.canReturn = canReturn;
		this.canWriteOff = canWriteOff;
	}

	/**
	 * 构造函数
	 * @param operator
	 * @param total
	 */
	public PurchaseVO(String operator, double total) {
		this.operatorId = operator;
		this.total = total;
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
		return receiptType;
	}

	@Override
	public boolean isWriteoff() {
		return isWriteOff;
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
	public String listToStr(){
		String str="";
		for(int i=0; i<saleList.size()-1; ++i){
			str=str+saleList.get(i)+",";
		}
		str+=saleList.get(saleList.size()-1);
		return str;
	}
}
