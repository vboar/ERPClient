package vo;

import util.DocumentStatus;
import util.DocumentType;

import java.util.ArrayList;

public class SaleVO {

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
	 * 客户等级
	 */
	public int customerVIP;

	/**
	 * 业务员
	 */
	public String salesmanId;

	/**
	 * 操作员id
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
	 * 赠品列表
	 */
	public ArrayList<PresentLineItemVO> giftList;
	
	/**
	 * 赠品单编号
	 */
	public String presentId;

	/**
	 * 折让前总额
	 */
	public double totalBeforeDiscount;

	/**
	 * 折让
	 */
	public double discount;

	/**
	 * 代金券
	 */
	public double voucher;

	/**
	 * 折让后总额
	 */
	public double totalAfterDiscount;

	/**
	 * 备注
	 */
	public String remark;

	/**
	 * 审批状态
	 */
	public DocumentStatus approvalState;

	/**
	 * 是否为红冲单据
	 */
	public boolean isWriteOff;

	/**
	 * 单据类型
	 */
	public DocumentType receiptType;

	/**
	 * 构造方法
	 * @param customerId
	 * @param storage
	 * @param saleList
	 * @param totalBeforeDiscount
	 * @param discount
	 * @param voucher
	 * @param totalAfterDiscount
	 * @param remark
	 * @param approvalState
	 * @param isWriteOff
	 * @param receiptType
	 */
	public SaleVO(String id, String time, String customerId,
			String customerName, int customerVIP, String salesmanId,
			String operatorId, String storage,
			ArrayList<CommodityLineItemVO> saleList,String presentId,
			ArrayList<PresentLineItemVO> giftList, double totalBeforeDiscount,
			double discount, double voucher, double totalAfterDiscount,
			String remark, DocumentStatus approvalState, boolean isWriteOff,
			DocumentType receiptType) {
		this.id = id;
		this.time = time;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerVIP = customerVIP;
		this.salesmanId = salesmanId;

		this.operatorId = operatorId;
		this.storage = storage;
		this.saleList = saleList;
		this.presentId=presentId;
		this.giftList = giftList;
		this.totalBeforeDiscount = totalBeforeDiscount;
		this.discount = discount;
		this.voucher = voucher;
		this.totalAfterDiscount = totalAfterDiscount;
		this.remark = remark;
		this.approvalState = approvalState;
		this.isWriteOff = isWriteOff;
		this.receiptType = receiptType;
	}

	public SaleVO(String name, double total) {
		this.customerName = name;
		this.totalAfterDiscount = total;
	}
}
