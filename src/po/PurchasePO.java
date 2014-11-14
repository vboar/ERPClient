/**
 * 进货单PO类
 * @author oneoneO
 * @date  2014/10/25 
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

public class PurchasePO implements Serializable {

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
	* 总额
	*/
	private double total;
	   
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
	* 
	* @param receiptId
	* @param customerId
	* @param name
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
	public PurchasePO(String receiptId,String time,String customerId,String name,
			String operator,String storage,ArrayList<CommodityLineItemPO> saleList,double total,
			String remark,DocumentStatus approvalState,boolean isWriteOff,DocumentType receiptType){
		this.time = time;
		this.receiptId=receiptId;
		this.customerId=customerId;
		this.name=name;
		this.operator=operator;
		this.storage=storage;
		this.saleList=saleList;
		this.total=total;
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

	public boolean isWriteOff() {
		return isWriteOff;
	}

	public void setWriteOff(boolean isWriteOff) {
		this.isWriteOff = isWriteOff;
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

	public String getOperator() {
		return operator;
	}

	public String getStorage() {
		return storage;
	}

	public ArrayList<CommodityLineItemPO> getSaleList() {
		return saleList;
	}

	public double getTotal() {
		return total;
	}

	public String getRemark() {
		return remark;
	}

	public DocumentType getReceiptType() {
		return receiptType;
	}
	
}
