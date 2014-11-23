/**
 * 销售单PO类
 * @author oneoneO
 * @date  2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

public class SalePO implements Serializable {

	/**
	* 序列化UID
	*/
	private static final long serialVersionUID = 1L;

	/**
	* 单据编号
	*/
	private String receiptId;
	 
	/**
	* 创建时间
	*/
	private String time;
	   
	/**
	* 客户编号
	*/
	 private String customerId;	
	
	/**
	* 业务员
	*/
	private String salesman;
	
	/**
	* 操作员
	*/
	private String operatorId;
	   
	/**
	* 仓库
	*/
	private String storage;
	   
	/**
	* 入库商品列表
	*/
	private ArrayList<CommodityLineItemPO> saleList;
	   
	/**
	* 折让前总额
	*/
	private double totalBeforeDiscount;
	   
	/**
	* 折让
	*/
	private double discount;
	   
	/**
	* 代金券
	*/
	private double voucher;
	   
	/**
	* 折让后总额
	*/
	private double totalAfterDiscount;
	   
	/**
	* 备注
	*/
	private String remark;
	   
	/**
	* 审批状态
	*/
	private int documentStatus;
	   
	/**
	* 是否为红冲单据
	*/
	private boolean isWriteOff;
	   
	/**
	* 单据类型
	*/
	private int documentType;
	
	/**
	 * 构造方法
	 * @param receiptId
	 * @param time
	 * @param customerId
	 * @param salsman
	 * @param operatorId
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
	public SalePO(String receiptId,String time,String customerId,String salsman,
			String operatorId,String storage,ArrayList<CommodityLineItemPO> saleList,double totalBeforeDiscount,
			double discount,double voucher,double totalAfterDiscount,String remark,
			int documentStatus,boolean isWriteOff,int documentType){
		this.receiptId=receiptId;
		this.time = time;
		this.customerId=customerId;
		this.salesman = salsman;
		this.operatorId=operatorId;
		this.storage=storage;
		this.saleList=saleList;
		this.totalBeforeDiscount=totalBeforeDiscount;
		this.discount=discount;
		this.voucher=voucher;
		this.totalAfterDiscount=totalAfterDiscount;
		this.remark=remark;
		this.documentStatus=documentStatus;
		this.isWriteOff=isWriteOff;
		this.documentType=documentType;
	}
	
	public String getReceiptId() {
		return receiptId;
	}

	public String getTime() {
		return time;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getSalesman() {
		return salesman;
	}

	public String getStorage() {
		return storage;
	}

	public ArrayList<CommodityLineItemPO> getSaleList() {
		return saleList;
	}

	public double getTotalBeforeDiscount() {
		return totalBeforeDiscount;
	}

	public double getDiscount() {
		return discount;
	}

	public double getVoucher() {
		return voucher;
	}

	public double getTotalAfterDiscount() {
		return totalAfterDiscount;
	}

	public String getRemark() {
		return remark;
	}

	public boolean isWriteOff() {
		return isWriteOff;
	}

	public int getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(int documentStatus) {
		this.documentStatus = documentStatus;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public int getDocumentType() {
		return documentType;
	}
	
}
