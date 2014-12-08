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
	 * 单据类型
	 */
	public DocumentType receiptType;

	public PurchaseVO(String id, String time,String customerId, String customerName,
			String operatorId, String storage,
			ArrayList<CommodityLineItemVO> saleList, double total,
			String remark, DocumentStatus documentStatus, boolean isWriteOff,
			DocumentType receiptType) {
		this.id = id;
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
	}

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
}
