/**
 * 销售单PO类
 * @author oneoneO
 * @date  2014/10/25
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

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
	* 客户姓名
	*/
	private String name;
	
	/**
	* 业务员
	*/
	private String salesman;
	/**
	* 操作员
	*/
	private String operator;
	   
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
	private DocumentStatus approvalState;
	   
	/**
	* 是否为红冲单据
	*/
	private boolean isWriteOff;
	   
	/**
	* 单据类型
	*/
	private DocumentType receiptType;
	
	/**
	 * 构造方法
	 * @param receiptId
	 * @param time
	 * @param customerId
	 * @param name
	 * @param salsman
	 * @param operator
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
	public SalePO(String receiptId,String time,String customerId,String name,String salsman,
			String operator,String storage,ArrayList<CommodityLineItemPO> saleList,double totalBeforeDiscount,
			double discount,double voucher,double totalAfterDiscount,String remark,
			DocumentStatus approvalState,boolean isWriteOff,DocumentType receiptType){
		this.receiptId=receiptId;
		this.time = time;
		this.customerId=customerId;
		this.name=name;
		this.salesman = salsman;
		this.operator=operator;
		this.storage=storage;
		this.saleList=saleList;
		this.totalBeforeDiscount=totalBeforeDiscount;
		this.discount=discount;
		this.voucher=voucher;
		this.totalAfterDiscount=totalAfterDiscount;
		this.remark=remark;
		this.approvalState=approvalState;
		this.isWriteOff=isWriteOff;
		this.receiptType=receiptType;
	}

	public DocumentStatus getApprovalState() {
		return approvalState;
	}

	public void setApprovalState(DocumentStatus approvalState) {
		this.approvalState = approvalState;
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

	public String getName() {
		return name;
	}

	public String getSalesman() {
		return salesman;
	}

	public String getOperator() {
		return operator;
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

	public DocumentType getReceiptType() {
		return receiptType;
	}
	
}
