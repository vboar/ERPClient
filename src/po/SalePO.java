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
	private String id;
	 
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
	private String customerName;
	 
	/**
	* 客户vip
	*/
	private int customerVIP;
	
	/**
	* 业务员
	*/
	private String salesmanId;
	
	/**
	* 操作员id
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
	* 赠品ID
	*/
	private String presentId;
	   
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
	
	public SalePO(String id,String time,String customerId,String customerName,int customerVIP,String salesmanId,
			String operatorId,String storage,ArrayList<CommodityLineItemPO> saleList,
			String presentId,double totalBeforeDiscount,
			double discount,double voucher,double totalAfterDiscount,String remark,
			int documentStatus,boolean isWriteOff,int documentType){
		this.id=id;
		this.time = time;
		this.customerId=customerId;
		this.customerName=customerName;
		this.customerVIP=customerVIP;
		this.salesmanId = salesmanId;
		this.operatorId=operatorId;
		this.storage=storage;
		this.saleList=saleList;
		this.presentId=presentId;
		this.totalBeforeDiscount=totalBeforeDiscount;
		this.discount=discount;
		this.voucher=voucher;
		this.totalAfterDiscount=totalAfterDiscount;
		this.remark=remark;
		this.documentStatus=documentStatus;
		this.isWriteOff=isWriteOff;
		this.documentType=documentType;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getSalesmanId() {
		return salesmanId;
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

	public String getCustomerName() {
		return customerName;
	}

	public int getCustomerVIP() {
		return customerVIP;
	}

	public String getPresentId() {
		return presentId;
	}
	
}
